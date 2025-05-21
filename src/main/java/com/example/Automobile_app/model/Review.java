package com.example.Automobile_app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Column(length = 2000)
    private String content;

    private int rating; // ✅ Added this field

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    // Constructors
    public Review() {}

    public Review(String username, String content, int rating, Car car) {
        this.username = username;
        this.content = content;
        this.rating = rating;
        this.car = car;
    }

    // Getters and Setters
    public Long getId() { return id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public int getRating() { return rating; } // ✅ Getter
    public void setRating(int rating) { this.rating = rating; } // ✅ Setter

    public Car getCar() { return car; }
    public void setCar(Car car) { this.car = car; }
}
