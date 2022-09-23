package com.example.associations.onetomany.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<PhoneNumber> numbers;

    public void addPhoneNumber(PhoneNumber number) {
        if (number != null) {
            if (numbers == null) {
                numbers = new HashSet<>();
            }
            number.setCustomer(this);
            numbers.add(number);
        }
    }
}
