package com.example.springbatch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@ToString
@XmlRootElement(name = "student")
public class StudentXml {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    @XmlElement(name = "first_name")
    public String getFirstName() {
        return firstName;
    }
}
