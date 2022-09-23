package com.example.associations.onetoone.repos;

import com.example.associations.onetoone.entities.License;
import org.springframework.data.repository.CrudRepository;

public interface LicenseRepository extends CrudRepository<License, Long> {
}
