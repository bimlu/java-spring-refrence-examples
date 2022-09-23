package com.example.filedata.files.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
@Getter
@Setter
public class Image {

    @Id
    private long id;

    private String name;

    @Lob
    private byte[] data;
}
