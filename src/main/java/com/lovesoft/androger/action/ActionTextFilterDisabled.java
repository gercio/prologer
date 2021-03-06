package com.lovesoft.androger.action;

import com.lovesoft.androger.core.LogViewManager;
import com.lovesoft.androger.core.datasource.LogDataSource;

public class ActionTextFilterDisabled extends ActionAbstract {
	public ActionTextFilterDisabled(LogViewManager viewManager, LogDataSource ds) {
		super(viewManager, ds);
	}

	@Override
	public void run() {
		getDs().pause(true);
		try {
			getViewManager().turnOnFilter(null);
		} finally {
			getDs().pause(false);
		}
	}

}
