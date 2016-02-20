package com.lovesoft.androger;

import com.lovesoft.androger.core.LogID;
import com.lovesoft.androger.core.LogView;

public interface ApplicationInput {
	void textFilterDisabled(LogView logTextView);
	void textFilterEnabled(LogView logTextView, String filterText);
	void GUIViewClosed();
	boolean addNewLogWatch();
	void closeLogWatch(LogID logID);
	void togglePauseLogView(LogID logID);
	void clearLogView(LogID logID);
}
