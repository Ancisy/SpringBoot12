package com.example.spring_security_login.repository;

import com.example.spring_security_login.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}