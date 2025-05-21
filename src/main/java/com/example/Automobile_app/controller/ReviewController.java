// ReviewController.java
package com.example.Automobile_app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Automobile_app.model.Car;
import com.example.Automobile_app.model.Review;
import com.example.Automobile_app.repository.CarRepository;
import com.example.Automobile_app.repository.ReviewRepository;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "*")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private CarRepository carRepository;

    @PostMapping
    public Review submitReview(@RequestBody ReviewRequest reviewRequest) {
        // Find the car by name
        Car car = carRepository.findByName(reviewRequest.getCarName());
        if (car == null) {
            throw new RuntimeException("Car not found");
        }

        // Create a new review
        Review review = new Review();
        review.setCar(car);
        review.setRating(reviewRequest.getRating());
        review.setContent(reviewRequest.getReviewText());

        // Save and return the review
        return reviewRepository.save(review);
    }

    // GET endpoint to return all cars with their reviews
    @GetMapping("/all")
    public List<Map<String, Object>> getAllCarsWithReviews() {
        List<Car> cars = carRepository.findAll();

        // Debug log all car names fetched
        System.out.println("Cars fetched from DB:");
        for (Car c : cars) {
            System.out.println("Car name: '" + c.getName() + "'");
        }

        // Map car names to image filenames
        Map<String, String> carImageMap = getCarImageMap();

        return cars.stream().map(car -> {
            // Set imageUrl for each car based on name
            String imageUrl = carImageMap.getOrDefault(car.getName(), "default_car.png");
            car.setImageUrl(imageUrl);

            // Debug log for imageUrl
            System.out.println("Setting imageUrl for car: " + car.getName() + " -> " + imageUrl);

            Map<String, Object> carWithReviews = new HashMap<>();
            carWithReviews.put("car", car);
            carWithReviews.put("reviews", reviewRepository.findByCar(car));
            return carWithReviews;
        }).collect(Collectors.toList());
    }

    // Private method to map car names to image filenames
    private Map<String, String> getCarImageMap() {
        Map<String, String> map = new HashMap<>();
        map.put("Tesla Model S", "Tesla_Model_S.jfif");
        map.put("Audi e-tron GT", "Audi_e-tron_GT.jfif");
        map.put("BMW i7", "Bmw_i7.jfif");
        map.put("Ford Mustang Mach-E", "Ford_Mustang_Mach-E.jfif");
        map.put("Hyundai Ioniq 6", "Hyundai_Ioniq 6.jfif");
        map.put("Kia EV6", "kia_ev6.jfif");
        map.put("Lucid Air", "lucid__air.jfif");
        map.put("Mercedes Benz EQE", "mercedes_benz_eqe.jfif");
        map.put("Nissan Leaf", "nissan_leaf.jfif");
        map.put("Porsche Taycan", "porsche_tycan.jfif");
        // Add more mappings as needed
        return map;
    }

    // Controller to serve the all_reviews.html page
    @Controller
    public static class PageController {
        @GetMapping("/all_reviews")
        public String allReviewsPage(Model model) {
            return "all_reviews";
        }
    }

    // Inner static class for receiving review data in request body
    public static class ReviewRequest {
        private String carName;
        private Integer rating;
        private String reviewText;

        // Getters and Setters
        public String getCarName() {
            return carName;
        }

        public void setCarName(String carName) {
            this.carName = carName;
        }

        public Integer getRating() {
            return rating;
        }

        public void setRating(Integer rating) {
            this.rating = rating;
        }

        public String getReviewText() {
            return reviewText;
        }

        public void setReviewText(String reviewText) {
            this.reviewText = reviewText;
        }
    }
}
