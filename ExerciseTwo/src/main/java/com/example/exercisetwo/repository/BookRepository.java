package com.example.exercisetwo.repository;

import com.example.exercisetwo.model.Person;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Repository
public class BookRepository {
    private ArrayList<Person> person = new ArrayList<>();
    public BookRepository(){
        try {
            File file = ResourceUtils.getFile("classpath:static/PersonJS.json");
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<Person>> typeRefe = new TypeReference<List<Person>>() {};
            person.addAll(mapper.readValue(file, typeRefe));

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Person> getBook(){
        return person;
    }
}
