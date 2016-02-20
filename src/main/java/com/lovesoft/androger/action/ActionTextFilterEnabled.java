package com.lovesoft.androger.action;

import com.lovesoft.androger.LogDataSource;
import com.lovesoft.androger.core.LogFilter;
import com.lovesoft.androger.core.LogFilterByStringInLine;
import com.lovesoft.androger.core.LogViewManager;

public class ActionTextFilterEnabled extends ActionAbstract {
	private String textFilter;
	
	public ActionTextFilterEnabled(LogViewManager viewManager, LogDataSource ds, String textFilter) {
		super(viewManager, ds);
		this.textFilter = textFilter;
	}

	@Override
	public void run() {
		LogFilter filter = new LogFilterByStringInLine(textFilter);
		getDs().pause(true);
		try {
			getViewManager().turnOnFilter(filter);			
		} finally {
			getDs().pause(false);	
		}
	}
}
