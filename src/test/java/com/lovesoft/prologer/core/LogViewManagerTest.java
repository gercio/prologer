package com.lovesoft.prologer.core;

import org.junit.Assert;
import org.junit.Test;

import com.lovesoft.androger.core.LogFilter;
import com.lovesoft.androger.core.LogFilterByStringInLine;
import com.lovesoft.androger.core.LogID;
import com.lovesoft.androger.core.LogString;
import com.lovesoft.androger.core.LogView;
import com.lovesoft.androger.core.LogViewManager;

public class LogViewManagerTest {

	@Test
	public void testSimple() {
		FakeLogTextView textView = new FakeLogTextView();
		LogViewManager adapter = new LogViewManager(textView);

		String expected = "Fiki miki!";
		LogString data = new LogString(expected);
		adapter.logObserved(data);
		Assert.assertEquals(expected, textView.getText());
		Assert.assertEquals(0, textView.viewPostion);
	}

	@Test
	public void testToMuchData() {
		FakeLogTextView textView = new FakeLogTextView();
		LogViewManager adapter = new LogViewManager(textView);

		String s = "Fiki miki!Holly molly\n crap!";
		LogString data = new LogString(s);
		adapter.logObserved(data);
		Assert.assertEquals("lly\n crap!", textView.getText());
		Assert.assertEquals(4, textView.viewPostion);
	}

	@Test
	public void testToMuchDataMultiple() {
		FakeLogTextView textView = new FakeLogTextView();
		LogViewManager adapter = new LogViewManager(textView);

		String s = "Fiki miki!Holly molly crap!";
		LogString data = new LogString(s);
		adapter.logObserved(data);

		data.setString("this it.");
		adapter.logObserved(data);

		Assert.assertEquals("p!this it.", textView.getText());
		Assert.assertEquals(0, textView.viewPostion); // 0 because only one line is displayed
	}

	@Test
	public void testWithFilter() {
		FakeLogTextView textView = new FakeLogTextView(1000);
		LogViewManager viewManager = new LogViewManager(textView);
		LogFilter filter = new LogFilterByStringInLine("Key");
		viewManager.turnOnFilter(filter);

		String s = "Fiki miki!\nHolly molly crap!\nThis is Key\n";
		LogString data = new LogString(s);
		viewManager.logObserved(data);

		data.setString("this it.");
		viewManager.logObserved(data);

		Assert.assertEquals("This is Key", textView.getText()); // only this line should be visible
	}
	
	@Test
	public void testDisableFilter() {
		FakeLogTextView textView = new FakeLogTextView(1000);
		LogViewManager viewManager = new LogViewManager(textView);
		LogFilter filter = new LogFilterByStringInLine("Key");
		viewManager.turnOnFilter(filter);

		String s = "Fiki miki!\nHolly molly crap!\nThis is Key\n";
		LogString data = new LogString(s);
		viewManager.logObserved(data);

		data.setString("this it.");
		viewManager.logObserved(data);

		
		// Disable filter
		viewManager.turnOnFilter(null);
		Assert.assertEquals("Fiki miki!\nHolly molly crap!\nThis is Key\nthis it.", textView.getText()); // only this line should be visible
	}
	
	@Test
	public void testPosition() {
		FakeLogTextView textView = new FakeLogTextView(50);
		LogViewManager adapter = new LogViewManager(textView);

		String expected = "before\nafter";
		LogString data = new LogString(expected);
		adapter.logObserved(data);
		Assert.assertEquals(7, textView.getViewPostion());
	}

	private class FakeLogTextView implements LogView {
		private int maxSize = 10;
		private String text = "";
		private int viewPostion = 0;

		public FakeLogTextView() {
		}
		

		public FakeLogTextView(int maxSize) {
			super();
			this.maxSize = maxSize;
		}


		@Override
		public String getText() {
			return text;
		}

		@Override
		public int getMaxTextFieldSize() {
			return maxSize;
		}

		@Override
		public void appendText(String newText) {
			text += newText;

		}

		@Override
		public void setViewAtPosition(int i) {
			viewPostion = i;

		}

		@Override
		public void setText(String newText) {
			text = newText;
		}


		public int getViewPostion() {
			return viewPostion;
		}


		@Override
		public int getNumberOfVisibleRows() {
			return 1;
		}


		@Override
		public LogID getLogID() {
			return null;
		}
	}
}
