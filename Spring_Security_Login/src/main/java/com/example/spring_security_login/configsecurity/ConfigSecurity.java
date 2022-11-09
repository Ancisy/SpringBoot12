package com.example.spring_security_login.configsecurity;

import com.example.spring_security_login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

    @Configuration
    public class ConfigSecurity extends WebSecurityConfigurerAdapter {
        @Autowired
        private LoginService loginservice;

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(loginservice).passwordEncoder(new BCryptPasswordEncoder());
        }
    }

