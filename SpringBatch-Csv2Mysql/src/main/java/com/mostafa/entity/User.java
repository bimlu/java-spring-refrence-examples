package com.mostafa.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "batch_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "GENDER")
    private String gender;
    @Column(name = "CONTACT")
    private String contactNo;
    @Column(name = "COUNTRY")
    private String country;
    @Column(name = "DOB")
    private String dob;
}
