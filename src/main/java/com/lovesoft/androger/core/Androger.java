package com.lovesoft.androger.core;

import java.util.ArrayList;
import java.util.List;

import com.lovesoft.androger.ApplicationInput;
import com.lovesoft.androger.Setup;
import com.lovesoft.androger.action.Action;
import com.lovesoft.androger.action.ActionTextFilterDisabled;
import com.lovesoft.androger.action.ActionTextFilterEnabled;
import com.lovesoft.androger.core.datasource.LogDataSource;
import com.lovesoft.androger.core.datasource.DataSourceFile;
import com.lovesoft.androger.core.datasource.DataSourceResource;
import com.lovesoft.androger.core.storage.ApplicationState;
import com.lovesoft.androger.core.storage.ApplicationStateStorage;

public class Androger implements ApplicationInput {
	private String[] args;
	private GUIView guiView;
	private List<LogSet> logs = new ArrayList<>();
	private static ApplicationInput appInput;

	public static Androger createProLoger(String[] args) {
		return new Androger(args);
	}

	public static ApplicationInput getApplicationInput() {
		if(appInput == null) {
			throw new RuntimeException("Use createProLoger method first!");
		}
		return appInput;
	}

	private Androger(String[] args) {
		this.args = args;
		Androger.appInput = this;
	}

	public void start() {
		// Hello :)
		guiView = Factory.buildGUIView(this);
		
		// Create log view for application argument
		if (args != null && args.length > 0) {
			createNewLogView(args[0]);
		} 
		
		// Load application state from disk
		ApplicationStateStorage storage = new ApplicationStateStorage(Setup.getConfigurationFilePath());
		ApplicationState as;
		try {
			as = storage.load();
			guiView.loadFrom(as);
			for(LogID id :as.getLocalLogIDList()) {
				createNewLogView(id);
			}			
		} catch (Exception ignored) {
		}
		
		// If still there is nothing opened, open default screen
		if (logs.size() == 0) {
			LogID logId = new LogID(Setup.getWelcomeFileName());
			logId.setResource(true);
			createNewLogView(logId);
		}
		guiView.showView();
	}

	private void createNewLogView(LogID id) {
		LogDataSource logDS;
		if(id.isResource()) {
			logDS = new DataSourceResource(id);
		} else {
			logDS = new DataSourceFile(id);			
		}
		createNewLogView(logDS);
	}
	
	private void createNewLogView(LogDataSource logDS) {
		LogView textView = guiView.createNewTextView(logDS.getVisibleName(), logDS.getLogID());
		LogViewManager viewManager = new LogViewManager(textView);
		guiView.showView();
		logDS.addLogDataObserver(viewManager);
		logDS.start();
		
		LogSet logSet = new LogSet();
		logSet.setLogDS(logDS);
		logSet.setLogView(textView);
		logSet.setViewManager(viewManager);
		logs.add(logSet);
	}

	private void createNewLogView(String filePath) {
		createNewLogView(new LogID (filePath));
	}

	@Override
	public void textFilterDisabled(LogView logTextView) {
		LogSet logSet = find(logTextView);
		Action a = new ActionTextFilterDisabled(logSet.getViewManager(), logSet.getLogDS());
		(new Thread(a)).start();

	}

	private LogSet find(LogView logView) {
		for(LogSet logSet: logs) {
			if(logSet.getLogView().equals(logView)) {
				return logSet;
			}
		}
		throw new RuntimeException("Can't find logset for view " + logView);
	}
	
	private LogSet find(LogID logId) {
		for(LogSet logSet: logs) {
			if(logSet.getLogView().getLogID().equals(logId)) {
				return logSet;
			}
		}
		throw new RuntimeException("Can't find logset for view " + logId);
	}

	@Override
	public void textFilterEnabled(LogView logTextView, String filterText) {
		LogSet logSet = find(logTextView);
		Action a = new ActionTextFilterEnabled(logSet.getViewManager(), logSet.getLogDS(), filterText);
		(new Thread(a)).start();
	}

	@Override
	public void GUIViewClosed() {
		// Save application configuration
		// Save opened log files
		ApplicationState as = new ApplicationState();
		for (LogSet logSet : this.logs) {
			as.getLocalLogIDList().add(logSet.getLogDS().getLogID());
		}
		//Save gui stuff
		try {
			guiView.saveTo(as);			
			ApplicationStateStorage storage = new ApplicationStateStorage(Setup.getConfigurationFilePath());
			storage.save(as);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

	@Override
	public boolean addNewLogWatch() {
		String logFileName = guiView.getNewLogFileName(); 
		if(logFileName != null && !"".equals(logFileName)) {
			createNewLogView(logFileName);
			return true;
		}
		return false;
	}

	@Override
	public void closeLogWatch(LogID logID) {
		LogSet logSet = findLogSet(logID);
		guiView.removeTextView(logSet.getLogView());
		logSet.getLogDS().stop();
		logs.remove(logSet);
	}

	private LogSet findLogSet(LogID logID) {
		for(LogSet logSetItem: logs) {
			if(logSetItem.getLogDS().getLogID().equals(logID)) {
				return logSetItem;
			}
		}
		throw new RuntimeException("Can't find proper log set for logId " + logID);
	}

	@Override
	public void togglePauseLogView(LogID logID) {
		LogDataSource ds = find(logID).getLogDS();
		ds.pause(!ds.isPaused());
	}

	@Override
	public void clearLogView(LogID logID) {
		find(logID).getLogView().setText("");
	}
}
