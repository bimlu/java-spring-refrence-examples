package com.example.patientscheduling.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Getter
@Setter
@ToString
@Embeddable
public class Insurance {

    private String providerName;

    private double copay;
}
