package com.safetynet.p5_alerts.service;

import java.util.List;

import com.safetynet.p5_alerts.model.ChildAlert;
import com.safetynet.p5_alerts.model.CommunityEmail;
import com.safetynet.p5_alerts.model.FirestationPerson;
import com.safetynet.p5_alerts.model.PersonForFirestationAddress;
import com.safetynet.p5_alerts.model.PersonInfo;
import com.safetynet.p5_alerts.model.PhoneAlert;

public interface MainService {

	public CommunityEmail getCommunityEmails(String city);

	public List<PersonInfo> personInfo(String lastName, String firstName);

	public List<ChildAlert> childAlert(String address);

	public FirestationPerson firestation(String address);

	public PhoneAlert phoneAlert(String firestation_number);

	public List<PersonForFirestationAddress> fire(String address);

}
