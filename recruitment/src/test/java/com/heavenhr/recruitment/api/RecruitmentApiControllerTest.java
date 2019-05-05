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

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class RecruitmentApiControllerTest {

	/*@Autowired
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
	public void offerGetTest() {

		ResponseEntity<Offer> response = restTemplate.getForEntity("/offer/{offerId}", Offer.class, 21);
		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		Assertions.assertThat(response.getBody().getJobTitle()).isEqualTo("ABC");

	}

	@Test
	public void allOffersTest() {

		ResponseEntity<List<Offer>> response = restTemplate.exchange("/offers", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Offer>>() {
				});

		Assertions.assertThat(Arrays.asList(response.getBody()).size()).isGreaterThan(0);

	}

	@Test
	public void offerSaveTest() {
		Offer offer = new Offer();
		offer.setJobTitle("ABC");
		offer.setStartDate(Date.from(Instant.now()));
		
		ResponseEntity<Long> response = restTemplate.postForEntity("/offer", offer, Long.class);
		
		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		Assertions.assertThat(response.getBody()).isNotNull();

	}

	@Test
	public void offerApplicationGetTest() {
		Map<String, Object> params = new HashMap<>();
		params.put("offerId", 21);
		params.put("id", 10);
		ResponseEntity<OfferApplication> response = restTemplate.getForEntity("/offer/{offerId}/application/{id}", OfferApplication.class, params);
		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		Assertions.assertThat(response.getBody().getUniqueCandidateOffer().getId()).isNotNull();

	}

	@Test
	public void offerApplicationSaveTest() {
		Map<String, Object> params = new HashMap<>();
		params.put("offerId", 21);
		ResponseEntity<Offer> response = restTemplate.getForEntity("/offer/{offerId}/application", Offer.class, params);
		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		Assertions.assertThat(response.getBody().getJobTitle()).isEqualTo("ABC");

	}

	@Test
	public void offerApplicationUpdateTest() {
		OfferApplication offerApplication= new OfferApplication();
		//offerApplication.setId(1l);
		offerApplication.setEmailId("newEmail@email.com");
		offerApplication.setStatus(ApplicationStatus.INVITED);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("offerId", 1l);
		map.add("offerApplication", offerApplication);
		HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<MultiValueMap<String, Object>>(map, headers);

		
		ResponseEntity<OfferApplication> response = restTemplate.postForEntity("/offer", httpEntity, OfferApplication.class);
		
		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		Assertions.assertThat(response.getBody()).isNotNull();

	}*/

}
