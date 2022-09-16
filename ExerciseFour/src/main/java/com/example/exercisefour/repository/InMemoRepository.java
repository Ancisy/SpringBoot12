package com.example.exercisefour.repository;

import com.example.exercisefour.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoRepository {
    private List<Customer> customers = new ArrayList<>();
    private ConcurrentHashMap<Integer,Customer> myHashList;
    public InMemoRepository(){
        myHashList = new ConcurrentHashMap<>();
        List<Customer> listCustomer = List.of(
                new Customer(1,"Tran Dai Nghia","usa@gmail.com",83288232),
                new Customer(2,"Minh Dao","thailand@gmail.com",8322323),
                new Customer(3,"Minh Trang","laos@gmail.com",83223232)
    );

        for(Customer c : listCustomer){
            customers.add(c);
        }

        for(Customer c : customers){
            myHashList.put(c.getId(),c);
        }
    }

    public ConcurrentHashMap<Integer,Customer> getHashCustomerMap(){
        return myHashList;
    }

    //Delete
    public void deleteByID(int id){
        if(myHashList.containsKey(id)) {
            myHashList.remove(id);
        }
    }

    //Edit Customer
    public void editCustomers(Customer customer){
        if(myHashList.containsKey(customer.getId())){
            myHashList.replace(customer.getId(),customer);
        }
    }

    //Create Customer
    public void createCustomer(Customer customer){
        int id;
        if (myHashList.isEmpty()) {
            id = 1;
        } else {
            Customer lastCustomer = myHashList.get(myHashList.size());
            id = lastCustomer.getId()+1;
        }
        customer.setId(id);
        myHashList.put(customer.getId(),customer);
    }

    //Search
    public Customer searchCustomer(String email){
        List<Customer> list = myHashList.values().stream().toList();
        for(Customer c : list){
            if(c.getEmail().contains(email)){
                return c;
            }
        }
        return null;
    }

}
