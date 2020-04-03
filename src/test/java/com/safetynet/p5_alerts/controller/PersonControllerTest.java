package com.safetynet.p5_alerts.controller;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.safetynet.p5_alerts.model.Person;
import com.safetynet.p5_alerts.service.MainService;

@ContextConfiguration
@SpringBootTest
class PersonControllerTest {
	
	
	/*@Test
	void checkInputPerson() {
		Person person = new Person();
		person.setFirstname("firstname-test");
		PersonController personController = new PersonController();
		Throwable exception = assertThrows(EntityIllegalArgumentException.class,
				() -> personController.addPerson(person));
		assertTrue(exception.getMessage().contains("No emails found for the city"));
		personController.addPerson(person);
	}*/

}
