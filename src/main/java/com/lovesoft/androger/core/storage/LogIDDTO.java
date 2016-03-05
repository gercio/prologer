package com.lovesoft.androger.core.storage;

import com.lovesoft.androger.core.LogID;

public class LogIDDTO {
	private String filePath;
	private String url;
	private boolean isResource;
	
	public LogIDDTO() {
	}
	
	public LogIDDTO(LogID logID) {
		this.filePath = logID.getFilePath();
		this.isResource = logID.isResource();
	}
	
	public LogID toLogID() {
		LogID id = new LogID(this.filePath.trim());
		id.setResource(isResource);
		return id;
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

	public boolean isResource() {
		return isResource;
	}

	public void setResource(boolean isResource) {
		this.isResource = isResource;
	}
}
