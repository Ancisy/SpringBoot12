package com.example.exercisetwo.controller;

import com.example.exercisetwo.model.Person;
import com.example.exercisetwo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class PersonController {
    private BookRepository bookRepo;

    @Autowired
    public PersonController(BookRepository bookRepo){
        this.bookRepo = bookRepo;
    }

    @GetMapping
    public List<Person> home(){
        List<Person> result = new ArrayList<>();
        result = bookRepo.getBook();
        return result;
    }

    @GetMapping("/person")
    public List<Person> getSortedList(@RequestParam("sort") String a, @RequestParam("direction") String b){
        List<Person> result = bookRepo.getBook();
        Map<String,Integer> newList = new HashMap<String,Integer>();
        if(a.equals("name")){
            result = SortBookByTitle(result,b);
            return result;
        }else if(a.equals("nationality")){
            result = SortBookByNation(result,b);
        }else if(a.equals("age")){
            result = SortBookByAge(result,b);
        }
        return result;
    }

    public static List<Person> SortBookByTitle(List<Person> result,String direction){
        result.sort(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                if(direction.equals("desc"))
                return o2.getName().compareTo(o1.getName());
                else
                    return o1.getName().compareTo(o2.getName());
            }
        });
        return result;
    }

    public static List<Person> SortBookByNation(List<Person> result,String direction){
        result.sort(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                if(direction.equals("desc"))
                    return o2.getNationality().compareTo(o1.getNationality());
                else
                    return o1.getNationality().compareTo(o2.getNationality());
            }
        });
        return result;
    }

    public static List<Person> SortBookByAge(List<Person> result,String direction){
        result.sort(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                if(direction.equals("desc"))
                    return o2.getAge() - o1.getAge();
                else
                    return o1.getAge()- o2.getAge();
            }
        });
        return result;
    }







}
