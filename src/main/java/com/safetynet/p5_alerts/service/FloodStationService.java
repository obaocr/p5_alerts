package com.safetynet.p5_alerts.service;

import java.util.List;

import com.safetynet.p5_alerts.model.Household;

public interface FloodStationService {
	public List<Household> floodStations(List<String> stations);
}
