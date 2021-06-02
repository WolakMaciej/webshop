package com.example.webshopback.controller;

import com.example.webshopback.model.ShopOrder;
import com.example.webshopback.service.ShopOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/")
public class ShopOrderController {
    @Autowired
    ShopOrderService shopOrderService;


    @GetMapping("/shopOrders")
    public ResponseEntity<List<ShopOrder>> getShopOrder() {
        List<ShopOrder> shopOrders = shopOrderService.getAll();
        if (CollectionUtils.isEmpty(shopOrders)) {
            throw new EntityNotFoundException();
        }
        return new ResponseEntity<>(shopOrders, HttpStatus.OK);
    }

    @PostMapping("/shopOrders")
    public ResponseEntity<ShopOrder> createNewShopOrder(@Valid @RequestBody ShopOrder shopOrder) {
        ShopOrder newShopOrder = shopOrderService.createNew(shopOrder);
        return new ResponseEntity<>(newShopOrder, HttpStatus.CREATED);
    }

    @GetMapping("/shopOrders/{id}")
    public ResponseEntity<ShopOrder> getShopOrder(@PathVariable long id) {
        ShopOrder shopOrder = shopOrderService.getOne(id);
        return new ResponseEntity<>(shopOrder, HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/api/shopOrders/{id}")
    public ResponseEntity<ShopOrder> deleteShopOrder(@PathVariable long id) {
        shopOrderService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/shopOrders/{id}")
    public ResponseEntity<ShopOrder> updateShopOrder(@PathVariable long id, @RequestBody ShopOrder shopOrder) {
        ShopOrder newShopOrder = shopOrderService.getOne(id);
        newShopOrder.setUser(newShopOrder.getUser());
        shopOrderService.update(newShopOrder);
        return new ResponseEntity<>(shopOrder, HttpStatus.OK);
    }
}
