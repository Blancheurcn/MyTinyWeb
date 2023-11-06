package com.example.MyTinyWeb;

import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<MyTinyUser, Integer> {

}
