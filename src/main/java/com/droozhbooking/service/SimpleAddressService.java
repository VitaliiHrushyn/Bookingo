package com.droozhbooking.service;

import com.droozhbooking.domain.address.*;
import com.droozhbooking.repository.*;
import com.google.gson.Gson;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roman Usyk on 05.09.17.
 */
@Service
public class SimpleAddressService implements AddressService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private AddressRepository addressRepository;
 //   private CountryRepository countryRepository;

    /*
        This code shouldn't be here
        but it is :)
     */
    public void init() {
          Address address = new Address();
//          Country country = new Country("Ukraine");
//        Region region = new Region("Sumska oblast");
//        District district = new District("Shostkinskyi raion");
//        Locality locality = new Locality("Shostka");
//        
//        country.addRegion(region);
//        region.addDistrict(district);
//        district.addLocalitiy(locality);
//       
//        address.setLocality(locality);
//          address.setCountry(country);
        
     //   LOGGER.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+address.toString());

 //         System.out.println("!!!!!!!!!!!!  Country "+country);
//          
          address.setApartment("17");
          address.setBuilding("12");
          address.setPhoneNumber("055-847-64-33");
          address.setEmail("address@address.com");
          address.setStreet("Kalynova");
          address.setZIPcode("2340787");
//          
//          System.out.println("!!!!!!!!!!!!  Address "+address);
          
  //        countryRepository.save(country);
          addressRepository.save(address);
          
   //       Country country = countryRepository.get;
   //       address.setCountry(country);
          
          addressRepository.save(address);
          
        LOGGER.debug(String.format("Saved Address instance with id %d: %s", address.getId(), new Gson().toJson(address)));
    }
    
    public void save(Address address) {
    	addressRepository.save(address);
    	LOGGER.debug(String.format("Saved by own method Address instance with id %d: %s", address.getId(), new Gson().toJson(address)));
    }

    @Override
    public Address getAddressById(Long id) {
        return addressRepository.findOne(id);
    }

    @Override
    public Address findOneAddressByLocalityPart(String LocalitySubstr) {
        // TODO: Perform search by locality title
        List<Address> addresses = new ArrayList<>();//addressRepository.findAddressByLocalityIgnoreCaseContaining(LocalitySubstr);
        if (addresses == null || addresses.isEmpty()) {
            return null;
        } else {
            return addresses.get(0);
        }
    }

}
