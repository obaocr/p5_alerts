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
import com.safetynet.p5_alerts.model.Person;
import com.safetynet.p5_alerts.model.PersonForAPIDelete;
import com.safetynet.p5_alerts.service.PersonService;

@SpringBootTest
@AutoConfigureMockMvc
/*
 * Controller tests with MockMvc 
 */
public class PersonControllerMockMvcTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private PersonService personService;
	
	@Test
	// Post Person
	void postPersonShouldReturnOK() throws Exception {
		Person.Builder personlBuilder = new Person.Builder();
		Person p = personlBuilder.setFirstname("Olivier").setLastname("Martin").setAddress("address").setCity("city")
				.setZip("zip").setPhone("phone").setEmail("email").build();
		//
		ObjectMapper objectMapper = new ObjectMapper();
		String personJSON = objectMapper.writeValueAsString(p);
		//
		this.mockMvc.perform(post("/person").contentType(MediaType.APPLICATION_JSON).content(personJSON)).andDo(print())
				.andExpect(status().isOk()).andReturn();
	}
	
	@Test
	// Put Person
	void putPersonShouldReturnOK() throws Exception {
		Person.Builder personlBuilder = new Person.Builder();
		Person p = personlBuilder.setFirstname("Olivier").setLastname("Martin").setAddress("address").setCity("city")
				.setZip("zip").setPhone("phone").setEmail("email").build();
		personService.addPerson(p);
		//
		p.setAddress("25 Rue des acacias");
		ObjectMapper objectMapper = new ObjectMapper();
		String personJSON = objectMapper.writeValueAsString(p);
		//
		this.mockMvc.perform(put("/person").contentType(MediaType.APPLICATION_JSON).content(personJSON)).andDo(print())
				.andExpect(status().isOk()).andReturn();
	}
	
	@Test
	// Put Person
	void deletePersonShouldReturnOK() throws Exception {
		Person.Builder personlBuilder = new Person.Builder();
		Person p = personlBuilder.setFirstname("Olivier").setLastname("Martin").setAddress("address").setCity("city")
				.setZip("zip").setPhone("phone").setEmail("email").build();
		personService.addPerson(p);
		//
		PersonForAPIDelete personForAPIDelete = new PersonForAPIDelete();
		personForAPIDelete.setFirstname("Olivier");
		personForAPIDelete.setLastname("Martin");
		ObjectMapper objectMapper = new ObjectMapper();
		String personJSON = objectMapper.writeValueAsString(personForAPIDelete);
		//
		this.mockMvc.perform(delete("/person").contentType(MediaType.APPLICATION_JSON).content(personJSON)).andDo(print())
				.andExpect(status().isOk()).andReturn();
	}
	
}
