package com.lovesoft.androger.core;

import com.lovesoft.androger.core.datasource.LogDataSource;

public class LogSet {
	private LogView logView;
	private LogViewManager viewManager;
	private LogDataSource logDS;
	public LogView getLogView() {
		return logView;
	}
	public void setLogView(LogView logView) {
		this.logView = logView;
	}
	public LogViewManager getViewManager() {
		return viewManager;
	}
	public void setViewManager(LogViewManager viewManager) {
		this.viewManager = viewManager;
	}
	public LogDataSource getLogDS() {
		return logDS;
	}
	public void setLogDS(LogDataSource logDS) {
		this.logDS = logDS;
	}

	
}
