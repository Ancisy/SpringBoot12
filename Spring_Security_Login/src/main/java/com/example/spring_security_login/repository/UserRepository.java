package com.example.spring_security_login.repository;

import com.example.spring_security_login.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByTaiKhoan(String taiKhoan);


}