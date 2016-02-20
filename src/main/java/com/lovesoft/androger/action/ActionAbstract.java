package com.lovesoft.androger.action;

import com.lovesoft.androger.LogDataSource;
import com.lovesoft.androger.core.LogViewManager;

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
