package com.lovesoft.androger.core.storage;

import java.util.ArrayList;
import java.util.List;

import com.lovesoft.androger.core.LogID;

public class LogContainer{
	private String logName;
	private List<LogID> logIds = new ArrayList<>();
	
	public LogContainer() {
	}
	public LogContainer(String logName) {
		this.logName = logName;
	}
	
	public String getLogName() {
		return logName;
	}
	public void setLogName(String logName) {
		this.logName = logName;
	}
	public List<LogID> getLogIds() {
		return logIds;
	}
	public void setLogIds(List<LogID> logIds) {
		this.logIds = logIds;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((logName == null) ? 0 : logName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LogContainer other = (LogContainer) obj;
		if (logName == null) {
			if (other.logName != null)
				return false;
		} else if (!logName.equals(other.logName))
			return false;
		return true;
	}
}
