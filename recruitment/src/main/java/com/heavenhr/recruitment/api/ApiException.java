package com.heavenhr.recruitment.api;

public class ApiException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final private int code;
	
	public ApiException(int code, String msg) {
		super(msg);
		this.code = code;		
	}
	
	public int getCode() {
		return this.code;
	}
}
