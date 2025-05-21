package com.example.Automobile_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Automobile_app.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameAndPassword(String username, String password);
}
