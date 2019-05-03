package com.heavenhr.recruitment.api;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.heavenhr.recruitment.model.Offer;

@RestController
public class RecruitmentApiController {
	
	RecruitmentApiController() {
		// Do Nothing.
	}
	
	private static final Logger LOG = LoggerFactory.getLogger(RecruitmentApiController.class);
	
	@RequestMapping(value = "/offer/{offerId}", produces = { "application/json" },
			method = RequestMethod.GET)
	public ResponseEntity<Offer> offerGet(@PathVariable("offerId") int offerId) {
		Offer offer = new Offer();
		offer.setId(1);
		offer.setJobTitle("java developer");
		offer.setStartDate(new Date());
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
				.body(offer);
	}
	

}
