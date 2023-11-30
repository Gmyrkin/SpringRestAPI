package ru.spb.db.rest.spring.exmp315.SpringRestAPI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spb.db.rest.spring.exmp315.SpringRestAPI.models.User;
import ru.spb.db.rest.spring.exmp315.SpringRestAPI.repository.UserRepository;
import ru.spb.db.rest.spring.exmp315.SpringRestAPI.HandlerExeption.UserNotFoundException;

import java.util.List;
import java.util.Optional;


@Service // данный класс компонент Spring
@Transactional (readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById (Long id){
        Optional<User> foundUser = userRepository.findById(id);
        return foundUser.orElseThrow(UserNotFoundException::new);
     }


     @Transactional // потому что изменяю данные в базе данных
     public void saveUser (User user){
        userRepository.save(user);
     }

    @Transactional
    public void deleteById (Long id){
        userRepository.deleteById(id);

    }

    @Transactional
    public String updateUser (User user) {
        User oldUser = userRepository.findById(user.getId()).get();
        String oldPassword = oldUser.getPassword();
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            user.setPassword(oldPassword);
        } else {
            user.setPassword(oldPassword); // ДОБАВИТЬ ПРОВЕРКУ ПАРОЛЯ
        }
        userRepository.save(user);
        return oldPassword;
    }


}
