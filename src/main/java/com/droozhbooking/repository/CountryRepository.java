package com.droozhbooking.repository;

import org.springframework.data.repository.CrudRepository;

import com.droozhbooking.domain.address.Country;

public interface CountryRepository extends CrudRepository<Country, Long> {
		
	//Country findById(Long id);

}
