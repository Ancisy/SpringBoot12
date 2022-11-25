package com.example.jpa_test.entity;

import lombok.*;

import javax.persistence.*;
@ToString
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
@NamedQuery(name = "Employee.findAllByLastName",
        query = "SELECT e.lastName FROM Employee e "
)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    private String emailAddress;
    private String firstname;
    private String lastName;
}
