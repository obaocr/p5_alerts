package com.safetynet.p5_alerts.config;

import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * 
 * htttpTraceRepository
 *
 */
@Configuration
public class HttpTraceRepository {
	@Bean
	public InMemoryHttpTraceRepository htttpTraceRepository() {
		return new InMemoryHttpTraceRepository();
	}
}
