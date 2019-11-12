package com.cognizant.mylearning.hospitalfrontdesk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class HospitalFrontDeskApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalFrontDeskApplication.class, args);
	}

}
