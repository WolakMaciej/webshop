package com.example.webshopback;

import com.example.webshopback.model.User;
import com.example.webshopback.model.UserAuthority;
import com.example.webshopback.repository.UserRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Start {

    public Start(UserRepository userRepository, PasswordEncoder passwordEncoder){
        User userAdmin = new User();
        userAdmin.setId(1L);
        userAdmin.setUsername("Admin");
        userAdmin.setPassword(passwordEncoder.encode("admin123"));
        userAdmin.setEmail("admin@gmail.com");
        userAdmin.setAuthority(UserAuthority.ADMIN);
        userRepository.save(userAdmin);

/*

        User userUser = new User();
        userUser.setUsername("User");
        userUser.setPassword(passwordEncoder.encode("user1234"));
        userUser.setEmail("user@gmail.com");
        userUser.setAuthority(UserAuthority.USER);
        userRepository.save(userUser);
*/


    }
}
