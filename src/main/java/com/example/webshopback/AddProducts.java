package com.example.webshopback;


import com.example.webshopback.model.Product;
import com.example.webshopback.repository.ProductRepository;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AddProducts {

    public AddProducts(ProductRepository productRepository){
        Product product = new Product(1L,"Tv Samsung", "UHD 55 cali",5000D,3, "https://images.pexels.com/photos/6782360/pexels-photo-6782360.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
        productRepository.save(product);
        Product product1 = new Product(2L,"Radio", "Sony STEREO",700D,25,"https://images.pexels.com/photos/1846387/pexels-photo-1846387.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
        productRepository.save(product1);
        Product product2 = new Product(3L,"xBox One", "Zestaw z 3 grami",1500D,10,"https://images.pexels.com/photos/5626726/pexels-photo-5626726.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
        productRepository.save(product2);
        Product product3 = new Product(4L,"PS4 pro", "z 2 padami",1800D,15,"https://images.pexels.com/photos/442576/pexels-photo-442576.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
        productRepository.save(product3);
        Product product4 = new Product(5L,"Telefon Nokia", "Model 34626",750D,9, "https://images.pexels.com/photos/4957/person-woman-hand-smartphone.jpg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
        productRepository.save(product4);
        Product product5 = new Product(6L,"Zegarek TIMEX", "Wodoodporny",500D,20,"https://images.pexels.com/photos/4567357/pexels-photo-4567357.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
        productRepository.save(product5);
        Product product6 = new Product(7L,"Laptop ASUS", "19 cali, Intel Core i7",5000D,6,"https://images.pexels.com/photos/5474293/pexels-photo-5474293.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
        productRepository.save(product6);
        Product product7 = new Product(8L,"Aparat Nikon", "D90 12MP + obiektyw",2000D,5,"https://images.pexels.com/photos/51383/photo-camera-subject-photographer-51383.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
        productRepository.save(product7);
        Product product8 = new Product(9L,"Laptop ACER", "15 cali, Intel Core i5",3000D,7,"https://images.pexels.com/photos/6598/coffee-desk-laptop-notebook.jpg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
        productRepository.save(product8);
        Product product9 = new Product(10L,"Aparat Canon", "D5 30MP + obiektyw",10000D,2,"https://images.pexels.com/photos/1093065/pexels-photo-1093065.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
        productRepository.save(product9);

    }
}
