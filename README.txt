Automobile User Rating System - Project README
===============================

Project Overview:
-----------------
Automobile_app is a Spring Boot web application designed to provide users with information about various cars, including their specifications and user reviews. The application allows users to submit reviews for cars and view aggregated reviews along with car details and images.

Code Files and Their Purpose:
-----------------------------

1. AutomobileAppApplication.java
   - The main entry point of the Spring Boot application.
   - Contains the main method that launches the application.

2. controller/ReviewController.java
   - REST controller handling HTTP requests related to car reviews.
   - Provides endpoints to submit reviews and fetch all cars with their reviews.
   - Maps car names to corresponding image filenames for frontend display.
   - Contains an inner controller to serve the "all_reviews.html" page.
   - Defines an inner static class ReviewRequest to receive review data from clients.

3. model/Car.java
   - JPA entity representing a car.
   - Fields include id, name, brand, model, year, and imageUrl (filename of the car image).
   - Used to persist car data in the database.

4. repository/CarRepository.java
   - Spring Data JPA repository interface for Car entity.
   - Provides methods to find cars by name with case-insensitive matching.

5. config/StaticResourceConfig.java
   - Spring MVC configuration class.
   - Configures the application to serve static resources (car images) from the classpath location "static/images" under the URL path "/images/**".

6. resources/templates/
   - Contains HTML templates for various pages such as dashboard, rate_review, all_reviews, top_rated_cars, welcome, and index.
   - These templates are rendered by the frontend to display car information and reviews.

7. resources/static/images/
   - Contains static image files of cars used in the application frontend.

How to Run the Project:
-----------------------

Requirements:
- Java Development Kit (JDK) 17 or later
- Apache Maven 3.6 or later

Steps:
1. Clone or download the project source code.
2. Open a terminal and navigate to the root directory of the project (where pom.xml is located).
3. Build the project using Maven:
   mvn clean install
4. Run the Spring Boot application:
   mvn spring-boot:run
5. Open a web browser and navigate to:
   http://localhost:8080/
   to access the application.

Flow Diagram:
-------------

User Request (Browser)
        |
        v
Spring Boot Dispatcher Servlet
        |
        v
ReviewController (handles /api/reviews endpoints)
        |
        v
CarRepository & ReviewRepository (data access layer)
        |
        v
Database (stores car and review data)
        |
        v
StaticResourceConfig serves images from /images/**
        |
        v
Frontend Templates render pages with car data and images

Additional Notes:
-----------------
- The application uses Spring Data JPA for database interactions.
- Static images of cars are served from the "static/images" directory.
- The ReviewController maps car names to image filenames to display appropriate images on the frontend.
- The project uses Thymeleaf templates located in "resources/templates" for rendering HTML pages.

This README provides a comprehensive overview of the project structure, code files, and instructions to run the application.
