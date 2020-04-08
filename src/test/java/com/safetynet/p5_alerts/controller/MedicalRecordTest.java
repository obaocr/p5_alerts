package com.safetynet.p5_alerts.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.p5_alerts.model.MedicalRecord;
import com.safetynet.p5_alerts.service.MedicalRecordService;

@SpringBootTest
@AutoConfigureMockMvc
/*
 * Controller tests with MockMvc
 */
public class MedicalRecordTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private MedicalRecordService medicalRecordService;

	@Test
	// Post MedicalRecord
	void postPersonShouldReturnOK() throws Exception {
		MedicalRecord medicalRecord = new MedicalRecord();
		medicalRecord.setFirstname("Olivier");
		medicalRecord.setLastname("Dupont");
		Date birthDate = new SimpleDateFormat("dd/MM/yyyy").parse("12/12/2010");
		medicalRecord.setBirthDate(birthDate);
		List<String> medications = new ArrayList<>();
		medications.add("Aspirine");
		medicalRecord.setMedications(medications);
		List<String> allergies = new ArrayList<>();
		medicalRecord.setAllergies(allergies);
		allergies.add("milk");
		medicalRecordService.addMedicalRecord(medicalRecord);
		//
		ObjectMapper objectMapper = new ObjectMapper();
		String medicalRecordJSON = objectMapper.writeValueAsString(medicalRecord);
		//
		this.mockMvc.perform(post("/medicalrecord").contentType(MediaType.APPLICATION_JSON).content(medicalRecordJSON))
				.andDo(print()).andExpect(status().isOk()).andReturn();
	}
	
	@Test
	// Post MedicalRecord
	void postPersonShouldReturnKO() throws Exception {
		MedicalRecord medicalRecord = new MedicalRecord();
		medicalRecord.setFirstname("");
		medicalRecord.setLastname("");
		Date birthDate = new SimpleDateFormat("dd/MM/yyyy").parse("12/12/2010");
		medicalRecord.setBirthDate(birthDate);
		List<String> medications = new ArrayList<>();
		medications.add("Aspirine");
		medicalRecord.setMedications(medications);
		List<String> allergies = new ArrayList<>();
		medicalRecord.setAllergies(allergies);
		allergies.add("milk");
		medicalRecordService.addMedicalRecord(medicalRecord);
		//
		ObjectMapper objectMapper = new ObjectMapper();
		String medicalRecordJSON = objectMapper.writeValueAsString(medicalRecord);
		//
		this.mockMvc.perform(post("/medicalrecord").contentType(MediaType.APPLICATION_JSON).content(medicalRecordJSON))
				.andDo(print()).andExpect(status().isBadRequest()).andReturn();
	}

	@Test
	// Put MedicalRecord
	void putPersonShouldReturnOK() throws Exception {
		MedicalRecord medicalRecord = new MedicalRecord();
		medicalRecord.setFirstname("Olivier");
		medicalRecord.setLastname("Dupont");
		Date birthDate = new SimpleDateFormat("dd/MM/yyyy").parse("12/12/2010");
		medicalRecord.setBirthDate(birthDate);
		List<String> medications = new ArrayList<>();
		medications.add("Aspirine");
		medicalRecord.setMedications(medications);
		List<String> allergies = new ArrayList<>();
		medicalRecord.setAllergies(allergies);
		allergies.add("milk");
		medicalRecordService.addMedicalRecord(medicalRecord);
		//
		medicalRecord.setAllergies(null);
		ObjectMapper objectMapper = new ObjectMapper();
		String medicalRecordJSON = objectMapper.writeValueAsString(medicalRecord);
		//
		this.mockMvc.perform(put("/medicalrecord").contentType(MediaType.APPLICATION_JSON).content(medicalRecordJSON))
				.andDo(print()).andExpect(status().isOk()).andReturn();
	}

	@Test
	// Delete MedicalRecord
	void deletePersonShouldReturnOK() throws Exception {
		MedicalRecord medicalRecord = new MedicalRecord();
		medicalRecord.setFirstname("Olivier");
		medicalRecord.setLastname("Dupont");
		Date birthDate = new SimpleDateFormat("dd/MM/yyyy").parse("12/12/2010");
		medicalRecord.setBirthDate(birthDate);
		List<String> medications = new ArrayList<>();
		medications.add("Aspirine");
		medicalRecord.setMedications(medications);
		List<String> allergies = new ArrayList<>();
		medicalRecord.setAllergies(allergies);
		allergies.add("milk");
		medicalRecordService.addMedicalRecord(medicalRecord);
		//
		ObjectMapper objectMapper = new ObjectMapper();
		String medicalRecordJSON = objectMapper.writeValueAsString(medicalRecord);
		//
		this.mockMvc.perform(delete("/medicalrecord").contentType(MediaType.APPLICATION_JSON).content(medicalRecordJSON))
				.andDo(print()).andExpect(status().isOk()).andReturn();
	}
}
