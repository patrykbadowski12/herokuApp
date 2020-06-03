package com.studia.HerokuApp;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByName(String name);

    @Override
    List<User> findAll();
}
