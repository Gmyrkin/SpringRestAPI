package ru.spb.db.rest.spring.exmp315.SpringRestAPI.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.spb.db.rest.spring.exmp315.SpringRestAPI.models.User;
import ru.spb.db.rest.spring.exmp315.SpringRestAPI.service.UserService;
import ru.spb.db.rest.spring.exmp315.SpringRestAPI.service.UserServiceImpl;

@RestController
@RequestMapping()
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping("/sayHello")
//    public String sayHello(){
//        return "Hello USERFUCKER";
//    }

    @GetMapping("/")
    public String getIndex() {
        return "index";
    }


}

