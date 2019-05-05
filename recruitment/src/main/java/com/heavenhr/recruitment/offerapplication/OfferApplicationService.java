package com.heavenhr.recruitment.offerapplication;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heavenhr.recruitment.api.RecruitmentApiController;
import com.heavenhr.recruitment.offer.Offer;
import com.heavenhr.recruitment.offer.OfferRepository;

@Service
public class OfferApplicationService {
	
	@Autowired
	OfferApplicationRepository offerApplicationRepository;
	
	@Autowired
	OfferRepository offerRepository;
	
	@Autowired
	private static final Logger LOG = LoggerFactory.getLogger(OfferApplicationService.class);
	
	@Transactional
	public OfferApplication save(OfferApplication offerApplication, Long offerId) {
		Optional<Offer> offer = this.offerRepository.findById(offerId);
		if(!offer.isPresent()) {
			return null;
		}
		offerApplication.setOfferId(offer.get());
		return this.offerApplicationRepository.save(offerApplication);
	}
	
	public Optional<OfferApplication> get(Long id) {
		return this.offerApplicationRepository.findById(id);
	}
	
	public List<OfferApplication> allOfferApplication() {
		return this.offerApplicationRepository.findAll();
	}
	
	@Transactional
	public OfferApplication update(OfferApplication offerApplication, Long offerId) {
		Optional<Offer> offer = this.offerRepository.findById(offerId);
		if(!offer.isPresent()) {
			return null;
		}
		offerApplication.setOfferId(offer.get());
		Optional<OfferApplication> offerOptional = this.offerApplicationRepository.findById(offerApplication.getId());
		if(!offerOptional.isPresent()){
			return null;
		}
		ApplicationStatus previousStatus = offerOptional.get().getStatus();
		offerApplication.setEmailId(offerApplication.getEmailId() != null ? offerApplication.getEmailId() : offerOptional.get().getEmailId());
		offerApplication.setResumeText(offerApplication.getResumeText() != null ? offerApplication.getResumeText() : offerOptional.get().getResumeText());
		offerApplication.setStatus(offerApplication.getStatus() != null ? offerApplication.getStatus() : offerOptional.get().getStatus());
		offerApplication.setId(offerApplication.getId() != null ? offerApplication.getId() : offerOptional.get().getId());
		
		OfferApplication updatedOfferApplication = this.offerApplicationRepository.save(offerApplication);
		/* 
		 * Status change alert
		 */
		if(updatedOfferApplication != null && updatedOfferApplication.getStatus() != previousStatus) {
			LOG.info("\n**************************Status change Alert*********************************************************\n" +
					"Dear Candidate,\n Your application status changed from "+ previousStatus.getValue() + " to "+ updatedOfferApplication.getStatus().getValue()
					+"\n******************************************************************************************************");					
		}
		return updatedOfferApplication;
	}

}
