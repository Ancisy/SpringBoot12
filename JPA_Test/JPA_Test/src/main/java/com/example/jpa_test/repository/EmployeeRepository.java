package com.example.jpa_test.repository;

import com.example.jpa_test.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("select e from Employee e where e.emailAddress = ?1 and e.lastName= ?2")
    Employee getAllEmployeesByEmailAndLastName(String emailAddress, String lastName);

    @Query("select e from Employee e where e.firstname = ?1 or e.lastName= ?2")
    List<Employee> getAllEmployeesByFirstNameOrLastName(String firstName, String lastName);
    List<Employee> findByLastNameOrderByFirstnameAsc(String lastName);
    Employee findByFirstnameIgnoreCase(String firstname);
    List<String> findAllByLastName();

}