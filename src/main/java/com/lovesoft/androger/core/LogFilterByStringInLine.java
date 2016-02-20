package com.lovesoft.androger.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;


public class LogFilterByStringInLine implements LogFilter {
	private String key;

	public LogFilterByStringInLine(String textFilter) {
		this.key = textFilter;
	}

	@Override
	public LogString filter(LogString logString) {
		BufferedReader br = new BufferedReader(new StringReader(logString.getString()));
		LogString outLog = filter(br);
		return outLog;
	}

	private LogString filter(BufferedReader br) {
		LogString outLog = new LogString();
		String line;
		try {
			line = br.readLine();
			while (line != null) {
				boolean found = false;
				if(line.contains(key)) {
					outLog.append(line);
					found = true;
				}
				line = br.readLine();
				if(found && line != null) {
					outLog.append("\n");					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return outLog;
	}

	@Override
	public String filter(String currentText) {
		LogString outLog = filter( new BufferedReader(new StringReader(currentText)));
		return outLog.getString();
	}

}
