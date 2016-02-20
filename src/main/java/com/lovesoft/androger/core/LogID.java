package com.lovesoft.androger.core;

import java.io.Serializable;

/**
 * Identify log 
 * @author Patryk 2014.09.06
 */

public class LogID implements Serializable {
	private static final String PREFIX_FILE_PATH = "filePath=";
	private static final long serialVersionUID = -1155020709144131249L;
	private String filePath;
	// Here is the place for other id's like http address in future.

	public LogID() {
	}
	
	public LogID(String filePath) {
		this.filePath = filePath;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((filePath == null) ? 0 : filePath.hashCode());
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
		LogID other = (LogID) obj;
		if (filePath == null) {
			if (other.filePath != null)
				return false;
		} else if (!filePath.equals(other.filePath))
			return false;
		return true;
	}
}
