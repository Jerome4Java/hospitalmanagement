package com.cognizant.mylearning.hospitalfrontdesk.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.mylearning.hospitalfrontdesk.constants.ApplicationConstants;
import com.cognizant.mylearning.hospitalfrontdesk.exception.SpecialistNotFoundException;
import com.cognizant.mylearning.hospitalfrontdesk.model.Appointment;
import com.cognizant.mylearning.hospitalfrontdesk.model.Specialist;

@Service
public class AppointmentService {

	private static final Logger logger = LogManager.getLogger(AppointmentService.class);

	@Autowired
	private Specialist specialist;

	public List<Appointment> getAppointmentDetails(String specialistName, String appointmentDay, String patientName) {
		if (!specialistName.equalsIgnoreCase(specialist.getSpecialistName())
				|| !appointmentDay.equalsIgnoreCase(specialist.getAvailableDay())) {
			logger.error("specialist is not available  : {}", () -> specialistName);
			throw new SpecialistNotFoundException(ApplicationConstants.SPECIALIST_NOT_AVAILABLE + specialistName);
		}

		List<Appointment> appointmentList = new ArrayList<>();
		appointmentList.add(new Appointment(specialist.getSpecialistName(), patientName, specialist.getAvailableDay(),
				specialist.getAvailableTime()));
		return appointmentList;

	}

}
