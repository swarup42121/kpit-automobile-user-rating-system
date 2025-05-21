package com.example.Automobile_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Automobile_app.model.Car;
import com.example.Automobile_app.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByCar(Car car);
}
