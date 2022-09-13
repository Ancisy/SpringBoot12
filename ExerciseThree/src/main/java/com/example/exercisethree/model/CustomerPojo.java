package com.example.exercisethree.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerPojo {
    private int id;
    private String name;
    private String email;
    private long telephone;
}
