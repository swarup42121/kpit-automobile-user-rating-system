package com.example.Automobile_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Automobile_app.model.Car;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByNameContainingIgnoreCase(String name);
    Car findByNameIgnoreCase(String name);
    Car findByName(String name);

}
