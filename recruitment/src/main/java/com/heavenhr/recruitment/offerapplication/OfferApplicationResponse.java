package com.heavenhr.recruitment.offerapplication;

import java.util.List;

public class OfferApplicationResponse {
	
	public OfferApplicationResponse() {
		// Do Nothing.
	}
	
	private Integer numberOfApplications = null;
	private List<OfferApplication> applications = null;
	public Integer getNumberOfApplications() {
		return numberOfApplications;
	}
	public void setNumberOfApplications(Integer numberOfApplications) {
		this.numberOfApplications = numberOfApplications;
	}
	public List<OfferApplication> getApplications() {
		return applications;
	}
	public void setApplications(List<OfferApplication> applications) {
		this.applications = applications;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((applications == null) ? 0 : applications.hashCode());
		result = prime
				* result
				+ ((numberOfApplications == null) ? 0 : numberOfApplications
						.hashCode());
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
		OfferApplicationResponse other = (OfferApplicationResponse) obj;
		if (applications == null) {
			if (other.applications != null)
				return false;
		} else if (!applications.equals(other.applications))
			return false;
		if (numberOfApplications == null) {
			if (other.numberOfApplications != null)
				return false;
		} else if (!numberOfApplications.equals(other.numberOfApplications))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "OfferApplicationResponse [numberOfApplications="
				+ numberOfApplications + ", applications=" + applications + "]";
	}

}
