package com.lovesoft.androger.tools;

public class StringCutter {
	private final int maxLenght;
	private final String prefix = "...";
	private boolean usePrefix = true;
	public StringCutter(int maxLenght) {
		super();
		this.maxLenght = maxLenght;
		if(maxLenght <= prefix.length()) {
			usePrefix = false;
		}
	}
	public String cut(final String str) {
		if(str == null) {
			return null;
		}
		if(str.length() > maxLenght) {
			if(usePrefix) {
				final int startIndex = str.length() - maxLenght + prefix.length();
				return prefix + str.substring(startIndex);
			} else {
				final int startIndex = str.length() - maxLenght;
				return str.substring(startIndex);				
			}
		}
		return str;
	}
}
