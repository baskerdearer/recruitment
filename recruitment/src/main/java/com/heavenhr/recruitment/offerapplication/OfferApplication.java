package com.heavenhr.recruitment.offerapplication;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@ManyToOne
	@JsonIgnore
    @JoinColumn(name="OFFER_ID", nullable=false)
	private Offer offerId;
	
	@Column(name="EMAIL_ID")
	private String emailId;
	
	@Column(name="RESUME_TEXT")
	private String resumeText;
	
	@Column(name="STATUS")
	@Enumerated(EnumType.STRING)
	private ApplicationStatus status;
	
	public void setStatus(ApplicationStatus status) {
		this.status = status;
	}
	
	public void setResumeText(String resumeText) {
		this.resumeText = resumeText;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;		
	//	result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((resumeText == null) ? 0 : resumeText.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	public Offer getOfferId() {
		return offerId;
	}

	public void setOfferId(Offer offerId) {
		this.offerId = offerId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getResumeText() {
		return resumeText;
	}

	public ApplicationStatus getStatus() {
		return status;
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
