package com.example.webshopback.service.impl;

import com.example.webshopback.model.ItemCart;
import com.example.webshopback.repository.ItemCartRepository;
import com.example.webshopback.service.ItemCartService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Data
public class ItemCartServiceImpl implements ItemCartService {

    @Autowired
    private ItemCartRepository itemCartRepository;

    @Override
    public List<ItemCart> getAll() {
        return itemCartRepository.findAll();
    }

    @Override
    public ItemCart createNew(ItemCart itemCart) {
        itemCart.setId(null);
       // itemCart.getProduct().setTotalInOrder((long) itemCart.getQuantity());
        return itemCartRepository.save(itemCart);
    }

    @Override
    public ItemCart getOne(long id) {
        return itemCartRepository.findById(id).orElseThrow(() -> {
            return new NotFoundException("ItemCart not found");
        });
    }

    @Override
    public void delete(long id) {
        itemCartRepository.deleteById(id);
    }

    @Override
    public void update(ItemCart itemCart) {
        itemCart.setId(itemCart.getId());
        itemCartRepository.save(itemCart);
    }

}
