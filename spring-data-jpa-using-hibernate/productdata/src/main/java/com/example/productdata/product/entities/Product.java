package com.example.productdata.product.entities;

import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table
@Data
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Product implements Serializable {

    @Id
    private int id;
    private String name;
    @Column(name = "description")
    private String desc;
    private Double price;
}
