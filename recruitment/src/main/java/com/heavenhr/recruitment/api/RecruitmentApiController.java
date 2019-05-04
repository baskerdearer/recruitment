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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.heavenhr.recruitment.offer.Offer;
import com.heavenhr.recruitment.offer.OfferService;
import com.heavenhr.recruitment.offerapplication.OfferApplication;
import com.heavenhr.recruitment.offerapplication.OfferApplicationService;
import com.heavenhr.recruitment.offerapplication.UniqueCandidateOffer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Api for Recruitment", description = "the API list for the HR management ")
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
	
	
	@ApiOperation(value = "Find offer by ID", nickname = "offerGet", notes = "Returns a single Offer", response = Offer.class, tags={ "offers", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "successful operation", response = Offer.class),
        @ApiResponse(code = 400, message = "Invalid Offer ID supplied"),
        @ApiResponse(code = 404, message = "Offer not found") })
	@GetMapping(value = "/offer/{offerId}", produces = { "application/json" })
	public ResponseEntity<Optional<Offer>> offerGet(@ApiParam(value = "ID of Offer to return",required=true) @PathVariable("offerId") Long offerId) {
		LOG.info("offerGet invoked "+offerId);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
				.body(offerService.get(offerId));
	}
	

	@ApiOperation(value = "Find all offers ", nickname = "allOffers", notes = "Returns all Offers", response = Offer.class, tags={ "offers", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "successful operation", response = Offer.class),
        @ApiResponse(code = 400, message = "Invalid Offer ID supplied"),
        @ApiResponse(code = 404, message = "Offer not found") })
	@GetMapping(value = "/offers", produces = {"application/json"})
	public ResponseEntity<List<Offer>> allOffers() {
		LOG.info("All offers invoked.");
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
				.body(this.offerService.getOffers());
	}
	
	@ApiOperation(value = "Save an offer ", nickname = "offerSave", notes = "Returns saved ID", response = Long.class, tags={ "offers", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "successful operation", response = Long.class),
        @ApiResponse(code = 400, message = "Invalid Offer  supplied"),
        @ApiResponse(code = 404, message = "Offer not found") })
	@PostMapping(value = "/offer", produces = { "application/json" })
	public ResponseEntity<Long> offerSave(@ApiParam(value = "Offer to save",required=true)@RequestBody Offer offer) {
		LOG.info("saving Offer : "+offer);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
				.body(this.offerService.save(offer).getId());		
	}
	
	@ApiOperation(value = "query an Offer and Application ", nickname = "offerApplicationGet", notes = "Returns OfferApplication", response = OfferApplication.class, tags={ "offers", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "successful operation", response = OfferApplication.class),
        @ApiResponse(code = 400, message = "Invalid Offer ID supplied"),
        @ApiResponse(code = 404, message = "Offer not found") })
	@GetMapping(value = "/offer/{offerId}/application/{id}", produces= {"application/json"})
	public ResponseEntity<Optional<OfferApplication>> offerApplicationGet(@ApiParam(value = "offer Id",required=true)@PathVariable ("offerId") Long offerId, @ApiParam(value = "application Id",required=true)@PathVariable ("id") Long id) {
		LOG.info("offerApplicationGet invoked.");
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
				.body(this.offerApplicationService.get(id));
	}
	
	
	@ApiOperation(value = "query an application by Offer Id ", nickname = "allOfferApplicationGet", notes = "Returns OfferApplication", response = OfferApplication.class, tags={ "offers", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "successful operation", response = OfferApplication.class),
        @ApiResponse(code = 400, message = "Invalid Offer ID supplied"),
        @ApiResponse(code = 404, message = "Application not found") })
	@RequestMapping(value="/offer/{offerId}/applications", produces = {"application/json"})
	public ResponseEntity<List<OfferApplication>> allOfferApplicationGet(@ApiParam(value = "offer Id",required=true)@PathVariable("offerId") Long offerId) {
		LOG.info("allOfferApplicationGet invoked.");
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
				.body(this.offerApplicationService.allOfferApplication());
	}
	
	@ApiOperation(value = "save an application for a Offer Id ", nickname = "allOfferApplicationGet", notes = "Returns OfferApplication", response = Long.class, tags={ "offers", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "successful operation", response = Long.class),
        @ApiResponse(code = 400, message = "Invalid Offer ID supplied"),
        @ApiResponse(code = 404, message = "Offer not found") })
	@PutMapping(value ="/offer/{offerId}/application", produces = {"application/json" })
	public  ResponseEntity<Long> offerApplicationSave(@ApiParam(value = "offer Id",required=true)@PathVariable("offerId") Long offerId,@ApiParam(value = "application object",required=true) @RequestBody OfferApplication offerApplication) {
		LOG.info("offerApplicationSave invoked.");
		return  ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
				.body(this.offerApplicationService.save(offerApplication, offerId).getId());
	}
	
	@ApiOperation(value = "update an application for a Offer Id ", nickname = "allOfferApplicationGet", notes = "Returns OfferApplication", response = OfferApplication.class, tags={ "offers", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "successful operation", response = OfferApplication.class),
        @ApiResponse(code = 400, message = "Invalid Offer ID supplied"),
        @ApiResponse(code = 404, message = "Offer not found") })
	@PatchMapping(value= "/offer/{offerId}/application", produces = {"application/json"})
	public  ResponseEntity<OfferApplication> offerApplicationUpdate(@ApiParam(value = "offr id",required=true) @PathVariable("offerId") Long offerId, @ApiParam(value = "application object",required=true) @RequestBody OfferApplication offerApplication) {
		LOG.info("offerApplicationUpdate invoked.");
		return  ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
				.body(this.offerApplicationService.update(offerApplication, offerId));
	}
}
