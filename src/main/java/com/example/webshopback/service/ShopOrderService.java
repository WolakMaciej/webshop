package com.example.webshopback.service;

import com.example.webshopback.model.ItemCart;
import com.example.webshopback.model.ShopOrder;

import java.util.List;

public interface ShopOrderService {
    List<ShopOrder> getAll();

    ShopOrder createNew(ShopOrder shopOrder);

    ShopOrder getOne(long id);

    void delete(long id);

    void update(ShopOrder shopOrder);

    List<ShopOrder> findShopOrdersByUserUsername(String username);


}
