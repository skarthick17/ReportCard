package com.example.reportcard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.reportcard.model.User;
import com.example.reportcard.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User login(String username, String password) {

        User user = userRepository.findByUsername(username);

        if(user != null && user.getPassword().equals(password)) {
            return user;
        }

        return null;
    }
}