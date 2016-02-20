package com.lovesoft.androger.core.storage;

import java.util.ArrayList;
import java.util.List;

import com.lovesoft.androger.core.LogID;

public class LogContainerDTO {
	private String logName;
	private List<LogIDDTO> logIds = new ArrayList<>();
	
	public LogContainerDTO() {
	}
	
	public LogContainerDTO(LogContainer c) {
		this.logName = c.getLogName();
		for(LogID l : c.getLogIds()) {
			logIds.add(new LogIDDTO(l));
		}
	}
	
	public String getLogName() {
		return logName;
	}
	public void setLogName(String logName) {
		this.logName = logName;
	}
	public List<LogIDDTO> getLogIds() {
		return logIds;
	}
	public void setLogIds(List<LogIDDTO> logIds) {
		this.logIds = logIds;
	}

	public LogContainer toLogContainer() {
		LogContainer lc = new LogContainer(logName);
		for(LogIDDTO logID : logIds) {
			lc.getLogIds().add(logID.toLogID());
		}
		return lc;
	}
}
