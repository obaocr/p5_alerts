package com.safetynet.p5_alerts.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
/*
 * Controller tests with MockMvc
 */
public class MainControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void getCommunityEmailsShouldReturnEmails() throws Exception {
		this.mockMvc.perform(get("/communityEmail").param("city", "Culver").characterEncoding("utf-8")).andDo(print())
				.andExpect(status().isOk()).andReturn();
	}

	@Test
	void getCommunityEmailsShouldReturnisNotFound() throws Exception {
		this.mockMvc.perform(get("/communityEmail").param("city", "xxzz").characterEncoding("utf-8")).andDo(print())
				.andExpect(status().isNotFound()).andReturn();
	}

	@Test
	void getCommunityEmailsShouldReturnisBadRequest() throws Exception {
		this.mockMvc.perform(get("/communityEmail")).andDo(print()).andExpect(status().isBadRequest()).andReturn();
	}

	@Test
	void childAlertShouldReturnchildAlerts() throws Exception {
		this.mockMvc.perform(get("/childAlert").param("address", "1509 Culver St").characterEncoding("utf-8"))
				.andDo(print()).andExpect(status().isOk()).andReturn();
	}

	@Test
	void childAlertShouldReturnchildKO() throws Exception {
		this.mockMvc.perform(get("/childAlert").param("address", "").characterEncoding("utf-8")).andDo(print())
				.andExpect(status().isBadRequest()).andReturn();
	}

	@Test
	void personInfoShouldReturnpersonInfos() throws Exception {
		this.mockMvc.perform(
				get("/personInfo").param("lastName", "Boyd").param("firstName", "John").characterEncoding("utf-8"))
				.andDo(print()).andExpect(status().isOk()).andReturn();
	}

	@Test
	void personInfoShouldReturnKO() throws Exception {
		this.mockMvc.perform(get("/personInfo").param("lastName", "").param("firstName", "").characterEncoding("utf-8"))
				.andDo(print()).andExpect(status().isBadRequest()).andReturn();
	}

	@Test
	void firestationPersonShouldReturnPersonForFirestations() throws Exception {
		this.mockMvc.perform(get("/firestation").param("stationNumber", "3").characterEncoding("utf-8")).andDo(print())
				.andExpect(status().isOk()).andReturn();
	}

	@Test
	void firestationPersonShouldReturnKO() throws Exception {
		this.mockMvc.perform(get("/firestation").param("stationNumber", "").characterEncoding("utf-8")).andDo(print())
				.andExpect(status().isBadRequest()).andReturn();
	}

	@Test
	void phoneAlertPersonShouldReturnPhones() throws Exception {
		this.mockMvc.perform(get("/phoneAlert").param("stationNumber", "3").characterEncoding("utf-8")).andDo(print())
				.andExpect(status().isOk()).andReturn();
	}

	@Test
	void phoneAlertPersonShouldReturnKO() throws Exception {
		this.mockMvc.perform(get("/phoneAlert").param("stationNumber", "").characterEncoding("utf-8")).andDo(print())
				.andExpect(status().isBadRequest()).andReturn();
	}

	@Test
	void fireShouldReturnPersonForFirestationAddress() throws Exception {
		this.mockMvc.perform(get("/fire").param("address", "1509 Culver St").characterEncoding("utf-8")).andDo(print())
				.andExpect(status().isOk()).andReturn();
	}

	@Test
	void fireShouldReturnKO() throws Exception {
		this.mockMvc.perform(get("/fire").param("address", "").characterEncoding("utf-8")).andDo(print())
				.andExpect(status().isBadRequest()).andReturn();
	}

	@Test
	void floodStationsShouldReturnHouseholdResponse() throws Exception {
		this.mockMvc.perform(get("/flood/stations").param("stations", "1,3").characterEncoding("utf-8")).andDo(print())
				.andExpect(status().isOk()).andReturn();
	}

	@Test
	void floodStationsShouldReturnKO() throws Exception {
		this.mockMvc.perform(get("/flood/stations").param("stations", "").characterEncoding("utf-8")).andDo(print())
				.andExpect(status().isBadRequest()).andReturn();
	}
}
