package com.example.webshopback.repository;

import com.example.webshopback.model.User;
import com.example.webshopback.model.UserAuthority;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    public static final String USERNAME = "Maciej";

    @BeforeEach
    void setUp(){
        User user = new User();
        user.setUsername("Maciej");
        user.setEmail("maciejwolak@gmail.com");
        user.setPassword("12345678");
        user.setAuthority(UserAuthority.USER);
        user.setToken("abcd1234");

        assertNull(user.getId());

        userRepository.save(user);

        assertNotNull(user.getId());
    }

    @Test
    void findByUsername() {
        User user = userRepository.findByUsername(USERNAME);
        assertEquals(USERNAME,user.getUsername(),"Invalid Name Found!");
    }

    @Test
    void findByToken() {
        User user = userRepository.findByToken("abcd1234");
        assertEquals("abcd1234",user.getToken(),"Invalid token");
    }

    @Test
    void existsUserByUsername() {
        assertTrue(userRepository.existsUserByUsername("Maciej"),"The name does not exist in the database");
    }

    @Test
    void testEditUser(){
        User user = userRepository.findByUsername(USERNAME);
        user.setUsername("Tomek");
        userRepository.save(user);
        assertEquals("Tomek",user.getUsername(),"The name is not change");
    }

    @Test
    void deleteEditUser(){
        long userId = userRepository.findByUsername(USERNAME).getId();
        userRepository.deleteById(userId);
        assertNull(userRepository.findByUsername(USERNAME),"User exist");
    }

}
