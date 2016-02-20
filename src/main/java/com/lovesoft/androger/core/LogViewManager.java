package com.lovesoft.androger.core;

import com.lovesoft.androger.LogDataObserver;

public class LogViewManager implements LogDataObserver {
	private LogView logView;
	private StringLogView logViewCopy;
	private LogFilter filter;
	
	public LogViewManager(LogView textView) {
		super();
		this.logView = textView;
	}

	@Override
	public void logObserved(LogString data) {
		synchronized (logView) {
			if(filter != null) {
				addLogToView(data, logViewCopy); // keep the original data in place
				data = filter(data); // Filter it
			}
			addLogToView(data, logView);
		}
	}

	private void addLogToView(LogString data, LogView ta) {
		final String currentText = ta.getText();
		final String newText = data.getString();
		final int newTextSize = newText.length();
		final int maxSize = ta.getMaxTextFieldSize();
		final int currentSize = currentText.length();
		
		if (newTextSize + currentSize <= maxSize) {
			// New data will fit into existing field
			ta.appendText(newText);
			setBestViewPosition(ta, currentText + newText);
		} else if (newTextSize > maxSize) {
			// Large chunk of data to display, holly molly, cut this crap!
			final int beginIndex = newTextSize - maxSize;
			String cut = "";
			if(beginIndex > -1 && beginIndex <newText.length()) {
				cut = newText.substring(beginIndex);
			}
			ta.setText(cut);
			setBestViewPosition(ta, cut);
		} else {
			// Merge new data with existing one. Cut existing one to fit new one.
			int currentStartIndex = currentSize - (maxSize - newTextSize);
			String logToDisplay = currentText.substring(currentStartIndex);
			logToDisplay +=  data.getString();
			ta.setText(logToDisplay);
			setBestViewPosition(ta, logToDisplay);
		}
	}

	private void setBestViewPosition(LogView ta, final String text) {
		int position = calulateBestViewPostion(text);
		setViewPosition(ta, position);
	}

	private int calulateBestViewPostion(String string) {
		if(string == null || string.length() == 0) {
			return 0;			
		}
		int lastNewLine = string.lastIndexOf("\n");
		if(lastNewLine == -1) {
			// New line does not exist, just scroll to the beginning of the line
			return 0;
		}
		if(lastNewLine + 1 < string.length()) {
			// Return last line first column position
			return lastNewLine + 1;
		}
		return lastNewLine;
	}

	private void setViewPosition(LogView ta, int viewPosition) {
		if(viewPosition < 0) {
			viewPosition = 0;
		}
		ta.setViewAtPosition(viewPosition);
	}

	private String filter(String string) {
		if(filter != null) {
			return filter.filter(string);
		}
		return string;
	}
	private LogString filter(LogString log) {
		if(filter != null) {
			return filter.filter(log);
		}
		return log;
	}

	public void turnOnFilter(LogFilter filter) {
		if(this.filter == filter) {
			return;
		}
		if(this.filter != null) {
			// We have filter already filter, and it will be disabled.
			// So not need to get back unfiltered text back to view.
			synchronized (logView) {
				logView.setText(logViewCopy.getText());
				logView.setViewAtPosition(logViewCopy.viewAtPosition);
			}
		}
		this.filter = filter;
		if(filter != null) {
			synchronized (logView) {
				logViewCopy = new StringLogView(logView.getMaxTextFieldSize());
				String currentText = logView.getText();
				logViewCopy.appendText(currentText);
				logViewCopy.setViewAtPosition(currentText.length());
				if(currentText != null && currentText.length() > 0) {
					logView.setText(filter(currentText));
				}
			}
		}
	}
	
	private class StringLogView implements LogView {
		private StringBuilder sb = new StringBuilder();
		private int max;
		private int viewAtPosition;
		
		public StringLogView(int max) {
			this.max = max;
		}

		@Override
		public String getText() {
			return sb.toString();
		}

		@Override
		public int getMaxTextFieldSize() {
			return max;
		}

		@Override
		public void appendText(String newText) {
			sb.append(newText);
		}

		@Override
		public void setViewAtPosition(int i) {
			viewAtPosition = i;
		}

		@Override
		public void setText(String newText) {
			sb = new StringBuilder(newText);
		}

		@Override
		public int getNumberOfVisibleRows() {
			return 0;
		}

		@Override
		public LogID getLogID() {
			return null;
		}
		
	}

}
