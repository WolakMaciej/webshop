package com.example.webshopback.service;

import com.example.webshopback.model.User;
import com.example.webshopback.security.AuthResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.transaction.Transactional;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    public static final String USERNAME = "Maciej";
    public static final String EMAIL = "maciejwolak@gmail.com";
    public static final String PASSWORD = "12345678";
    public static final String AUTHORITY = "["+"USER"+"]";

/*
    @Test
    void getAll() {
    }

    @Test
    void getUserById() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }
*/
    @Test
    void findByToken() {
        User user = new User();
        user.setUsername(USERNAME);
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);
        UserDetails userDetails = userService.register(user);
        AuthResponse authResponse = userService.login(USERNAME,PASSWORD);
        assertNotNull(authResponse.getAuthToken(),"Not logged in!");
        assertEquals(USERNAME, userService.findByToken(authResponse.getAuthToken()).getUsername(),"User not found!");

    }

    @Test
    void register() {
        User user = new User();
        user.setUsername(USERNAME);
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);
        Collection<? extends GrantedAuthority> userA = userService.register(user).getAuthorities();
        String authority = userA.toString();
        assertEquals(AUTHORITY,authority,"Not register!");

    }

    @Test
    void login() {
        User user = new User();
        user.setUsername(USERNAME);
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);
        UserDetails userDetails = userService.register(user);
        AuthResponse authResponse = userService.login(USERNAME,PASSWORD);
        assertNotNull(authResponse.getAuthToken(),"Not logged in!");
    }

    @Test
    void logout() {
    User user = new User();
        user.setUsername(USERNAME);
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);
        UserDetails userDetails = userService.register(user);
        AuthResponse authResponse = userService.login(USERNAME,PASSWORD);
        assertNotNull(authResponse.getAuthToken(),"Not logged in!");
        userService.logout(USERNAME);
        assertNull(userService.findByToken(authResponse.getAuthToken()),"Not logged out");

    }
}
