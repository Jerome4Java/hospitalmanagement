package com.cognizant.mylearning.hospitalfrontdesk.exception;

public class HospitalNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -8422752046706044212L;

	public HospitalNotFoundException(String message) {
		super(message);
	}

}
