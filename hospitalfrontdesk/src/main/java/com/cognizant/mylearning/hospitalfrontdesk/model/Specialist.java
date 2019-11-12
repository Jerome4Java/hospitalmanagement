package com.cognizant.mylearning.hospitalfrontdesk.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@PropertySource("classpath:specialist.properties")
@ConfigurationProperties
@Component
public class Specialist {
	
	private String specialistName;
	private String availableDay;
	private String availableTime;

}
