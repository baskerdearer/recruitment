package com.heavenhr.recruitment.offerapplication;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferApplicationRepository extends JpaRepository<OfferApplication, UniqueCandidateOffer> {

}
