package com.example.webshopback.controller;

import com.example.webshopback.model.ShopOrder;
import com.example.webshopback.model.User;
import com.example.webshopback.service.ShopOrderService;
import com.example.webshopback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    @Autowired
    UserService userService;

    @GetMapping("/shopOrders")
    public ResponseEntity<List<ShopOrder>> getShopOrder() {
        List<ShopOrder> shopOrders = shopOrderService.getAll();
        if (CollectionUtils.isEmpty(shopOrders)) {
            throw new EntityNotFoundException();
        }
        return new ResponseEntity<>(shopOrders, HttpStatus.OK);
    }


/*    @GetMapping("/shopOrders")
    public ResponseEntity<List<ShopOrder>> getShopOrder() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String auth = authentication.getName();
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        List<ShopOrder> shopOrders;
        if (auth.equals("ADMIN")) {
            shopOrders = shopOrderService.getAll();
        }else {
            shopOrders = shopOrderService.findShopOrdersByUserUsername(username);
        }
        if (CollectionUtils.isEmpty(shopOrders)) {
            throw new EntityNotFoundException();
        }
        return new ResponseEntity<>(shopOrders, HttpStatus.OK);
    }*/

    @PostMapping("/shopOrders")
    public ResponseEntity<ShopOrder> createNewShopOrder(@Valid @RequestBody ShopOrder shopOrder) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        User user = userService.findByUsername(username);
        //user.setId(user.getId());
        shopOrder.setUser(user);
        shopOrderService.createNew(shopOrder);
        return new ResponseEntity<>(shopOrder, HttpStatus.CREATED);
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

/*    @GetMapping("/shopOrders")
    public ResponseEntity<List<ShopOrder>> getShopOrderByUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        if (username.equals("Admin")){
            List<ShopOrder> shopOrders=shopOrderService.getAll();
            return new ResponseEntity<>(shopOrders, HttpStatus.OK);
        }
        else {
            List<ShopOrder> shopOrders = shopOrderService.findShopOrdersByUserUsername(username);
            return new ResponseEntity<>(shopOrders, HttpStatus.OK);
        }

    }

    @GetMapping("/getu")
    public ResponseEntity<User> getu(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        User user = userService.findByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }*/
}
