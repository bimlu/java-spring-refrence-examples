package com.example.associations.manytomany.repos;

import com.example.associations.manytomany.entities.Programmer;
import org.springframework.data.repository.CrudRepository;

public interface ProgrammereRepository extends CrudRepository<Programmer, Integer> {
}
