package com.cognizant.mylearning.hospitalfrontdesk.model;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@XmlRootElement
public class Enquiry {
	
	private String type;
	private String name;
	private String availableDay;
	private String availableTime;
	private String isAvailable;
	private String hospitalId;

}
