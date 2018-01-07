package com.droozhbooking.service;

import com.droozhbooking.domain.address.Country;

public interface CountryService {

	void init();
	
	Country getCountryById(Long id);

}
