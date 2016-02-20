package com.lovesoft.androger.core;

import com.lovesoft.androger.core.storage.ApplicationState;

/**
 * View for logger. This can be console or swing components or something else.
 * @author Patryk Kaluzny
 *
 */
public interface GUIView {
	void showView();
	LogView createNewTextView(String name, LogID logID);
	void removeTextView(LogView logView);
	String getNewLogFileName();
	/**
	 * Write GUI state to application state
	 * @param as
	 */
	void saveTo(ApplicationState as);
	
	void loadFrom(ApplicationState as);
}
