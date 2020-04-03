package com.safetynet.p5_alerts.config;

import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.context.annotation.Bean;

public class HttpTraceRepository {
	@Bean
	public InMemoryHttpTraceRepository htttpTraceRepository() {
		return new InMemoryHttpTraceRepository();
	}
}
