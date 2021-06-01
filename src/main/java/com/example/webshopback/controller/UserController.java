package com.example.webshopback.controller;

import com.example.webshopback.model.User;
import com.example.webshopback.security.AuthResponse;
import com.example.webshopback.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class UserController {

    @Autowired
    UserService userService;

    @Operation(summary = "Get users list")
    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getAll();
        if (CollectionUtils.isEmpty(users)) {
            throw new EntityNotFoundException();
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Operation(summary = "Get user by id")
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Operation(summary = "User register with username and password")
    @PostMapping("/register")
    public UserDetails register(@Valid @RequestBody User user) {
        return userService.register(user);
    }

    @Operation(summary = "User details")
    @GetMapping("/details")
    public UserDetails details() {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Operation(summary = "User login with username and password")
    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody User user) {
        return userService.login(user.getUsername(), user.getPassword());
    }

    @Operation(summary = "User logout")
    @PostMapping("/logout")
    public boolean logout() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userService.logout(userDetails.getUsername());
        return true;
    }

    @Operation(summary = "Delete user by id")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Edit username")
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User user) {
        User newUser = userService.getUserById(id);
        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        userService.update(newUser);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
