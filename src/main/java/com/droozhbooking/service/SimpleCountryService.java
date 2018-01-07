package com.droozhbooking.service;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.droozhbooking.domain.address.Country;
import com.droozhbooking.repository.CountryRepository;
import com.google.gson.Gson;

@Service
public class SimpleCountryService implements CountryService {
	
	 private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	    @Autowired
	    private CountryRepository countryRepository;

	    /*
	        This code shouldn't be here
	        but it is :)
	     */
	    public void init() {
            Country country = new Country("Ukraine");
//	        Region region = new Region("Sumska oblast");
//	        District district = new District("Shostkinskyi raion");
//	        Locality locality = new Locality("Shostka");
//	        
//	        country.addRegion(region);
//	        region.addDistrict(district);
//	        district.addLocalitiy(locality);
//	       
//	        address.setLocality(locality);
//	          address.setCountry(country);
	        
	     //   LOGGER.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+address.toString());

	 //         System.out.println("!!!!!!!!!!!!  Country "+country);
//	          
//	          address.setApartment("17");
//	          address.setBuilding("12");
//	          address.setPhoneNumber("055-847-64-33");
//	          address.setEmail("address@address.com");
//	          address.setStreet("Kalynova");
//	          address.setZIPcode("2340787");
//	          
//	          System.out.println("!!!!!!!!!!!!  Address "+address);
	          
	          countryRepository.save(country);
	        LOGGER.debug(String.format("Saved Country instance with id %d: %s", country.getId(), new Gson().toJson(country)));
	    }

	@Override
	public Country getCountryById(Long id) {
		return countryRepository.findOne(id);
	}

}
