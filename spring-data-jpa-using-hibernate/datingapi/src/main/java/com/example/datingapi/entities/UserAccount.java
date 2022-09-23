package com.example.datingapi.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String userName;

    private String password;

    private int age;

    private String email;

    private String gender;

    private String phoneNumber;

    private String city;

    private String country;

    @OneToOne(mappedBy = "userAccount")
    private Interest interest;
}

