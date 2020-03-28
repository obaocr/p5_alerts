package com.safetynet.p5_alerts.util;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

class UtilsTest {

	@Test
	void CalculateAgeFromBirthDateTest() throws ParseException {
		Date birthDate = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2000");
		int age = Utils.calculateAgeFromBirthDate(birthDate);
		assertTrue(age > 18);
	}

}
