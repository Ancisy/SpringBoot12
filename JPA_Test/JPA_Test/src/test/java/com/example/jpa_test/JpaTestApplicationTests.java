package com.example.jpa_test;

import com.example.jpa_test.dto.UserDto;
import com.example.jpa_test.entity.Employee;
import com.example.jpa_test.entity.User;
import com.example.jpa_test.repository.CategoryRepository;
import com.example.jpa_test.repository.EmployeeRepository;
import com.example.jpa_test.repository.ProductRepository;
import com.example.jpa_test.repository.UserRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class JpaTestApplicationTests {
    @Autowired
    private Faker faker;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    @Rollback(value = false)
    void save_employee() {
        for (int i = 0; i < 10; i++) {
            Employee employee = Employee.builder()
                    .emailAddress(faker.internet().emailAddress())
                    .firstname(faker.name().firstName())
                    .lastName(faker.name().lastName())
                    .build();
            employeeRepository.save(employee);
        }
    }

    @Test
    void test_jpa_employee_1(){
        System.out.println("Ket qua" + employeeRepository.getAllEmployeesByEmailAndLastName("lincoln.christiansen@gmail.com","Medhurst"));
    }

    @Test
    void test_jpa_employee_2(){
        System.out.println("Ket qua" + employeeRepository.getAllEmployeesByFirstNameOrLastName("Jorge","Medhurst"));
    }

    @Test
    void test_jpa_employee_3(){
        System.out.println("Ket qua" + employeeRepository.findByLastNameOrderByFirstnameAsc("Medhurst"));
    }

    @Test
    void test_jpa_employee_4(){
        System.out.println("Ket qua" + employeeRepository.findByFirstnameIgnoreCase("antonio"));
    }

    @Test()
    void test_name_query(){
        System.out.println(employeeRepository.findAllByLastName());
    }

    @Test()
    @Rollback(value = false)
    void test(){
        categoryRepository.deleteById(1);
    }

    @Test
    void save_user() {
        for (int i = 0; i < 10; i++) {
            User user = User.builder()
                    .name(faker.name().fullName())
                    .email(faker.internet().emailAddress())
                    .password("111")
                    .build();
            userRepository.save(user);
        }
    }

    @Test
    void method_query_toUserDto(){
        List<UserDto> result = userRepository.findAll().stream().map(post -> modelMapper.map(post, UserDto.class))
                .collect(Collectors.toList());

        System.out.println(result);
    }



}
