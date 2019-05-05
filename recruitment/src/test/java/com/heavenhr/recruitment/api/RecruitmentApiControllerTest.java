package com.heavenhr.recruitment.api;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.heavenhr.recruitment.offer.Offer;
import com.heavenhr.recruitment.offerapplication.ApplicationStatus;
import com.heavenhr.recruitment.offerapplication.OfferApplication;
import com.heavenhr.recruitment.offerapplication.OfferApplicationResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class RecruitmentApiControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	HttpHeaders headers = new HttpHeaders();

	@Before
	public void init() {
		MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
		mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON));
		restTemplate.getRestTemplate().getMessageConverters().add(mappingJackson2HttpMessageConverter);		
	}
	
	@Test
	public void offerSaveTest() {
		Offer offer = new Offer();
		offer.setJobTitle("DEF");
		offer.setStartDate(Date.from(Instant.now()));
		offer.setId(22L);
				
		ResponseEntity<ApiResponseObject> response = restTemplate.postForEntity("/recruitment/v1/offer", offer, ApiResponseObject.class);
		
		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		Assertions.assertThat(response.getBody()).isNotNull();

	}

	@Test
	public void offerGetTest() {

		ResponseEntity<Offer> response = restTemplate.getForEntity("/recruitment/v1/offer/{offerId}", Offer.class, 1);
		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		Assertions.assertThat(response.getBody().getJobTitle()).isEqualTo("Senior Java Developer");

	}

	@Test
	public void allOffersTest() {

		ResponseEntity<List<Offer>> response = restTemplate.exchange("/recruitment/v1/offers", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Offer>>() {
				});

		Assertions.assertThat(Arrays.asList(response.getBody()).size()).isGreaterThan(0);

	}	

	@Test
	public void offerApplicationGetTest() {
		Map<String, Object> params = new HashMap<>();
		params.put("offerId", 1);
		params.put("id", 1);
		ResponseEntity<OfferApplication> response = restTemplate.getForEntity("/recruitment/v1/offer/{offerId}/application/{id}", OfferApplication.class, params);
		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		Assertions.assertThat(response.getBody().getId()).isNotNull();
	}
	
	@Test
	public void offerApplicationGetAllTest() {
		Map<String, Object> params = new HashMap<>();
		params.put("offerId", 1);
		ResponseEntity<OfferApplicationResponse> response = restTemplate.getForEntity("/recruitment/v1/offer/{offerId}/applications", OfferApplicationResponse.class, params);
		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	public void offerApplicationUpdateTest() {
		OfferApplication offerApplication= new OfferApplication();
		offerApplication.setEmailId("newEmail@email.com");
		offerApplication.hashCode();
		offerApplication.toString();
		offerApplication.equals(offerApplication);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("offerId", 1l);
		map.add("offerApplication", offerApplication);
		HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<MultiValueMap<String, Object>>(map, headers);

		
		ResponseEntity<OfferApplication> response = restTemplate.postForEntity("/recruitment/v1/offer/1/application", httpEntity, OfferApplication.class);
		
		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		Assertions.assertThat(response.getBody()).isNotNull();

	}

}
