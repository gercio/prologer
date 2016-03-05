package com.lovesoft.androger.core.datasource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.lovesoft.androger.core.LogID;
import com.lovesoft.androger.tools.ResourceLoader;

public class DataSourceResource extends DataSourceInputStream {

	private byte[] loadedResource;
	public DataSourceResource(LogID inputFileName) {
		super(inputFileName);
		loadedResource = ResourceLoader.loadFileResource(inputFileName.getFilePath());
	}

	@Override
	protected long getCurrentLenght() {
		return loadedResource.length;
	}

	@Override
	protected InputStream createInputStream(LogID logID) throws IOException {
		return new ByteArrayInputStream(loadedResource);
	}

}
