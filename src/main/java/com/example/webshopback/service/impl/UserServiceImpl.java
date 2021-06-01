package com.example.webshopback.service.impl;

import com.example.webshopback.exception.UsernameExistException;
import com.example.webshopback.model.User;
import com.example.webshopback.model.UserAuthority;
import com.example.webshopback.repository.UserRepository;
import com.example.webshopback.security.AuthResponse;
import com.example.webshopback.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Data
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDetails userDetails = userRepository.findByUsername(s);
        if (userDetails == null) {
            throw new UsernameNotFoundException("Not found");
        }
        return userDetails;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow(() -> {
            return new NotFoundException("User not found");
        });
    }


    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User update(User user) {
        user.setId(user.getId());
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public UserDetails register(User user) {
        if (userRepository.existsUserByUsername(user.getUsername())) {
            throw new UsernameExistException("C003-User with name exist");
        } else {
            User newUser = new User();
            newUser.setUsername(user.getUsername());
            newUser.setPassword(passwordEncoder.encode(user.getPassword()));
            newUser.setEmail(user.getEmail());
            newUser.setAuthority(UserAuthority.USER);
            return userRepository.save(newUser);
        }
    }

    public UserDetails findByToken(String token) {
        return userRepository.findByToken(token);
    }

    public AuthResponse login(String username, String password) {
        User newUser = userRepository.findByUsername(username);
        if (newUser == null) {
            throw new UsernameNotFoundException(username);
        }

        if (!passwordEncoder.matches(password, newUser.getPassword())) {
            throw new UsernameNotFoundException(username);
        }

        newUser.setToken(UUID.randomUUID().toString());
        userRepository.save(newUser);
        return new AuthResponse(newUser.getToken(), newUser.getAuthority());
    }

    public void logout(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        user.setToken(null);
        userRepository.save(user);
    }
}
