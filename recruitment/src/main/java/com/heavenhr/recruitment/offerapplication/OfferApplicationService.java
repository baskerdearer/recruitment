package com.heavenhr.recruitment.offerapplication;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferApplicationService {
	
	@Autowired
	OfferApplicationRepository offerApplicationRepository;
	
	public OfferApplication save(OfferApplication offerApplication) {
		return this.offerApplicationRepository.save(offerApplication);
	}
	
	public Optional<OfferApplication> get(Long id) {
		return this.offerApplicationRepository.findById(id);
	}
	
	public List<OfferApplication> allOfferApplication() {
		return this.offerApplicationRepository.findAll();
	}
	
	public OfferApplication update(OfferApplication offerApplication) {
		return this.offerApplicationRepository.save(offerApplication);
	}

}
