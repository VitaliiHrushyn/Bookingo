package com.droozhbooking.web;

import com.droozhbooking.domain.address.Address;
import com.droozhbooking.service.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by romm on 14.08.17.
 */

@RestController
@EnableAutoConfiguration	
@RequestMapping("/api/v1")
@Api(
        value = "Address controller"
)
public class AddressController {

    @Autowired
    private AddressService addressService;

    @RequestMapping(value = "/address/id/{id}", method = RequestMethod.GET)
    @ApiOperation(
            value = "getAddressById",
            notes = "Find one user by id",
            produces = "Application/json"
    )
    public ResponseEntity<Address> getAddressById(@PathVariable("id") Long id)
    {
        Address address = addressService.getAddressById(id);
        if (address == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @RequestMapping(value = "/address/locality/{locality}", method = RequestMethod.GET)
    @ApiOperation(
            value = "getAddressByLocalityPart",
            notes = "Find one user by locality part. Returns first instance if multiple ones found.",
            produces = "Application/json"
    )
    public ResponseEntity<Address> getAddressByLocalityPart(@PathVariable("locality") String LocalitySubstr)
    {
        Address address = addressService.findOneAddressByLocalityPart(LocalitySubstr);
        if (address == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

}
