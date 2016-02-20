package com.lovesoft.androger.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import com.lovesoft.androger.LogDataObserver;
import com.lovesoft.androger.LogDataSource;
import com.lovesoft.androger.Setup;

public class LogFileDS implements LogDataSource, Runnable {
	private static final long PAUSE_TIME_IN_MS = Setup.getPauseTimeInMs();
	private int fileChunkSize = Setup.getFileChunkSize();
	private LogID logID;
	private FileInputStream fileIn;
	private File file;
	private Set<LogDataObserver> observers;
	private AtomicBoolean canRun;
	private AtomicBoolean isPaused;
	private Long lastLenght;
	private Thread workingThread;

	public LogFileDS(LogID inputFileName) {
		this.logID = inputFileName;
		lastLenght = 0L;
		canRun = new AtomicBoolean(false);
		isPaused = new AtomicBoolean(true);
		observers = new HashSet<>();
	}

	public void start() {
		if (!canRun.get()) {
			canRun.set(true);
			isPaused.set(false);
			workingThread = new Thread(this);
			workingThread.start();
		}
	}

	public void addLogDataObserver(LogDataObserver logDataObserver) {
		observers.add(logDataObserver);
	}

	public void removeLogDataObserver(LogDataObserver logDataObserver) {
		observers.remove(logDataObserver);
	}

	public void run() {
		try {
			while (canRun.get()) {
				synchronized (isPaused) {
					if (!isPaused.get()) {
						doWork();
					}
				}
				Thread.sleep(PAUSE_TIME_IN_MS);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void doWork() {
		if (canReadFile()) {
			long currentLenght = file.length();
			if (lastLenght != currentLenght) {
				// We have new data to read!

				if (currentLenght < lastLenght) {
					reset();
				}
				if (canReadFile()) {
					long bytesReadOrSkiped = readFileAndNotifyObservers();
					lastLenght = lastLenght + bytesReadOrSkiped;
				}
			}
		} else {
			lastLenght = 0l;
		}
	}

	private long readFileAndNotifyObservers() {

		byte[] buffor = new byte[fileChunkSize];
		LogString log = null;
		long skipBytes = 0;
		int bytesRead = 0;
		try {
			long currentLenght = file.length();
			long bytesToSkip = 0;
			if(currentLenght - lastLenght > fileChunkSize) {
				bytesToSkip = currentLenght - fileChunkSize;
			}
			skipBytes = bytesToSkip;
			while(bytesToSkip > 0) {
				// Let's pray for end of this loop! :)
				bytesToSkip -= fileIn.skip(bytesToSkip);
			}
			bytesRead = fileIn.read(buffor);
			if (bytesRead > 0) {
				log = new LogString();
				log.append(new String(buffor, 0, bytesRead));
			}
		} catch (IOException e) {
			e.printStackTrace(); // TODO
			reset();
		}
		if(bytesRead > 0) {
			callObservers(log);			
		}
		if(bytesRead + skipBytes > 0) {
			return bytesRead + skipBytes;			
		}
		return 0;
	}

	private void callObservers(LogString log) {
		for (LogDataObserver observer : this.observers) {
			observer.logObserved(log);
		}
	}

	private boolean canReadFile() {
		if (fileIn != null) {
			return true;
		}
		try {
			openFile();
			return true;
		} catch (Exception ex) {
			System.err.println("Can't read file " + logID.getFilePath() + " Exception: " + ex.getMessage());
			reset();
			return false;
		}
	}

	private void openFile() throws FileNotFoundException {
		if (fileIn == null) {
			file = new File(logID.getFilePath());
			fileIn = new FileInputStream(file);
		}
	}

	private void reset() {
		if (fileIn != null) {
			try {
				fileIn.close();
			} catch (IOException e) {
				// Do not care
			}
		}
		file = null;
		fileIn = null;
		lastLenght = 0L;
	}

	public void close() throws Exception {
		canRun.set(false);
		if (fileIn != null) {
			fileIn.close();
		}

	}

	@Override
	public void pause(boolean pause) {
		synchronized (isPaused) {
			isPaused.set(pause);
		}
	}

	public void setFileChunkSize(int fileChunkSize) {
		this.fileChunkSize = fileChunkSize;
	}

	@Override
	public String getVisibleName() {
		return Setup.getGuistringCutter().cut(logID.getFilePath());
	}

	public LogID getLogID() {
		return this.logID;
	}

	@Override
	public void stop() {
		try {
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isPaused() {
		return isPaused.get();
	}
}
