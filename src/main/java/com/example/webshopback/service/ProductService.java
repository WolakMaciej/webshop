package com.example.webshopback.service;

import com.example.webshopback.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();

    Product createNew(Product product);

    Product getOne(long id);

    void delete(long id);

    void update(Product product);

}
