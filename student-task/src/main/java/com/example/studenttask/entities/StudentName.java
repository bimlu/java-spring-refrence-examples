package com.example.studenttask.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentName {

    @Column(nullable = false)
    @Schema(description = "First name of the student")
    private String firstName;

    @Schema(description = "Middle name of the student")
    private String middleName;

    @Schema(description = "Last name of the student")
    @Column(nullable = false)
    private String lastName;
}
