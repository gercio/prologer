package com.lovesoft.androger.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import com.lovesoft.androger.LogDataObserver;
import com.lovesoft.androger.LogDataSource;
import com.lovesoft.androger.Setup;

public class LogFileDS extends DataSourceInputStream implements LogDataSource, Runnable {
	private static final long PAUSE_TIME_IN_MS = Setup.getPauseTimeInMs();
	private File file;

	public LogFileDS(LogID logId) {
		super(logId);
	}

	@Override
	protected long getCurrentLenght() {
		return file.length();
	}


	@Override
	protected InputStream createInputStream(LogID logID) throws IOException {
		file = new File(logID.getFilePath());
		return new FileInputStream(file);
	}
	
}
