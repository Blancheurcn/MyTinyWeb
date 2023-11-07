package com.example.MyTinyWeb;

import org.springframework.data.repository.CrudRepository;


public interface MyTinyUserRepository extends CrudRepository<MyTinyUser, Integer> {
    MyTinyUser findByNameAndPassword(String name, String password);
}
