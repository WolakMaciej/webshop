package com.example.webshopback.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ItemCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonProperty("product")
    private Product product;

    private int quantity;

/*
    @Transient
    public double getTotalPrice() {
        return getProduct().getPrice() * getQuantity();
    }
*/


}
