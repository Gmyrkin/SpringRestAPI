package ru.spb.db.rest.spring.exmp315.SpringRestAPI.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.spb.db.rest.spring.exmp315.SpringRestAPI.models.Book;
import ru.spb.db.rest.spring.exmp315.SpringRestAPI.repository.ServiceResponse;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {

    List<Book> bookStore = new ArrayList<>();

    @PostMapping("/saveBook")
    public ResponseEntity<Object> addBook(@RequestBody Book book) {
        bookStore.add(book);
        ServiceResponse<Book> response = new ServiceResponse<Book>("success", book);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @GetMapping("/getBooks")
    public ResponseEntity<Object> getAllBooks() {
        ServiceResponse<List<Book>> response = new ServiceResponse<>("success", bookStore);
        System.out.println("я в методe getBOOKS");
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }
}
