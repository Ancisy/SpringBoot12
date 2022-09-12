package com.example.exerciseone.repository;

import com.example.exerciseone.model.Book;
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
    private ArrayList<Book> myBook = new ArrayList<>();
    public BookRepository(){
        try {
            File file = ResourceUtils.getFile("classpath:static/BookJson.json");
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<Book>> typeRefe = new TypeReference<List<Book>>() {};
            myBook.addAll(mapper.readValue(file, typeRefe));

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Book> getBook(){
        return myBook;
    }
}
