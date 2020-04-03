package com.safetynet.p5_alerts.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.p5_alerts.model.FireStation;
import com.safetynet.p5_alerts.service.FireStationService;

@SpringBootTest
@AutoConfigureMockMvc
/*
 * Controller tests with MockMvc
 */
public class FireStationMockMvcTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private FireStationService fireStationService;

	@Test
	// Post FireStation
	void postPersonShouldReturnOK() throws Exception {
		FireStation fireStation = new FireStation();
		fireStation.setAddress("address1");
		fireStation.setStation("100");
		fireStationService.addFireStation(fireStation);
		//
		ObjectMapper objectMapper = new ObjectMapper();
		String personJSON = objectMapper.writeValueAsString(fireStation);
		//
		this.mockMvc.perform(post("/firestation").contentType(MediaType.APPLICATION_JSON).content(personJSON))
				.andDo(print()).andExpect(status().isOk()).andReturn();
	}

	@Test
	// Put FireStation
	void putPersonShouldReturnOK() throws Exception {
		FireStation fireStation = new FireStation();
		fireStation.setAddress("address1");
		fireStation.setStation("100");
		fireStationService.addFireStation(fireStation);
		//
		fireStation.setAddress("address1");
		ObjectMapper objectMapper = new ObjectMapper();
		String personJSON = objectMapper.writeValueAsString(fireStation);
		//
		this.mockMvc.perform(put("/firestation").contentType(MediaType.APPLICATION_JSON).content(personJSON))
				.andDo(print()).andExpect(status().isOk()).andReturn();
	}

	@Test
	// Put FireStation
	void deletePersonShouldReturnOK() throws Exception {
		FireStation fireStation = new FireStation();
		fireStation.setAddress("address1");
		fireStation.setStation("100");
		fireStationService.addFireStation(fireStation);
		//
		ObjectMapper objectMapper = new ObjectMapper();
		String personJSON = objectMapper.writeValueAsString(fireStation);
		//
		this.mockMvc.perform(delete("/firestation").contentType(MediaType.APPLICATION_JSON).content(personJSON))
				.andDo(print()).andExpect(status().isOk()).andReturn();
	}
}
