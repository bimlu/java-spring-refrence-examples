package com.example.mongodemo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
public class Product {

    private String id;

    private String name;

    private float price;
}
