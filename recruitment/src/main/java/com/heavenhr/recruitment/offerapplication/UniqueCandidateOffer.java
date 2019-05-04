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
	
	@Column(name="ID")
	private Long id;
	
	@Column(name="OFFER_ID")
	private Long offerID;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOfferID() {
		return offerID;
	}

	public void setOfferID(Long offerID) {
		this.offerID = offerID;
	}	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		if (offerID == null) {
			if (other.offerID != null)
				return false;
		} else if (!offerID.equals(other.offerID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UniqueCandidateOffer [offerID=" + offerID + "]";
	}
	
}
