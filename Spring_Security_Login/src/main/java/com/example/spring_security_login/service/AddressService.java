package com.example.spring_security_login.service;

import com.example.spring_security_login.entity.Address;
import com.example.spring_security_login.entity.User;
import com.example.spring_security_login.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public List<Address> getAllAddress(){
        List<Address> addresses = addressRepository.findAll();
        return addresses;
    }


}
