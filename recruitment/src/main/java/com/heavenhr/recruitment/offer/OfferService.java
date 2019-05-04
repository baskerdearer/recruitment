package com.heavenhr.recruitment.offer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferService {
	
	@Autowired
	OfferRepository offerRepository;
	
	public Offer save(Offer offer) {
		return offerRepository.save(offer);
	}
	
	public Optional<Offer> get(Long offerId) {
		return offerRepository.findById(offerId);
	}

	public List<Offer> getOffers() {
		return offerRepository.findAll();
	}
}
