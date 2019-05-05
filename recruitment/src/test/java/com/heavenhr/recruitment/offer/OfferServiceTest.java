package com.heavenhr.recruitment.offer;

import java.util.Date;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class OfferServiceTest {
	
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	@InjectMocks
	OfferService offerService;
	
	@Mock
	OfferRepository offerRepository;
	
	@Test
	public void saveOfferTest() {
		Offer offer = new Offer();
		offer.setJobTitle("JAVA Developer");
		offer.setStartDate(new Date());
		offer.toString();
		offer.hashCode();
		offer.equals(offer);
		Mockito.when(this.offerRepository.save(offer)).thenReturn(offer);
		this.offerService.save(offer);
		Mockito.verify(this.offerRepository, Mockito.atLeast(1)).save(Mockito.any(Offer.class));
	}
	
	@Test
	public void saveOfferTestForEqualCoverage() {
		Offer offer = new Offer();
		offer.setJobTitle("JAVA Developer");
		offer.setStartDate(new Date());
		
		Offer offer1 = new Offer();
		offer1.setJobTitle("JAVA Developer");
		offer1.setStartDate(new Date());
		offer.equals(offer1);
		Mockito.when(this.offerRepository.save(offer)).thenReturn(offer);
		this.offerService.save(offer);
		Mockito.verify(this.offerRepository, Mockito.atLeast(1)).save(Mockito.any(Offer.class));
	}

}
