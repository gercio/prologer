package com.lovesoft.androger.ui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.lovesoft.androger.Setup;
import com.lovesoft.androger.tools.LightStreamLoader;
import com.lovesoft.androger.ui.ImageHolder.ImageEnum;

public class ResourceLoader {
	public static byte[] loadFileResource(String dirName, String fileName) {
		try{
			try (InputStream stream = new FileInputStream(getPath(dirName, fileName))) {
				return convert(stream);			
			} catch (FileNotFoundException e) {
				return openFileFromJAR(dirName, fileName, e);
			}			
		} catch( IOException ex ) {
			throw new RuntimeException("Can't read file " + dirName + "/" + fileName, ex);
		}
	}

	private static byte[] openFileFromJAR(String dirName, String fileName, FileNotFoundException e) throws IOException {
		try (InputStream stream = ImageHolder.class.getResourceAsStream( getPathForJar(dirName, fileName))) {
			if(stream == null) {
				throw new RuntimeException("Can't open file " + dirName + "/" + fileName, e);
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

	private static String getPath(String dirName, String fileName) {
		return Setup.getResourceFolder() + dirName + "/" +  fileName;
	}
	
	private static String getPathForJar(String dirName, String fileName) {
		return '/' + dirName + '/' + fileName;
	}
}
