package com.safetynet.p5_alerts.service;

import java.util.List;

import com.safetynet.p5_alerts.model.ChildAlert;
import com.safetynet.p5_alerts.model.FirestationPerson;
import com.safetynet.p5_alerts.model.PersonInfo;

public interface MainService {

	public List<String> getCommunityEmails(String city);

	public List<PersonInfo> personInfo(String lastName, String firstName);

	public List<ChildAlert> childAlert(String address);

	public FirestationPerson firestation(String address);

}
