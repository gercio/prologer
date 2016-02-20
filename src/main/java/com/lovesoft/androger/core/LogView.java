package com.lovesoft.androger.core;

public interface LogView {

	String getText();
	int getMaxTextFieldSize();
	int getNumberOfVisibleRows();
	void appendText(String newText);
	void setViewAtPosition(int i);
	void setText(String newText);
	LogID getLogID();

}
