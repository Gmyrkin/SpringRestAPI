package ru.spb.db.rest.spring.exmp315.SpringRestAPI.controllers;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.spb.db.rest.spring.exmp315.SpringRestAPI.handler.UserErrorResponse;
import ru.spb.db.rest.spring.exmp315.SpringRestAPI.handler.UserNotCreatedException;
import ru.spb.db.rest.spring.exmp315.SpringRestAPI.models.User;
import ru.spb.db.rest.spring.exmp315.SpringRestAPI.service.UserService;
import java.util.List;


@RestController // @Controller + @ResponseBody над каждым методом (если нуждно возвращать данные)
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create/user")
    public ResponseEntity<?> createNewUser(@RequestBody @Valid User user,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }

            throw new UserNotCreatedException(errorMsg.toString());
        }

        User createUser = userService.saveUser(user);
        // отправляем НТТР ответ с пустым телом и статусом 200 (всё прошло успешно)
        return new ResponseEntity<Object>(createUser, HttpStatus.CREATED);

    }


    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {
        User user = userService.findUserById(id);
        if (user == null || user.getPassword().isEmpty()) {
            UserErrorResponse userErrorResponse = new UserErrorResponse(
                    "User with this id wasn't found !",
                    System.currentTimeMillis()
            );

            return new ResponseEntity<Object>(userErrorResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Object>(user, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<?> getAllUsers() {
        System.out.println("я попал в метод getAllUsers");
        List<User> userList = userService.findAllUsers();
        return new ResponseEntity<Object>(userList, HttpStatus.OK);
    }


    @PutMapping(value = "/update/user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        user.setId(id);
        User updatedUser = userService.updateUser(user);
        return new ResponseEntity<Object>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/delete/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        UserErrorResponse userErrorResponse = new UserErrorResponse(
                "User has been deleted successfully !",
                System.currentTimeMillis()
        );
        return new ResponseEntity<Object>(userErrorResponse, HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handlerException(UserNotCreatedException e) {
        UserErrorResponse response = new UserErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); // 400 ошибка

    }


}

