package com.example.webshopback.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Length(min = 3, max = 30, message = "min 3 max 30 chars")
    private String name;
    @Length(max = 250)
    private String description;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private int quantity;
    @Length(max = 1000)
    private String imageUrl;


    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.MERGE)
    private List<ItemCart> itemCarts;


    @Transient
    public int getTotalUnitsInOrder() {
        int sum = 0;
        List<ItemCart> itemCarts = getItemCarts();
        if (itemCarts == null) {
            return 0;
        } else
            for (ItemCart op : itemCarts) {
                sum += op.getQuantity();
            }
        return sum;
    }

    @Transient
    public int getCurrentTotalUnitsInStock() {
        return getQuantity() - getTotalUnitsInOrder();
    }
}


