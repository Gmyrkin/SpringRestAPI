package ru.spb.db.rest.spring.exmp315.SpringRestAPI.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.spb.db.rest.spring.exmp315.SpringRestAPI.models.User;

public interface UserRepository extends JpaRepository<User,Long> {

}
