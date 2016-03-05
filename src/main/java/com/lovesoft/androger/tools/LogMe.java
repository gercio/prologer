package com.lovesoft.androger.tools;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogMe {
	private static final Logger logger = LogManager.getLogger(LogMe.class);
	
	public static void logDebug(String msg) {
		if(logger.isDebugEnabled()) {
			logger.debug(msg);			
		}
	}
	
	public static void logDebug(String msg, Throwable th) {
		if(logger.isDebugEnabled()) {
			logger.debug(msg, th);			
		}
	}
	
	public static void logInfo(String msg) {
		if(logger.isInfoEnabled()) {
			logger.info(msg);			
		}
	}
	
	public static void logError(String msg, Exception ex) {
		if(logger.isErrorEnabled()) {
			logger.error(msg, ex);	
		}
	}
	
	public static void logError(String msg) {
		if(logger.isErrorEnabled()) {
			logger.error(msg);	
		}
	}

}
