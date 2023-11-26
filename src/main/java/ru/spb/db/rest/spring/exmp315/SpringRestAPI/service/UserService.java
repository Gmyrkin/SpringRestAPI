package ru.spb.db.rest.spring.exmp315.SpringRestAPI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.spb.db.rest.spring.exmp315.SpringRestAPI.models.User;
import ru.spb.db.rest.spring.exmp315.SpringRestAPI.repository.UserRepository;
import ru.spb.db.rest.spring.exmp315.SpringRestAPI.utill.UserErrorResponse;
import ru.spb.db.rest.spring.exmp315.SpringRestAPI.utill.UserNotFoundException;

import java.util.List;
import java.util.Optional;


@Service // данный класс компонент Spring
@Transactional (readOnly = true)
public class UserService {

    //инициализация (старый способ @Autowired)
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findOne(Long id){
        Optional<User> foundUser = userRepository.findById(id);
        return foundUser.orElseThrow(UserNotFoundException::new);
     }


     @Transactional // потому что изменяю данные в базе данных
     public void save(User user){
        userRepository.save(user);
     }

}
