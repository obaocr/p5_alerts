package com.safetynet.p5_alerts.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class to calculateAge
 */
public class Utils {
	
	private static final Logger log = LogManager.getLogger(Utils.class);
	
	public static int calculateAgeFromBirthDate(Date birthDate) {
		
		if (birthDate != null) {
			LocalDate localDate = LocalDate.now();
			// Getting the default zone id
			ZoneId defaultZoneId = ZoneId.systemDefault();
			// Converting the date to Instant
			Instant instant = birthDate.toInstant();
			// Converting the Date to LocalDate
			LocalDate birthLocalDate = instant.atZone(defaultZoneId).toLocalDate();
			return Period.between(birthLocalDate, localDate).getYears();
		}
		else {
			log.error("CalculateAgeFromBirthDate : Date de naissance nulle");
			return 0;
		}
	}
}
