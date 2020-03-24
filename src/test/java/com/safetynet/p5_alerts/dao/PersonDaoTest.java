package com.safetynet.p5_alerts.dao;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.safetynet.p5_alerts.service.DataService;
import com.safetynet.p5_alerts.service.DataServiceImpl;

class PersonDaoTest {
	
	@BeforeAll
	private static void initData () {
		DataService ds = new DataServiceImpl("data_test.json");
		ds.loadData();
	}
	
	@Test
	// Test de la recherche des emails des personnes d'une ville "Culver"
	void communityEmaiTtest() {
		PersonDao personDao = new PersonDaoImpl();
		List<String> emails = personDao.communityEmail("Culver");
		System.out.println("Test dans communityEmaiTtes, city : 'Culver', emails size : "  + emails.size());
		assertTrue(emails.size() == 23);
	}
	
	@Test
	// Test de la recherche des emails des personnes d'une ville inexistante
	void communityEmailEmptyTest() {
		PersonDao personDao = new PersonDaoImpl();
		List<String> emails = personDao.communityEmail("xx");
		assertTrue(emails.size() == 0);
	}

}
