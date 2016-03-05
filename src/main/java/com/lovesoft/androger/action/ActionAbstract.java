package com.lovesoft.androger.action;

import com.lovesoft.androger.core.LogViewManager;
import com.lovesoft.androger.core.datasource.LogDataSource;

public abstract class ActionAbstract implements Action {
	private LogViewManager viewManager;
	private LogDataSource ds;

	public ActionAbstract(LogViewManager viewManager, LogDataSource ds) {
		super();
		this.viewManager = viewManager;
		this.ds = ds;
	}

	protected LogViewManager getViewManager() {
		return viewManager;
	}

	protected void setViewManager(LogViewManager viewManager) {
		this.viewManager = viewManager;
	}

	protected LogDataSource getDs() {
		return ds;
	}

	protected void setDs(LogDataSource ds) {
		this.ds = ds;
	}

}
