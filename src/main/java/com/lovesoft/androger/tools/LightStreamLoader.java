package com.lovesoft.androger.tools;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Load data from input stream into memory (byte table), and use less memory as possible
 * @author Patryk Kaluzny 2009.02.15
 *
 */
public class LightStreamLoader {
	private InputStream iStream;
	private int SIZE = 1024*10;
	public LightStreamLoader(InputStream stream) {
		super();
		iStream = new BufferedInputStream(stream,SIZE) ;
	}
	
	public byte[] load() throws IOException {
		byte[] buff = new byte[SIZE];
		ByteArrayOutputStream oStream = new ByteArrayOutputStream();
		try(BufferedOutputStream oBuffStream = new BufferedOutputStream(oStream, SIZE)) {
			int read = 0;
			while (read != -1) {
				read = iStream.read(buff);
				if (read != -1) {
					oBuffStream.write(buff, 0, read);
				}
			}
			oBuffStream.flush();
			return oStream.toByteArray();			
		}
	}
}
