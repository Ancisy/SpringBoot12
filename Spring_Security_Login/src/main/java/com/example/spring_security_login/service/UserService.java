package com.example.spring_security_login.service;

import com.example.spring_security_login.entity.User;
import com.example.spring_security_login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<User> getUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }
}
