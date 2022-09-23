package com.example.clinicalsapi.controllers;

import com.example.clinicalsapi.dto.ClinicalDataRequest;
import com.example.clinicalsapi.entities.ClinicalData;
import com.example.clinicalsapi.entities.Patient;
import com.example.clinicalsapi.repos.ClinicalDataRepository;
import com.example.clinicalsapi.repos.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ClinicalDataController {

    @Autowired
    ClinicalDataRepository clinicalDataRepository;

    @Autowired
    PatientRepository patientRepository;

    @RequestMapping(value = "/clinicals", method = RequestMethod.POST)
    public ClinicalData saveClinicalData(@RequestBody ClinicalDataRequest request) {

        ClinicalData clinicalData = new ClinicalData();

        clinicalData.setComponentName(request.getComponentName());
        clinicalData.setComponentValue(request.getComponentValue());

        Patient patient = patientRepository.findById(request.getPatientId()).get();
        clinicalData.setPatient(patient);

        return clinicalDataRepository.save(clinicalData);
    }
}
