package com.lovesoft.prologer.tools;

import org.junit.Assert;
import org.junit.Test;

import com.lovesoft.androger.tools.StringCutter;

public class StringCutterTest {

	@Test
	public void testIt() {
		StringCutter cutter = new StringCutter(5);
		String orginal = "123456789";
		String cut = cutter.cut(orginal);
		Assert.assertEquals("...89", cut);
	}

	@Test
	public void testNoCut() {
		StringCutter cutter = new StringCutter(5);
		String orginal = "12345";
		String cut = cutter.cut(orginal);
		Assert.assertEquals("12345", cut);
	}

	@Test
	public void testCut() {
		StringCutter cutter = new StringCutter(5);
		String orginal = "123456";
		String cut = cutter.cut(orginal);
		Assert.assertEquals("...56", cut);
	}
}
