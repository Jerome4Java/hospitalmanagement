package com.cognizant.mylearning.hospitalfrontdesk.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@ConfigurationProperties("hospital")
@PropertySource("classpath:specialist.properties")
@Getter
public class ApplicationConfiguration {
	
	private List<Enquiry> enquiryList = new ArrayList<>();
	private List<Admission> admissionList = new ArrayList<>();
	

}
