package com.example.exerciseone.service;

import com.example.exerciseone.model.Book;
import com.poiji.bind.Poiji;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;
@Service
public class BookService {
    public List<Book> getAllBook(){
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:static/BookExel.xlsx");
            List<Book> myBookList = Poiji.fromExcel(file,Book.class);
            return myBookList;
        } catch (FileNotFoundException e) {
            return Collections.emptyList();
        }


    }
}
