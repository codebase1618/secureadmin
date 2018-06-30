package com.rss.repository;
 

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rss.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    User findByEmail(String email);
    List<User> findAll();
}
