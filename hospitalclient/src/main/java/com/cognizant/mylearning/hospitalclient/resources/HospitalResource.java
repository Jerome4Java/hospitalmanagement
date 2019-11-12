package com.cognizant.mylearning.hospitalclient.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cognizant.mylearning.hospitalclient.model.Enquiry;

@RestController
@RequestMapping("/hospital")
public class HospitalResource {

	@Value("${service.url}")
	private String serviceUrl;

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping(value = "/client/{hospitalName}/{type}")
	public ResponseEntity<List<Enquiry>> enquireSpecialistDetails(@PathVariable String hospitalName,
			@PathVariable String type) {

		ResponseEntity<List<Enquiry>> specialistResponse = restTemplate.exchange(serviceUrl + hospitalName + "/" + type,
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Enquiry>>() {
				});
		List<Enquiry> enquiryList = specialistResponse.getBody();
		if (enquiryList.isEmpty()) {
			return new ResponseEntity<List<Enquiry>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Enquiry>>(enquiryList, HttpStatus.OK);

	}

}
