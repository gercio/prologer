package com.lovesoft.androger.core.storage;

import java.util.ArrayList;
import java.util.List;

public class ApplicationStateDTO {
	private List<LogContainerDTO> logContainers = new ArrayList<LogContainerDTO>();
	private GUIStateDTO guiState;

	public ApplicationStateDTO() {

	}

	public ApplicationStateDTO(ApplicationState as) {
		for (LogContainer c : as.getLogContainers()) {
			logContainers.add(new LogContainerDTO(c));
		}
		guiState = new GUIStateDTO(as.getGuiState());
	}

	public ApplicationState toAplicationState() {
		ApplicationState as = new ApplicationState();
		as.setGuiState(guiState.toGUIState());
		for (LogContainerDTO logContainerDTO : logContainers) {
			as.addLogContainer(logContainerDTO.toLogContainer());
		}
		return as;
	}

	public GUIStateDTO getGuiState() {
		return guiState;
	}

	public void setGuiState(GUIStateDTO guiState) {
		this.guiState = guiState;
	}

	public List<LogContainerDTO> getLogContainers() {
		return logContainers;
	}

	public void setLogContainers(List<LogContainerDTO> logContainers) {
		this.logContainers = logContainers;
	}
}
