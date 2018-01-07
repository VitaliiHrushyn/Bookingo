package com.droozhbooking.service;

import com.droozhbooking.domain.address.Address;

/**
 * Created by Roman Usyk on 05.09.17.
 */
public interface AddressService {

    void init();

    Address getAddressById(Long id);

    Address findOneAddressByLocalityPart(String LocalitySubstr);
}
