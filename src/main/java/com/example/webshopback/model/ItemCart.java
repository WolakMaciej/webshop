package com.example.webshopback.model;

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
    @JoinColumn
    private Product product;

    private int quantity;

    @ManyToOne
    @JoinColumn
    @JsonProperty("order")
    private ShopOrder shopOrder;

    @Transient
    public double getTotalPrice() {
        return getProduct().getPrice() * getQuantity();
    }


}
