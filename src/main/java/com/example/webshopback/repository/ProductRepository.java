package com.example.webshopback.repository;

import com.example.webshopback.model.ItemCart;
import com.example.webshopback.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT sum(quantity) FROM Product")
    Long sumQuantities();

    @Query(value = "SELECT sum(quantity * price) FROM Product")
    Double total();
}
