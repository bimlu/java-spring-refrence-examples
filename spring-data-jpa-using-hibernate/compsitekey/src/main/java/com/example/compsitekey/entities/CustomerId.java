package com.example.compsitekey.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class CustomerId implements Serializable {

    private int id;

    private String email;
}
