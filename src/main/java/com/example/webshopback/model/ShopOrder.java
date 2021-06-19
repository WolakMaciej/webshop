package com.example.webshopback.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class ShopOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CreationTimestamp
    private LocalDateTime createDateTime;
    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    @ManyToOne
    private User user;
    //@JsonProperty("cart")
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ItemCart> itemCarts = new ArrayList<ItemCart>();


    @Transient
    public Double getGrandTotalPrice() {
        double sum = 0D;
        List<ItemCart> itemCarts = getItemCarts();
        if (itemCarts == null) {
            return 0.0;
        } else
            for (ItemCart op : itemCarts) {
                sum += op.getQuantity()*op.getProduct().getPrice();
            }
        return sum;
    }



}
