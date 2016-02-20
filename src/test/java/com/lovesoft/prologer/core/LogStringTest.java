package com.lovesoft.prologer.core;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.lovesoft.androger.core.LogString;

public class LogStringTest {
	
	private LogString logString;
	
	@Before
	public void before() {
		logString = new LogString();
	}


	@Test
	public void testSetString() {
		String s = "This is Sparta!";
		logString.append(s);
		
		String s2 = "So nice!";
		logString.setString(s2);
		Assert.assertEquals(s2, logString.toString());
	}

	@Test
	public void testAppendLogData() {
		String s = "This is Sparta!";
		logString.append(s);
		
		String s2 = "Holly Molly!";
		LogString logData2 = new LogString(); 
		logData2.append(s2);
		
		logString.append(logData2);
		Assert.assertEquals(s+s2, logString.toString());
		
	}

	@Test
	public void testGetSize() {
		Assert.assertEquals(0, logString.getSize());
	}

	@Test
	public void testAddString() {
		String s = "This is Sparta!";
		logString.append(s);
		Assert.assertEquals(s, logString.toString());
		
		String s2 = ". Nope it is not!";
		logString.append(s2);
		s+=s2;
		Assert.assertEquals(s, logString.toString());
	}


	@Test
	public void testGetString() {
		String s = "This is Sparta!";
		logString.append(s);
		
		String subString = logString.getSubString(0, 1);
		Assert.assertEquals("T", subString);
		subString = logString.getSubString(1, 2);
		Assert.assertEquals("h", subString);
	}

}
