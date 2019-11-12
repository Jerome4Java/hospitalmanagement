package com.cognizant.mylearning.hospitalclient.exception;

public class HospitalNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1697893423481186866L;

	public HospitalNotFoundException(String message) {
		super(message);
	}

}
