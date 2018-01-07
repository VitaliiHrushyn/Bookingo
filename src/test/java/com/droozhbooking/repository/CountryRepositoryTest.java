package com.droozhbooking.repository;

import com.droozhbooking.domain.address.Country;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by Roman Usyk on 12.10.17.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class CountryRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CountryRepository countryRepository;

    @Test
    public void testFindOne() {
        Country country = new Country("Ukraine");
        entityManager.persist(country);
        entityManager.flush();

        Country found = countryRepository.findOne(country.getId());
        assertThat(found).isNotNull();
        assertThat(country.getName()).isEqualTo(found.getName());
    }
}
