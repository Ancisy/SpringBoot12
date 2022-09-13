package com.example.exercisethree.controller;

import com.example.exercisethree.entities.Customer;
import com.example.exercisethree.model.CustomerPojo;
import com.example.exercisethree.model.CustomerPojoMapper;
import com.example.exercisethree.otd.CustomerPojoRequest;
import com.example.exercisethree.repository.CustomerPoJoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class CustomerController {

    private CustomerPoJoRepo myPojo;

    ConcurrentHashMap<Integer, CustomerPojo> myHashList;
    @Autowired
    public CustomerController(CustomerPoJoRepo myPojo){
        this.myPojo = myPojo;
        myHashList= new ConcurrentHashMap<>();
        myHashList = myPojo.getHashBook();
    }
    //GET
    @GetMapping(value = "/home")
    public List<Customer> getCustomerList(){
        List<Customer> myCustomer = CustomerPojoMapper
                .INSTANCE
                .pojotoCustomer(myHashList.values().stream().toList());
        return myCustomer;
    }
    //POST
    @PostMapping
    public CustomerPojo postCustomerList(@RequestBody CustomerPojoRequest pojoRequest){
        Random number = new Random();
        int newID = number.nextInt(11,100);
        CustomerPojo newCustomer = new CustomerPojo(newID,pojoRequest.name(),pojoRequest.email(),pojoRequest.telephone());
        myHashList.put(newID,newCustomer);
        return newCustomer;
    }


    //Find
    @GetMapping(value ="/{id}")
    public CustomerPojo findCustomer(@PathVariable("id") int id){
        return myHashList.get(id);
    }

    //PUT
    @PutMapping(value="/{id}")
    public CustomerPojo putCustomerList(@PathVariable("id") int id, @RequestBody CustomerPojoRequest pojoRequest){
        CustomerPojo updatedCustomer = new CustomerPojo(id,pojoRequest.name(),pojoRequest.email(),pojoRequest.telephone());
        myHashList.replace(id,updatedCustomer);

        return updatedCustomer;
    }

    //DELETE
    @DeleteMapping(value="/{id}")
    public CustomerPojo deleteCustomer(@PathVariable("id") int id){
        CustomerPojo deletedPojo = myHashList.remove(id);
        return deletedPojo;
    }


}
