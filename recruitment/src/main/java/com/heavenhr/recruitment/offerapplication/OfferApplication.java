package com.heavenhr.recruitment.offerapplication;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.heavenhr.recruitment.offer.Offer;

@Entity
@Table(name = "OFFER_APPLICATION")
public class OfferApplication implements Serializable{

	public OfferApplication() {
		// Do Nothing.
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@EmbeddedId
	private UniqueCandidateOffer uniqueCandidateOffer;
	
	private String resumeText;
	private String status;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}	
	
	public String getResumeText() {
		return resumeText;
	}
	public void setResumeText(String resumeText) {
		this.resumeText = resumeText;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;		
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((resumeText == null) ? 0 : resumeText.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		OfferApplication other = (OfferApplication) obj;		
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;		
		if (resumeText == null) {
			if (other.resumeText != null)
				return false;
		} else if (!resumeText.equals(other.resumeText))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "OfferApplication [id=" + id 
				+ ", resumeText=" + resumeText
				+ ", status=" + status + "]";
	}
	
}
