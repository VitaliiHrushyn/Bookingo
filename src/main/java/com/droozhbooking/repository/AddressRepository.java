package com.droozhbooking.repository;

import com.droozhbooking.domain.address.Locality;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;

import com.droozhbooking.domain.address.Address;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Roman Usyk on 05.09.17.
 */
public interface AddressRepository extends CrudRepository<Address, Long> {

    List<Address> findAddressByLocality(Locality locality);

    /**
     * Usage:
     *
     * addressRepository.findAll(hasIdMoreThan(10));
     *
     */
    static Specification<Address> hasIdMoreThan(Integer id) {

        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("Address"), "id");

    }

}
