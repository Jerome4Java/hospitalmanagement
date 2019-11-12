package com.cognizant.mylearning.hospitalfrontdesk.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.mylearning.hospitalfrontdesk.constants.ApplicationConstants;
import com.cognizant.mylearning.hospitalfrontdesk.exception.SpecialistNotFoundException;
import com.cognizant.mylearning.hospitalfrontdesk.model.Appointment;
import com.cognizant.mylearning.hospitalfrontdesk.model.Enquiry;
import com.cognizant.mylearning.hospitalfrontdesk.service.AppointmentService;
import com.cognizant.mylearning.hospitalfrontdesk.service.SpecialistService;

@RestController
@RequestMapping("/hospital")
public class HospitalController {
	
	private static final Logger logger = LogManager.getLogger(HospitalController.class);

	@Autowired
	private SpecialistService specialistService;

	@Autowired
	private AppointmentService appointmentService;

	@GetMapping("/enquiry/{hospitalName}/{type}")
	public List<Enquiry> specialistDetails(@PathVariable String hospitalName, @PathVariable String type) {
		List<Enquiry> availableList = specialistService.getSpecialistDetails(hospitalName, type);
		if (availableList.isEmpty()) {
			logger.error("specialist is not available  : {}", () -> type);
			throw new SpecialistNotFoundException(ApplicationConstants.SPECIALIST_NOT_AVAILABLE + type);
		}

		return availableList;
	}

	@GetMapping(value = "/appointment/{specialistName}/{appointmentDay}/{patientName}", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public List<Appointment> appointmentDetails(@PathVariable String specialistName,
			@PathVariable String appointmentDay, @PathVariable String patientName) {

		return appointmentService.getAppointmentDetails(specialistName, appointmentDay, patientName);

	}

	@GetMapping("/admission/{hospitalName}")
	public String admissionDetails(@PathVariable String hospitalName) {
		return specialistService.getBedsAvailability(hospitalName);

	}

}
