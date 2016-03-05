package com.lovesoft.androger;

import java.awt.Dimension;
import java.io.File;

import com.lovesoft.androger.tools.StringCutter;

public class Setup {
	private static final long PAUSE_TIME_IN_MS = 500;
	private static final int MAX_TEXT_FIELD_SIZE = (int) (1024 * 1024 * 3d);
	private static final int FILE_CHUNK_SIZE = MAX_TEXT_FIELD_SIZE;
	private static final int MAX_VISIBLE_DATASOURCE_NAME = 60;
	private static String iconDirectory = "icons";
	private static String RESOURCE_FOLDER = "src/main/resources/";
	private static String CONFIGURATION_FILE_PATH = System.getProperty("user.dir") +File.separator +"androger_configuration.xml";
	private static String WELCOME_FILE_NAME = "welcome.txt";
	
	public static Dimension IMAGE_SIZE_SMALL = new Dimension(10,10);
	public static Dimension IMAGE_SIZE_NORMAL = new Dimension(32,32);
	public static Dimension IMAGE_SIZE_LARGE = new Dimension(64,64);
	
	private static int guiOffset = 8;
	
	private static final StringCutter guiStringCutter = new StringCutter(MAX_VISIBLE_DATASOURCE_NAME);
	
	public static int getMaxTextFieldSize() {
		return MAX_TEXT_FIELD_SIZE;
	}
	public static long getPauseTimeInMs() {
		return PAUSE_TIME_IN_MS;
	}
	public static int getFileChunkSize() {
		return FILE_CHUNK_SIZE;
	}
	public static String getApplicationTitle() {
		return "Androger v1.2";
	}
	public static String getConfigurationFilePath() {
		return CONFIGURATION_FILE_PATH;
	}
	public static StringCutter getGuistringCutter() {
		return guiStringCutter;
	}
	public static String getIconDirectory() {
		return iconDirectory;
	}
	public static int getGuiOffset() {
		return guiOffset;
	}
	public static String getResourceFolder() {
		return RESOURCE_FOLDER;
	}
	public static String getWelcomeFileName() {
		return WELCOME_FILE_NAME;
	}
}
