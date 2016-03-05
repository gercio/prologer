package com.lovesoft.prologer.tools;

import org.junit.Assert;
import org.junit.Test;

import com.lovesoft.androger.Setup;
import com.lovesoft.androger.tools.ResourceLoader;

public class ResourceLoaderTest {
	
	@Test
	public void testIt() throws Exception {
		byte[] is = ResourceLoader.loadFileResource("icons/add.png");
		Assert.assertNotNull(is);
		is = ResourceLoader.loadFileResource(Setup.getWelcomeFileName());
		Assert.assertNotNull(is);
	}

}
