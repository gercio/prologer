package com.lovesoft.androger.core;


public interface LogFilter {
	LogString filter(LogString logString);
	String filter(String currentText);
}
