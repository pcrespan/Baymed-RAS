package com.ras.baymax.services;

import com.ras.baymax.entities.User;
import com.ras.baymax.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return Optional.ofNullable(userRepository.save(user)).orElseThrow(() -> new IllegalArgumentException("User already exists"));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
