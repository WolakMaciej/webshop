package com.example.webshopback.controller;

import com.example.webshopback.model.Product;
import com.example.webshopback.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = productService.getAll();
        if (CollectionUtils.isEmpty(products)) {
            throw new EntityNotFoundException();
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createNewProduct(@Valid @RequestBody Product product) throws IOException {

        Product persistedProduct = productService.createNew(product);
        return new ResponseEntity<>(persistedProduct, HttpStatus.CREATED);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable long id) {
        Product product = productService.getOne(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable long id) {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable long id, @Valid @RequestBody Product product) {
        Product newProduct = productService.getOne(id);
        newProduct.setName(product.getName());
        newProduct.setDescription(product.getDescription());
        newProduct.setPrice(product.getPrice());
        productService.update(newProduct);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }


}
