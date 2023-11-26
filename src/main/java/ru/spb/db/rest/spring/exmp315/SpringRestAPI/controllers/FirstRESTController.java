package ru.spb.db.rest.spring.exmp315.SpringRestAPI.controllers;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class FirstRESTController {

    @GetMapping("/sayHello")
    public String sayHello(){
        return "Hello FUCKER";
    }
}

