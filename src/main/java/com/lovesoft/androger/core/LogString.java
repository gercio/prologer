package com.lovesoft.androger.core;

public class LogString {
	private StringBuilder sb = new StringBuilder(1024 * 10);

	public LogString() {
	}

	public LogString(String s) {
		append(s);
	}

	public String getString() {
		return sb.toString();
	}

	public void setString(String s) {
		this.sb = new StringBuilder(s);
	}

	public int getSize() {
		return sb.length();
	}

	public void append(LogString log) {
		this.sb.append(log.sb);
	}

	public void append(String string) {
		sb.append(string);
	}

	public String getSubString(int beginIndex, int endIndex) {
		return sb.toString().substring(beginIndex, endIndex);
	}
	
	public String toString() {
		return sb.toString();
	}

	public String getSubString(int beginIndex) {
		return sb.toString().substring(beginIndex);
	}
}
