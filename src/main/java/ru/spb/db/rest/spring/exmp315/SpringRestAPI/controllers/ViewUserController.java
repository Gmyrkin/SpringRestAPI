package ru.spb.db.rest.spring.exmp315.SpringRestAPI.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewUserController {

    @GetMapping("/usertable")
    public String usertable() {
        return "usertable";
    }
}
