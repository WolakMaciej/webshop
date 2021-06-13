package com.example.webshopback.repository;

import com.example.webshopback.model.ItemCart;
import com.example.webshopback.model.ShopOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopOrderRepository extends JpaRepository<ShopOrder, Long> {
    List<ShopOrder> findShopOrdersByUserUsername(String username);
}
