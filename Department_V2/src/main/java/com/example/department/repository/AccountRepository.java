package com.example.department.repository;

import com.example.department.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByUsername(String username);

}