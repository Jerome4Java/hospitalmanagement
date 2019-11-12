package com.cognizant.mylearning.hospitalfrontdesk.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.cognizant.mylearning.hospitalfrontdesk.constants.ApplicationConstants;
import com.cognizant.mylearning.hospitalfrontdesk.exception.BedsNotAvailableException;
import com.cognizant.mylearning.hospitalfrontdesk.exception.HospitalNotFoundException;
import com.cognizant.mylearning.hospitalfrontdesk.model.ApplicationConfiguration;
import com.cognizant.mylearning.hospitalfrontdesk.model.Enquiry;

@Service
public class SpecialistService {
	
	private static final Logger logger = LogManager.getLogger(SpecialistService.class);

	private Map<String, String> hospitalInfo;
	
	@Autowired
	ApplicationConfiguration enquiryConfiguration;

	@PostConstruct
	public void init() {

		hospitalInfo = new HashMap<>();

		hospitalInfo.put("appollo", "946");
		hospitalInfo.put("global", "947");
		hospitalInfo.put("kamachi", "948");
		hospitalInfo.put("chettinad", "949");
		hospitalInfo.put("kaveri", "950");
		
		logger.debug("Hospital details initialized : {}", () -> hospitalInfo);
		
	}
	
	@PreDestroy
	public void destroy() {
		hospitalInfo.clear();
		logger.debug("Hospital details cleared : {}", () -> hospitalInfo);
	   }

	@Cacheable("specialist")
	public List<Enquiry> getSpecialistDetails(String hospitalName, String specialistType) {

		String hospitalId = validateHospitalName(hospitalName);

		List<Enquiry> spNewList = enquiryConfiguration.getEnquiryList().stream().filter(type -> specialistType.equals(type.getType()))
				.filter(type -> (hospitalId.equals(type.getHospitalId()))).collect(Collectors.toList());

		return spNewList;

	}

	public String getBedsAvailability(String hospitalName) {
		
		validateHospitalName(hospitalName);
		
		long count = enquiryConfiguration.getAdmissionList().stream().filter((admission) -> hospitalName.equals(admission.getHospitalName())
				&& ApplicationConstants.DISCHARGE.equals(admission.getPatientStatus())).count();
		if(count== 0) {
			logger.error("beds are not available for hospital : {}", () -> hospitalName);
			throw new BedsNotAvailableException(ApplicationConstants.BEDS_NOT_AVAILABLE + hospitalName);
		}
		
		return ApplicationConstants.BEDS_AVAILABLE + count;

	}
	
	private String validateHospitalName(String hospitalName) {
		return hospitalInfo.entrySet().stream()
				.filter(hospital -> hospitalName.equals(hospital.getKey()))
				.map(Map.Entry::getValue)
				.findFirst()
				.orElseThrow(() -> new HospitalNotFoundException(
						ApplicationConstants.HOSPITAL_NOT_AVAILABLE + hospitalName));
	}

}
