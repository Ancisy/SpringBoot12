package com.example.department.service;

import com.example.department.entity.Account;
import com.example.department.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
@Service
public class AccountService implements AccountServiceImpl{

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username la " + username);
        Account account = accountRepository.findByUsername(username);
        String password = account.getPassword();
        return new User(username,password, AuthorityUtils.createAuthorityList(account.getRole()));
    }
}
