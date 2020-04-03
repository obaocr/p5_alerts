package com.safetynet.p5_alerts.service;

import java.io.IOException;

/**
 * Interface for DataService
 */
public interface DataService {

	/**
	 * Method to load data (person, firestation, medicalrecord) into java objects
	 * 
	 * @throws IOException
	 */
	void loadData() throws IOException;
}
