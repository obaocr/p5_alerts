package com.safetynet.p5_alerts;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.safetynet.p5_alerts.service.DataService;
import com.safetynet.p5_alerts.service.DataServiceImpl;

@Component
public class ApplicationStartupRunner implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		// chargement des données
		System.out.println("MyCommandLineRunner...");
		DataService ds = new DataServiceImpl("data_test.json");
		ds.loadData();
	}
}
