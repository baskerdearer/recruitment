package com.heavenhr.recruitment.api;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.heavenhr.recruitment.offer.Offer;
import com.heavenhr.recruitment.offer.OfferService;
import com.heavenhr.recruitment.offerapplication.OfferApplication;
import com.heavenhr.recruitment.offerapplication.OfferApplicationService;
import com.heavenhr.recruitment.offerapplication.UniqueCandidateOffer;

@RestController
public class RecruitmentApiController {
	
	RecruitmentApiController() {
		// Do Nothing.
	}
	
	@Autowired
	OfferService offerService;
	
	@Autowired
	OfferApplicationService offerApplicationService;
	
	private static final Logger LOG = LoggerFactory.getLogger(RecruitmentApiController.class);
	
	@GetMapping(value = "/offer/{offerId}", produces = { "application/json" })
	public ResponseEntity<Optional<Offer>> offerGet(@PathVariable("offerId") Long offerId) {
		LOG.info("offerGet invoked "+offerId);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
				.body(offerService.get(offerId));
	}
	

	@GetMapping(value = "/offers", produces = {"application/json"})
	public ResponseEntity<List<Offer>> allOffers() {
		LOG.info("All offers invoked.");
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
				.body(this.offerService.getOffers());
	}
	
	@PutMapping(value = "/offer", produces = { "application/json" })
	public Long offerSave(@RequestBody Offer offer) {
		LOG.info("saveOffer : ");
		return this.offerService.save(offer).getId();		
	}
	
	@GetMapping(value = "/offer/{offerId}/application/{id}", produces= {"application/json"})
	public ResponseEntity<Optional<OfferApplication>> offerApplicationGet(@PathVariable ("offerId") Long offerId, @PathVariable ("id") Long id) {
		LOG.info("offerApplicationGet invoked.");
		UniqueCandidateOffer uniqueCandidateOffer = new UniqueCandidateOffer();
		uniqueCandidateOffer.setId(id);
		uniqueCandidateOffer.setOfferID(offerId);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
				.body(this.offerApplicationService.get(uniqueCandidateOffer));
	}
	
	@RequestMapping(value="/offer/{offerId}/applications", produces = {"application/json"})
	public ResponseEntity<List<OfferApplication>> allOfferApplicationGet(@PathVariable("offerId") Long offerId) {
		LOG.info("allOfferApplicationGet invoked.");
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
				.body(this.offerApplicationService.allOfferApplication());
	}
	
	@PutMapping(value ="/offer/{offerId}/application", produces = {"application/json" })
	public Long offerApplicationSave(@PathVariable("offerId") Long offerId, @RequestBody OfferApplication offerApplication) {
		LOG.info("offerApplicationSave invoked.");
		return this.offerApplicationService.save(offerApplication).getId();
	}
	
	@PatchMapping(value= "/offer/{offerId}/application", produces = {"application/json"})
	public OfferApplication offerApplicationUpdate(@PathVariable("offerId") Long offerId, @RequestBody OfferApplication offerApplication) {
		LOG.info("offerApplicationUpdate invoked.");
		return this.offerApplicationService.update(offerApplication);
	}
}
