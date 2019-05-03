package com.heavenhr.recruitment.offer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferService {
	
	@Autowired
	OfferRepository offerRepository;
	
	public Offer save(Offer offer) {
		return offerRepository.save(offer);
	}
	
	public Offer get(Long offerId) {
		return offerRepository.findOne(offerId);
	}

}
