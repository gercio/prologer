package com.lovesoft.prologer.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.lovesoft.androger.LogDataObserver;
import com.lovesoft.androger.Setup;
import com.lovesoft.androger.core.LogFileDS;
import com.lovesoft.androger.core.LogID;
import com.lovesoft.androger.core.LogString;

public class LogFileDSTest {

	private static final String OUT_FILE_NAME = "testfile.txt";
	private static final String SIMPLE_CONTENT = "We are in this beautifull universe and we are so happy to say that óóó óóó:) !!!!";
	private static final String SIMPLE_CONTENT2 = "Life is all about Love. True story.óóó";

	private DataWriter dw;
	private LogFileDS ds;
	@Before
	public void beforeTest() {
		try {
			(new DataWriter(OUT_FILE_NAME)).removeFile();
			dw = new DataWriter(OUT_FILE_NAME); 
			ds = new LogFileDS(new LogID(OUT_FILE_NAME));
			
		} catch ( Exception e) {
			// do not care
		} 
	}
	
	@After
	public void afterTest() {
		try {
			dw.close();
		} catch (Exception e) {
			// Do not care
		}
		try {
			ds.close();
		} catch (Exception e) {
			// Do not care
		}
	}
	
	
	@Test
	public void testSimpleNewLog() throws Exception {

			ds.start();
			FakeLogDataObserver logDataObserver = new FakeLogDataObserver();
			ds.addLogDataObserver(logDataObserver);
			Thread.sleep(20);
			dw.write(SIMPLE_CONTENT);
			dw.close();
			
			waitForLoger();
			Assert.assertEquals(SIMPLE_CONTENT, logDataObserver.getLogString());
	}
	
	@Test
	public void testMultipleLogWritings() throws Exception {
			ds.start();
			FakeLogDataObserver logDataObserver = new FakeLogDataObserver();
			ds.addLogDataObserver(logDataObserver);
			Thread.sleep(20);
			
			dw.write(SIMPLE_CONTENT);
			waitForLoger();
			dw.write(SIMPLE_CONTENT2);
			waitForLoger();
			
			Assert.assertEquals(SIMPLE_CONTENT+SIMPLE_CONTENT2, logDataObserver.getLogString());
	}
	
	@Test
	public void testMultipleWithLogRemoval() throws Exception {
			ds.start();
			FakeLogDataObserver logDataObserver = new FakeLogDataObserver();
			ds.addLogDataObserver(logDataObserver);
			Thread.sleep(20);
			
			dw.write(SIMPLE_CONTENT);
			waitForLoger();
			dw.turncateFile();
			waitForLoger();
			dw.write(SIMPLE_CONTENT2);
			waitForLoger();
			
			Assert.assertEquals(SIMPLE_CONTENT+SIMPLE_CONTENT2, logDataObserver.getLogString());
	}
	
	@Test
	public void testSkipIfToLong() throws Exception {
			dw.write(SIMPLE_CONTENT2);
			dw.close();
			ds.setFileChunkSize(11);
			FakeLogDataObserver logDataObserver = new FakeLogDataObserver();
			ds.addLogDataObserver(logDataObserver);
			
			ds.start();
			waitForLoger();

	}
	

	private void waitForLoger() throws InterruptedException {
		Thread.sleep(Setup.getPauseTimeInMs() + 50);
	}

	private class FakeLogDataObserver implements LogDataObserver {
		private String logString = "";

		public void logObserved(LogString data) {
			logString += data;
		}

		public String getLogString() {
			return logString;
		}
	}

	private class DataWriter {
		private String outFileName;
		private FileOutputStream file;

		public DataWriter(String outFileName) throws Exception {
			super();
			this.outFileName = outFileName;
			
		}

		public void turncateFile() throws IOException {
			write("", true);
			closeFile();
		}

		public void removeFile() throws IOException {
			closeFile();
			File file1 = new File(outFileName);
			if (file1.exists()) {
				if (!file1.delete()) {
					throw new RuntimeException("Can't remove the file " + outFileName);
				}
			}
		}
		
		public void createFile() throws IOException {
			File file1 = new File(outFileName);
			if(file1.exists()) {
				return;
			}
			if(!file1.createNewFile()) {
				throw new RuntimeException("Can't create the file " + outFileName);
			}
		}

		public void write(String s, boolean resetFile) throws IOException {
			if(file == null || resetFile) {
				closeFile();
				file = new FileOutputStream(outFileName, false);				
			}
			file.write(s.getBytes());
			file.flush();
			
		}

		private void closeFile() throws IOException {
			if(file != null) {
				file.flush();
				file.close();
				file = null;
			}
		}

		public void write(String s) throws IOException {
			write(s, false);
		}

		public void close() throws IOException {
			closeFile();
		}

	}
}
