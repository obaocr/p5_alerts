package com.safetynet.p5_alerts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.safetynet.p5_alerts.service.DataService;
/**
 * 
 * CommandLineRunner to load data
 *
 */
@Component
public class ApplicationStartupRunner implements CommandLineRunner {

	@Autowired
	private DataService dataService;

	@Override
	public void run(String... args) throws Exception {
		// chargement des données
		System.out.println("MyCommandLineRunner...");
		dataService.loadData();
	}
}
