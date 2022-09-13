package com.example.exercisethree.repository;

import com.example.exercisethree.model.CustomerPojo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


@Repository
public class CustomerPoJoRepo {
    private List<CustomerPojo> myCustomerPojo = new ArrayList<>();
    private ConcurrentHashMap<Integer, CustomerPojo> myPojoHash = new ConcurrentHashMap<>();

    public CustomerPoJoRepo() {
        try {
            File resource = ResourceUtils.getFile("classpath:static/CustomerJS.json");
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<CustomerPojo>> typeRef = new TypeReference<List<CustomerPojo>>() {};
            myCustomerPojo.addAll(mapper.readValue(resource, typeRef));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<CustomerPojo> getBookList(){
        return myCustomerPojo;
    }

    public ConcurrentHashMap<Integer, CustomerPojo> getHashBook(){
        for(CustomerPojo c : myCustomerPojo){
            myPojoHash.put(c.getId(),c);
        }
        return myPojoHash;
    }
}
