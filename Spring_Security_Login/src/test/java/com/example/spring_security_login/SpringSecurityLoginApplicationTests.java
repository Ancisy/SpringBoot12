package com.example.spring_security_login;

import com.example.spring_security_login.entity.User;
import com.example.spring_security_login.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SpringSecurityLoginApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Rollback(value = false)
    void data_user(){
        User user = User.builder()
                .taiKhoan("ancisy")
                .matKhau("hello")
                .email("nghia@gmail.com")
                .hoten("Tran Dai Nghia")
                .soDienThoai("32141234")
                .diachi("ha noi")
                .isActive(true)
                .build();

        User user1 = User.builder()
                .taiKhoan("hello")
                .matKhau("12345")
                .email("hello@gmail.com")
                .hoten("Tran Dai")
                .soDienThoai("332412344")
                .diachi("hai phong")
                .isActive(false)
                .build();

        User user2 = User.builder()
                .taiKhoan("asdfsdf")
                .matKhau("123456")
                .email("asdfsdf@gmail.com")
                .hoten("sadfasdf")
                .soDienThoai("332412344")
                .diachi("sdafdsf")
                .isActive(false)
                .build();
        userRepository.saveAll(List.of(user,user1,user2));
    }
}
