package ru.spb.db.rest.spring.exmp315.SpringRestAPI.handler;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException (String msg){
        super(msg); // передаю в RuntimeException сообщение об ошибке из UserErrorResponse
    }
}
