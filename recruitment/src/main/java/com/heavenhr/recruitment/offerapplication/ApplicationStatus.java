package com.heavenhr.recruitment.offerapplication;

public enum ApplicationStatus {
	APPLIED("APPLIED"), INVITED("INVITED"), REJECTED("REJECTED"), HIRED("HIRED");
	
	private String value;
	
	ApplicationStatus(
			String value){
		this.value= value;
	}
	
	public String toString(){
		return String.valueOf(this.ordinal());		
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
