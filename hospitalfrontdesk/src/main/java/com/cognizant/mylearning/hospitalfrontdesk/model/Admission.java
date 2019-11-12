package com.cognizant.mylearning.hospitalfrontdesk.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Admission {
	
	private String hospitalName;
	private String patientName;
	private String patientStatus;

}
