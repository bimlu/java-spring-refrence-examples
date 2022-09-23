package com.example.patientscheduling.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Timestamp appointmentTime;

    private Boolean started;

    private Boolean ended;

    private String reason;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Doctor doctor;
}
