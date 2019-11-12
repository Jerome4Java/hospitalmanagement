package com.cognizant.mylearning.hospitalfrontdesk.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Appointment {
	
	private String specialistName;
	private String patientName;
	private String appointmentDay;
	private String appointmentTime;

}
