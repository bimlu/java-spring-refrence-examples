package com.example.compsitekey.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@Getter
@Setter
public class Customer {

    @EmbeddedId
    private CustomerId id;

    private String name;
}
