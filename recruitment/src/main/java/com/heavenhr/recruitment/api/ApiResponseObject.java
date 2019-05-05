package com.heavenhr.recruitment.api;

public class ApiResponseObject {
	
	ApiResponseObject() {
		
	}

	public ApiResponseObject(Integer statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
	}
	private Integer statusCode;
	private String message;
	public Integer getStatusCode() {
		return statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result
				+ ((statusCode == null) ? 0 : statusCode.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ApiResponseObject other = (ApiResponseObject) obj;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (statusCode == null) {
			if (other.statusCode != null)
				return false;
		} else if (!statusCode.equals(other.statusCode))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ApiResponseObject [statusCode=" + statusCode + ", message=" + message
				+ "]";
	}
}
