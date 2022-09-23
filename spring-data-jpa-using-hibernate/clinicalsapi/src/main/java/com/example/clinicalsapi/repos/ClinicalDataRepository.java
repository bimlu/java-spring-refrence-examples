package com.example.clinicalsapi.repos;

import com.example.clinicalsapi.entities.ClinicalData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicalDataRepository extends JpaRepository<ClinicalData, Integer> {
}
