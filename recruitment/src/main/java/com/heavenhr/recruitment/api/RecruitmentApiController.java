package com.heavenhr.recruitment.api;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.heavenhr.recruitment.offer.Offer;
import com.heavenhr.recruitment.offer.OfferService;

@RestController
public class RecruitmentApiController {
	
	RecruitmentApiController() {
		// Do Nothing.
	}
	
	@Autowired
	OfferService offerService;
	
	private static final Logger LOG = LoggerFactory.getLogger(RecruitmentApiController.class);
	
	@RequestMapping(value = "/offer/{offerId}", produces = { "application/json" },
			method = RequestMethod.GET)
	public ResponseEntity<Offer> offerGet(@PathVariable("offerId") Long offerId) {		
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
				.body(offerService.get(offerId));
	}
	
	@PutMapping(value = "/offer", produces = { "application/json" })
	public Long saveOffer(@RequestBody Offer offer) {		
		return this.offerService.save(offer).getId();
	}

}
