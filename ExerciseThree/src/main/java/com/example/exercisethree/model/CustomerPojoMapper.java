package com.example.exercisethree.model;

import com.example.exercisethree.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CustomerPojoMapper {
    CustomerPojoMapper INSTANCE = Mappers.getMapper(CustomerPojoMapper.class);


    //Map CustomerPojo to Customer
    List<Customer> pojotoCustomer(List<CustomerPojo> pojo);
}
