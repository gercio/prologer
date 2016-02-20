package com.lovesoft.androger.core.storage;

import java.util.ArrayList;
import java.util.List;

import com.lovesoft.androger.core.LogID;

public class ApplicationState {
	private final String LOCAL_LOG_CONTAINER_NAME = "_local_log_container_";
	
	private List<LogContainer> logContainers = new ArrayList<>();
	private GUIState guiState = new GUIState();

	public ApplicationState() {
		// This local container should always exist
		logContainers.add(new LogContainer(LOCAL_LOG_CONTAINER_NAME)); 
	}
	public List<LogContainer> getLogContainers() {
		return logContainers;
	}

	public void setLogContainers(List<LogContainer> logContainers) {
		this.logContainers = logContainers;
	}

	public GUIState getGuiState() {
		return guiState;
	}

	public void setGuiState(GUIState guiState) {
		this.guiState = guiState;
	}
	
	public List<LogID> getLocalLogIDList() {
		LogContainer container = find(LOCAL_LOG_CONTAINER_NAME);
		return container.getLogIds();
	}
	public void addLogContainer(LogContainer logContainer) {
		logContainers.remove(logContainer);
		logContainers.add(logContainer);
		
	}
	private LogContainer find(String logContainerName) {
		for(LogContainer c : logContainers) {
			if(c.getLogName().equals(logContainerName)) {
				return c;
			}
		}
		return null;
	}
}
