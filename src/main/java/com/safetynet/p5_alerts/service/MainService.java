package com.safetynet.p5_alerts.service;

import java.util.List;

import com.safetynet.p5_alerts.model.ChildAlertResponse;
import com.safetynet.p5_alerts.model.CommunityEmail;
import com.safetynet.p5_alerts.model.FirestationPerson;
import com.safetynet.p5_alerts.model.PersonForFirestationAddress;
import com.safetynet.p5_alerts.model.PersonForFirestationAddressResponse;
import com.safetynet.p5_alerts.model.PersonInfoResponse;
import com.safetynet.p5_alerts.model.PhoneAlert;

public interface MainService {

	public CommunityEmail getCommunityEmails(String city);

	public PersonInfoResponse personInfo(String lastName, String firstName);

	public ChildAlertResponse childAlert(String address);

	public FirestationPerson firestation(String address);

	public PhoneAlert phoneAlert(String firestation_number);

	public PersonForFirestationAddressResponse fire(String address);

}
