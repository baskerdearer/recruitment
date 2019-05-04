package com.heavenhr.recruitment.offerapplication;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UniqueCandidateOffer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UniqueCandidateOffer() {
		// Do Nothing.
	}
	
	@Column(name="offer_id")
	private Long offerID;
	
	@Column(name="email_id")
	private String emailId;

	public Long getOfferID() {
		return offerID;
	}

	public void setOfferID(Long offerID) {
		this.offerID = offerID;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emailId == null) ? 0 : emailId.hashCode());
		result = prime * result + ((offerID == null) ? 0 : offerID.hashCode());
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
		UniqueCandidateOffer other = (UniqueCandidateOffer) obj;
		if (emailId == null) {
			if (other.emailId != null)
				return false;
		} else if (!emailId.equals(other.emailId))
			return false;
		if (offerID == null) {
			if (other.offerID != null)
				return false;
		} else if (!offerID.equals(other.offerID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UniqueCandidateOffer [offerID=" + offerID + ", emailId="
				+ emailId + "]";
	}
	
}
