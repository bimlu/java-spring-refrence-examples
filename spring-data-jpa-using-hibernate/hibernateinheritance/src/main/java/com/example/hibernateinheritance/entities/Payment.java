package com.example.hibernateinheritance.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Payment {

    @Id
    private int id;
    private double amount;
}
