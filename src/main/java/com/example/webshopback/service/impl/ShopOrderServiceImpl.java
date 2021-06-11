package com.example.webshopback.service.impl;

import com.example.webshopback.model.ItemCart;
import com.example.webshopback.model.ShopOrder;
import com.example.webshopback.repository.ShopOrderRepository;
import com.example.webshopback.service.ShopOrderService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
@Service
@Data
public class ShopOrderServiceImpl implements ShopOrderService {

    @Autowired
    private ShopOrderRepository shopOrderRepository;

    @Override
    public List<ShopOrder> getAll() {
        return shopOrderRepository.findAll();
    }

    @Override
    public ShopOrder createNew(ShopOrder shopOrder) {
        if(shopOrder.getUser()!=null && (shopOrder.getItemCarts()!=null) ){
        //shopOrder.setUser(shopOrder.getUser());
        shopOrder.setItemCarts(shopOrder.getItemCarts());}
        return shopOrderRepository.save(shopOrder);
    }

    @Override
    public ShopOrder getOne(long id) {
        return shopOrderRepository.findById(id).orElseThrow(() -> {
            return new NotFoundException("Order not found");
        });
    }

    @Override
    public void delete(long id) {
        shopOrderRepository.deleteById(id);
    }

    @Override
    public void update(ShopOrder shopOrder) {
        shopOrder.setId(shopOrder.getId());
        shopOrderRepository.save(shopOrder);
    }

    @Override
    public List<ShopOrder> findShopOrdersByUserUsername(String username) {
        return shopOrderRepository.findShopOrdersByUserUsername(username);
    }



}
