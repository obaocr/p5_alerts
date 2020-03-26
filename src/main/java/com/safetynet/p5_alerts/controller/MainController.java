package com.safetynet.p5_alerts.controller;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.classic.Logger;

@RestController
public class MainController {
	Logger log = (Logger) LoggerFactory.getLogger(PersonController.class);
}
