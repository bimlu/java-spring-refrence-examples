package com.example.springbatch.postgres.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@ToString
@Table(name = "subjects_learning")
public class SubjectsLearning {

    @Id
    private Long id;

    private String subName;

    private Long marksObtained;
}
