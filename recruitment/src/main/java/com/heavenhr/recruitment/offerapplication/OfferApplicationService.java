package com.heavenhr.recruitment.offerapplication;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heavenhr.recruitment.offer.Offer;
import com.heavenhr.recruitment.offer.OfferRepository;

@Service
public class OfferApplicationService {
	
	@Autowired
	OfferApplicationRepository offerApplicationRepository;
	
	@Autowired
	OfferRepository offerRepository;
	
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
	
	public OfferApplication update(OfferApplication offerApplication, Long offerId) {
		Optional<Offer> offer = this.offerRepository.findById(offerId);
		if(!offer.isPresent()) {
			return null;
		}
		offerApplication.setOfferId(offer.get());
		return this.offerApplicationRepository.save(offerApplication);
	}

}
