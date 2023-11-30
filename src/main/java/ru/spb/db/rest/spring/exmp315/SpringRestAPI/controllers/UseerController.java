package ru.spb.db.rest.spring.exmp315.SpringRestAPI.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.spb.db.rest.spring.exmp315.SpringRestAPI.models.User;
import ru.spb.db.rest.spring.exmp315.SpringRestAPI.service.UserService;

@RestController
@RequestMapping("/user")
public class UseerController {

    private final UserService userService;

    public UseerController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/sayHello")
    public String sayHello(){
        return "Hello FUCKER";
    }

    @GetMapping("/{id}")
    public User getOneUser(@PathVariable("id") Long id){
        return userService.findById(id); // Jackson конвертирует в JSON
    }

}

