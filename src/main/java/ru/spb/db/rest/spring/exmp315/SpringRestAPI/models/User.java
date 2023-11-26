package ru.spb.db.rest.spring.exmp315.SpringRestAPI.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;


@Entity // связать данной сущности с БД
@Table(name="users") // название табл в БД
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column// генерация ID на 1 (указано при создании табл в БД)
    private Long id;

    @Column(name="first_name") // явное указание колонок в табл в БД (т.к. есть нижнее подчеркивание)
    @NotEmpty(message="Name should not empty")
    @Size(min = 3,max = 30,message = "Name between 3 and 30")
    private String firstName;


    @Column(name="last_name")
    @NotEmpty(message="Name should not empty")
    @Size(min = 3,max = 30,message = "Name between 3 and 30")
    private String lastName;

    @Column (name="password")
    @Min(value = 3,message = "Min 3 digits")
    private String password;

    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
