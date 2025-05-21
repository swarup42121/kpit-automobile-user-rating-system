package com.example.Automobile_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Automobile_app.model.User;
import com.example.Automobile_app.repository.UserRepository;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String showLoginForm() {
        return "index";
    }

    @GetMapping("/cars")
public String viewCars() {
    return "top_rated_cars"; // Thymeleaf will load cars.html
}

@GetMapping("/rate-review")
public String showRateReviewPage() {
    return "rate_review"; // This maps to rate_review.html in templates
}


    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        if (user != null) {
            model.addAttribute("message", "Login successful!");
            return "dashboard";
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "index";
        }
    }
}
