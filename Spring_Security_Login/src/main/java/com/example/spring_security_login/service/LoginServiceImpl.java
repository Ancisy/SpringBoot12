package com.example.spring_security_login.service;


import com.example.spring_security_login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collections;
@Service
public class LoginServiceImpl implements LoginService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username la " + username);

        com.example.spring_security_login.entity.User user = userRepository.findByTaiKhoan(username);
        String password = user.getMatKhau();

        return new User(username,password, Collections.emptyList());
    }
}
