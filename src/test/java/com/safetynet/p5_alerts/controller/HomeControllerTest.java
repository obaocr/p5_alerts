package com.safetynet.p5_alerts.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class HomeControllerTest {

	@Autowired
	private MainController mainController;
	
	@Autowired
	FireStationController fireStationController;
	
	@Autowired
	MedicalRecordController medicalRecordController;
	
	@Autowired
	PersonController personController;

	@Test
	public void mainControllerLoads() throws Exception {
		assertThat(mainController).isNotNull();
	}
	
	@Test
	public void fireStationControllerLoads() throws Exception {
		assertThat(fireStationController).isNotNull();
	}
	
	@Test
	public void medicalRecordControllerLoads() throws Exception {
		assertThat(medicalRecordController).isNotNull();
	}
	
	@Test
	public void personControllerLoads() throws Exception {
		assertThat(personController).isNotNull();
	}

}
