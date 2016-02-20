package com.lovesoft.androger.core.storage;

import com.lovesoft.androger.core.LogID;

public class LogIDDTO {
	private String filePath;
	private String url;
	
	public LogIDDTO() {
	}
	
	public LogIDDTO(LogID logID) {
		this.filePath = logID.getFilePath();
	}
	
	public LogID toLogID() {
		return new LogID(this.filePath.trim());
	}
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
