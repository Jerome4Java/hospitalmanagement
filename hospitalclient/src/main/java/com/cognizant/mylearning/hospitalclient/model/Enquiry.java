package com.cognizant.mylearning.hospitalclient.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Enquiry {
	
	private String type;
	private String name;
	private String availableDay;
	private String availableTime;
	private String isAvailable;
	private String hospitalId;

}
