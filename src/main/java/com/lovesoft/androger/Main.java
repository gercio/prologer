package com.lovesoft.androger;

import com.lovesoft.androger.core.Androger;
/**
 * ProLoger main class.
 * @author Patryk Kaluzny 2013.05.01
 *
 */
public class Main {
	public static void main(String[] args) {
		Androger loger = Androger.createProLoger(args);
		loger.start();
	}
}
