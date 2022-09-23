package com.example.patientscheduling.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String speciality;

    @ManyToMany(mappedBy = "doctors")
    private List<Patient> patients;

    @OneToMany
    private List<Appointment> appointments;
}
