package com.lovesoft.androger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lovesoft.androger.core.Androger;
/**
 * ProLoger main class.
 * @author Patryk Kaluzny 2013.05.01
 *
 */
public class Main {
	
	private static final Logger logger = LogManager.getLogger(Main.class);
	
	public static void main(String[] args) {
		logger.info("Application is starting...");
		Androger loger = Androger.createProLoger(args);
		loger.start();
	}
}
