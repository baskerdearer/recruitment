package com.heavenhr.recruitment.offerapplication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferApplicationRepository extends JpaRepository<OfferApplication, Long> {

}
