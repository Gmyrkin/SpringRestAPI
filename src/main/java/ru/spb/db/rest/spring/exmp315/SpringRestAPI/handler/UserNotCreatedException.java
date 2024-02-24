package ru.spb.db.rest.spring.exmp315.SpringRestAPI.handler;

public class UserNotCreatedException extends RuntimeException {

    public UserNotCreatedException (String msg){
        super(msg); // передаю в RuntimeException сообщение об ошибке из UserErrorResponse
    }
}
