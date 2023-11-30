package ru.spb.db.rest.spring.exmp315.SpringRestAPI.controllers;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.spb.db.rest.spring.exmp315.SpringRestAPI.models.User;
import ru.spb.db.rest.spring.exmp315.SpringRestAPI.service.UserService;
import ru.spb.db.rest.spring.exmp315.SpringRestAPI.HandlerExeption.UserErrorResponse;
import ru.spb.db.rest.spring.exmp315.SpringRestAPI.HandlerExeption.UserNotCreatedException;
import ru.spb.db.rest.spring.exmp315.SpringRestAPI.HandlerExeption.UserNotFoundException;

import java.util.List;

@RestController // @Controller + @ResponseBody над каждым методом (если нуждно возвращать данные)
@RequestMapping("/user")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.findAll(); // Jackson конвертирует эти объекты в JSON

    }

    @PostMapping
    public ResponseEntity<HttpStatus> createUser(@RequestBody @Valid User user,
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
        userService.saveUser(user); // отправляем НТТР ответ с пустым телом и статусом 200 (всё прошло успешно)
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handlerException(UserNotFoundException e) {

        UserErrorResponse response = new UserErrorResponse(
                "User with this id wasn't found !",
                System.currentTimeMillis()
        );

        // в НТТР отвтет будет тело ответа (response) который преброзован в JSON
        // а также будет отображён статус в заголовке

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); // @ResponseEntity объёртка над исключением
        // NOT_FOUND - 404 ошибка
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handlerException(UserNotCreatedException e) {
        UserErrorResponse response = new UserErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); // 400 ошибка

    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);

    }


//    @PostMapping ("/update/{id}")
//    public ResponseEntity<HttpStatus> updateUser(@RequestBody User user, @PathVariable Long id) {
//        userService.updateUser(user);
//        return ResponseEntity.ok(HttpStatus.OK);
//    }

    @PatchMapping("/update/{id}")
    public String update(@ModelAttribute(value = "user") User user, @PathVariable("id") Long id) {
        return userService.updateUser(user);

    }
}
