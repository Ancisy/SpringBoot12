package com.example.spring_security_login.controller;

import com.example.spring_security_login.entity.Address;
import com.example.spring_security_login.entity.User;
import com.example.spring_security_login.repository.UserRepository;
import com.example.spring_security_login.service.AddressService;
import com.example.spring_security_login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/login/v1")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    @GetMapping("/user")
    public ResponseEntity<?> getListUsers (){
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/address")
    public ResponseEntity<?> getList (){
        List<Address> addresses = addressService.getAllAddress();
        return ResponseEntity.ok(addresses);
    }
}
