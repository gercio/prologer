package com.lovesoft.androger.tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.lovesoft.androger.Setup;
import com.lovesoft.androger.ui.ImageHolder;
import static com.lovesoft.androger.tools.LogMe.*;

public class ResourceLoader {
	
	public static byte[] loadFileResource(String fileName) {
		try{
			String file = getPath(fileName);
			try (InputStream stream = new FileInputStream(file)) {
				return convert(stream);			
			} catch (FileNotFoundException e) {
				logDebug("File not found " + file);
				return openFileFromJAR(fileName, e);
			}			
		} catch( IOException ex ) {
			throw new RuntimeException("Can't read file " + fileName, ex);
		}
	}

	private static byte[] openFileFromJAR(String fileName, FileNotFoundException e) throws IOException {
		String file = getPathForJar( fileName);
		try (InputStream stream = ImageHolder.class.getResourceAsStream(file)) {
			if(stream == null) {
				logError("Can't load file from class path: " + file);
				throw new RuntimeException("Can't open file " + file, e);
			}	
			return convert(stream);
		}
	}
	
	private static byte[] convert(InputStream stream) {
		LightStreamLoader loader = new LightStreamLoader(stream); 
		try {
			return loader.load();
		} catch (IOException e) {
			throw new RuntimeException("Cant read file.", e);
		}
	}

	private static String getPath(String fileName) {
			return Setup.getResourceFolder() +  fileName;
	}
	
	private static String getPathForJar(String fileName) {
		return '/' + fileName;
	}
}
