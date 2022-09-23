package com.example.customerapi.repos;

import com.example.customerapi.entities.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "addresses", path = "addresses", exported = false)
public interface AddressRepository extends CrudRepository<Address, Long> {
}
