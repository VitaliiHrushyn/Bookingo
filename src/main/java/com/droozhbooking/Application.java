package com.droozhbooking;

import com.droozhbooking.service.SimpleAddressService;
import com.droozhbooking.service.SimpleCountryService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.lang.invoke.MethodHandles;

/**
 * Created by romm on 14.08.17.
 */
@SpringBootApplication
public class Application {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args) {
    	
    	logger.info("app starting");
    	
   // 	SpringApplication.run(Application.class, args);

        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        // This line need be removed
       SimpleCountryService countryService = context.getBean(SimpleCountryService.class);

       SimpleAddressService addressService = context.getBean(SimpleAddressService.class);
       
       countryService.init();
       addressService.init();
       
//       Country country = countryService.getCountryById(1L);
//       Address address = addressService.getAddressById(1L);
//       
//       address.setCountry(country);
//       
//       addressService.save(address);
        
        logger.info("hurray!!! app's started");

    }

}
