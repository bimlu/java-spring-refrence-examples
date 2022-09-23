package com.example.studenttask.entities;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Schema(description = "A simple student schema")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Schema(description = "Name of the student")
    private StudentName studentName;

    @Column(nullable = false)
    @Schema(description = "Roll number of the student")
    private String rollNumber;

    @Schema(description = "Mobile number of the student")
    private String mobileNumber;

    @Schema(description = "Email of the student")
    private String email;
}
