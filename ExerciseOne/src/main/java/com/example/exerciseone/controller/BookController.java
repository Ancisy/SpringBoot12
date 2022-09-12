package com.example.exerciseone.controller;

import com.example.exerciseone.repository.BookRepository;
import com.example.exerciseone.model.Book;
import com.example.exerciseone.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/home")
public class BookController {
    private BookRepository bookRepo;
    private final BookService bookSer;
    @Autowired
    public BookController(BookRepository bookRepo, BookService bookSer){
        this.bookRepo = bookRepo;
        this.bookSer = bookSer;
    }

    @GetMapping
    public String home (){
        return "Home";
    }

    @GetMapping(value = "JsonSort", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Book> getJSON(){
        List<Book> result = new ArrayList<>();
        result = bookRepo.getBook();
        result = SortBookByYear(result);
        return result;
    }

    @GetMapping(value = "Excel")
    public ResponseEntity<?> getExel(){
        List<Book> result = new ArrayList<>();
        result = bookSer.getAllBook();
        return ResponseEntity.ok().body(result);
    }

    public static List<Book> SortBookByAuthor(List<Book> result){
        result.sort(new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o2.getAuthor().compareTo(o1.getAuthor());
            }
        });
        return result;
    }

    public static List<Book> SortBookByTitle(List<Book> result){
        result.sort(new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o2.getTitle().compareTo(o1.getTitle());
            }
        });
        return result;
    }

    public static List<Book> SortBookByYear(List<Book> result){
        result.sort(new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o2.getYear()-o1.getYear();
            }
        });
        return result;
    }



}
