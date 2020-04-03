package com.safetynet.p5_alerts.service;

import java.util.List;

import com.safetynet.p5_alerts.model.HouseholdResponse;

/**
 * Interface for FloodStationService
 */
public interface FloodStationService {
	public HouseholdResponse floodStations(List<String> stations);
}
