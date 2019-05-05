package com.heavenhr.recruitment.api;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	
	private final String HTTP_RESPONSE_MESSAGE_400 = "Invalid Input";
	private final String HTTP_RESPONSE_MESSAGE_404 = "Resource not found.";
	private final String HTTP_RESPONSE_MESSAGE_201 = "Resource successfully created.";
	private final String HTTP_RESPONSE_MESSAGE_200 = "Success";
	
	private static final Logger LOG = LoggerFactory.getLogger(RecruitmentApiController.class);
	
	
	@ApiOperation(value = "Find offer by ID", nickname = "offerGet", notes = "Returns a single Offer", response = Offer.class, tags={ "offers", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "successful operation", response = Offer.class),
        @ApiResponse(code = 400, message = HTTP_RESPONSE_MESSAGE_400), // need to capture
        @ApiResponse(code = 404, message = HTTP_RESPONSE_MESSAGE_404) })
	@GetMapping(value = "/offer/{offerId}", produces = { "application/json" })
	public ResponseEntity<Optional<Offer>> offerGet(@ApiParam(value = "ID of Offer to return",required=true) @PathVariable("offerId") Long offerId) {
		LOG.info("offerGet invoked "+offerId);
		Optional<Offer> offer = offerService.get(offerId);
		System.out.println(offer.isPresent());
		if(!offer.isPresent()) {
			throw new ApiException(HttpStatus.NOT_FOUND.value(), HTTP_RESPONSE_MESSAGE_404);
		}
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
				.body(offer);
	}
	

	@ApiOperation(value = "Find all offers ", nickname = "allOffers", notes = "Returns all Offers", response = Offer.class, tags={ "offers", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "successful operation", response = Offer.class),
        @ApiResponse(code = 400, message = HTTP_RESPONSE_MESSAGE_400), // need to capture
        @ApiResponse(code = 404, message = HTTP_RESPONSE_MESSAGE_404) })
	@GetMapping(value = "/offers", produces = {"application/json"})
	public ResponseEntity<List<Offer>> allOffers() {
		LOG.info("All offers invoked.");
		List<Offer> offers = this.offerService.getOffers();
		if(offers.isEmpty()) {
			throw new ApiException(HttpStatus.NOT_FOUND.value(), HTTP_RESPONSE_MESSAGE_404);
		}
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
				.body(offers);
	}
	////////////////////////////////////// Save ///////////////////////////// Not found
	@ApiOperation(value = "Save an offer ", nickname = "offerSave", notes = "Returns saved ID", response = Long.class, tags={ "offers", })
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = HTTP_RESPONSE_MESSAGE_201, response = ApiResponseObject.class),
        @ApiResponse(code = 400, message = HTTP_RESPONSE_MESSAGE_400)})
	@PostMapping(value = "/offer", produces = { "application/json" })
	public ResponseEntity<ApiResponseObject> offerSave(@ApiParam(value = "Offer to save",required=true)@RequestBody Offer offer) {
		LOG.info("saving Offer : "+offer);
		Offer newOffer = this.offerService.save(offer);
		if(null == newOffer) {
			throw new ApiException(HttpStatus.BAD_REQUEST.value(), HTTP_RESPONSE_MESSAGE_400);
		}
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
				.body(new ApiResponseObject(HttpStatus.CREATED.value(), HTTP_RESPONSE_MESSAGE_201));		
	}
	
	@ApiOperation(value = "query an Offer and Application ", nickname = "offerApplicationGet", notes = "Returns OfferApplication", response = OfferApplication.class, tags={ "offers", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "successful operation", response = OfferApplication.class),
        @ApiResponse(code = 400, message = HTTP_RESPONSE_MESSAGE_400), 
        @ApiResponse(code = 404, message = HTTP_RESPONSE_MESSAGE_404) })
	@GetMapping(value = "/offer/{offerId}/application/{id}", produces= {"application/json"})
	public ResponseEntity<Optional<OfferApplication>> offerApplicationGet(@ApiParam(value = "offer Id",required=true)@PathVariable ("offerId") Long offerId, @ApiParam(value = "application Id",required=true)@PathVariable ("id") Long id) {
		LOG.info("offerApplicationGet invoked.");
		Optional<OfferApplication> offerApplication = this.offerApplicationService.get(id);
		if(!offerApplication.isPresent()) {
			throw new ApiException(HttpStatus.NOT_FOUND.value(), HTTP_RESPONSE_MESSAGE_404);
		}
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
				.body(offerApplication);
	}
	
	
	@ApiOperation(value = "query an application by Offer Id ", nickname = "allOfferApplicationGet", notes = "Returns OfferApplication", response = OfferApplication.class, tags={ "offers", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "successful operation", response = OfferApplication.class),
        @ApiResponse(code = 400, message = HTTP_RESPONSE_MESSAGE_400),
        @ApiResponse(code = 404, message = HTTP_RESPONSE_MESSAGE_404) })
	@RequestMapping(value="/offer/{offerId}/applications", produces = {"application/json"})
	public ResponseEntity<List<OfferApplication>> allOfferApplicationGet(@ApiParam(value = "offer Id",required=true)@PathVariable("offerId") Long offerId) {
		LOG.info("allOfferApplicationGet invoked.");
		List<OfferApplication> offerApplications = this.offerApplicationService.allOfferApplication();
		if(offerApplications.isEmpty()) {
			throw new ApiException(HttpStatus.NOT_FOUND.value(), HTTP_RESPONSE_MESSAGE_404);
		}
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
				.body(offerApplications);
	}
	
	////////////////////////////////////// Save ///////////////////////////// Not found
	@ApiOperation(value = "save an application for a Offer Id ", nickname = "allOfferApplicationGet", notes = "Returns OfferApplication", response = Long.class, tags={ "offers", })
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = HTTP_RESPONSE_MESSAGE_201, response = ApiResponseObject.class),
        @ApiResponse(code = 400, message = HTTP_RESPONSE_MESSAGE_400)})
	@PutMapping(value ="/offer/{offerId}/application", produces = {"application/json" })
	public  ResponseEntity<ApiResponseObject> offerApplicationSave(@ApiParam(value = "offer Id",required=true)@PathVariable("offerId") Long offerId,@ApiParam(value = "application object",required=true) @RequestBody OfferApplication offerApplication) {
		LOG.info("offerApplicationSave invoked.");
		OfferApplication newOfferApplication = this.offerApplicationService.save(offerApplication, offerId);
		if(newOfferApplication == null) {
			throw new ApiException(HttpStatus.NOT_FOUND.value(), HTTP_RESPONSE_MESSAGE_400);
		}
		return  ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
				.body(new ApiResponseObject(HttpStatus.CREATED.value(), "Application successfully created."));
	}
	
	@ApiOperation(value = "update an application for a Offer Id ", nickname = "allOfferApplicationGet", notes = "Returns OfferApplication", response = OfferApplication.class, tags={ "offers", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = HTTP_RESPONSE_MESSAGE_200, response = ApiResponseObject.class),
        @ApiResponse(code = 400, message = HTTP_RESPONSE_MESSAGE_400),
        @ApiResponse(code = 404, message = HTTP_RESPONSE_MESSAGE_404) })
	@PatchMapping(value= "/offer/{offerId}/application/{id}", produces = {"application/json"})
	public  ResponseEntity<ApiResponseObject> offerApplicationUpdate(@ApiParam(value = "offr id",required=true) @PathVariable("offerId") Long offerId, 
				@ApiParam(value = "offr id",required=true) @PathVariable("id") Long id, @ApiParam(value = "application object",required=true) @RequestBody OfferApplication offerApplication) {
		LOG.info("offerApplicationUpdate invoked.");
		OfferApplication application = offerApplication;
		application.setId(id);
		OfferApplication newOfferApplication = this.offerApplicationService.update(application, offerId);
		if(newOfferApplication == null) {
			throw new ApiException(HttpStatus.NOT_FOUND.value(), HTTP_RESPONSE_MESSAGE_404);
		}
		return  ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
				.body(new ApiResponseObject(HttpStatus.OK.value(), HTTP_RESPONSE_MESSAGE_200));
	}
}