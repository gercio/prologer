package com.lovesoft.prologer.core;


import org.junit.Assert;
import org.junit.Test;

import com.lovesoft.androger.core.LogFilterByStringInLine;

public class LogFilterByStringInLineTest {

	@Test
	public void testSimple() {
		LogFilterByStringInLine filter = new LogFilterByStringInLine("key");
		Assert.assertEquals("key", filter.filter("Hej\nkey"));
	}
	@Test
	public void testMultipleRows() {
		LogFilterByStringInLine filter = new LogFilterByStringInLine("key");
		Assert.assertEquals("key is here\nkey", filter.filter("Hej\nkey is here\nhello\nkey"));
	}
}
