package com.lovesoft.androger.core.storage;

import java.io.FileReader;
import java.io.FileWriter;

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Save and load application state to disk (XML file)
 * @author Patryk 2014-10-01
 * 
 */
public class ApplicationStateStorage {
	private String filePath;
	public ApplicationStateStorage(String filePath) {
		this.filePath = filePath;
	}

	public void save(ApplicationState as) throws Exception {
		// Create a File to marshal to
		FileWriter writer = new FileWriter(filePath);
		ApplicationStateDTO asDto = new ApplicationStateDTO(as);
		Marshaller.marshal(asDto, writer);
	}

	public ApplicationState load() throws Exception {
		FileReader reader = new FileReader(filePath);
		ApplicationStateDTO asDto = (ApplicationStateDTO) Unmarshaller.unmarshal(ApplicationStateDTO.class, reader);
		return asDto.toAplicationState();
	}

}
