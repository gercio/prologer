package com.lovesoft.androger;

import com.lovesoft.androger.core.LogID;


public interface LogDataSource {
	void addLogDataObserver(LogDataObserver logDataObserver);
	void removeLogDataObserver(LogDataObserver logDataObserver);
	void pause(boolean paused);
	boolean isPaused();
	void start();
	void stop();
	String getVisibleName();
	LogID getLogID();
}
