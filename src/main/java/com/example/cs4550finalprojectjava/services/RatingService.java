package com.example.cs4550finalprojectjava.services;


import com.example.cs4550finalprojectjava.models.Rating;
import com.example.cs4550finalprojectjava.models.Review;
import com.example.cs4550finalprojectjava.repositories.RatingRepository;
import com.example.cs4550finalprojectjava.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge=3600, allowCredentials = "true")
public class RatingService {

    @Autowired
    RatingRepository ratingRepository;
    @Autowired
    ReviewRepository reviewRepository;

    @GetMapping("/api/rating")
    public List<Rating> findAllRatings() {
        return (List<Rating>) ratingRepository.findAll();
    }

    @GetMapping("/api/rating/{id}")
    public Rating findRatingById(@PathVariable("id") String id) {
        int ratingId = Integer.parseInt(id);
        return ratingRepository.findById(ratingId).get();
    }

    @GetMapping("/api/review/{reviewId}/rating")
    public List<Rating> findAllRatingsForReview(@PathVariable("reviewId") String reviewId) {
        Optional<Review> data = reviewRepository.findById(Integer.parseInt(reviewId));
        if(data.isPresent()) {
            Review review = data.get();
            return review.getRatings();
        }
        return null;
    }

    @PostMapping("/api/review/{reviewId}/rating")
    public Rating createRating(@PathVariable("reviewId") String reviewId, @RequestBody Rating rating) {
        Optional<Review> data = reviewRepository.findById(Integer.parseInt(reviewId));
        if(data.isPresent()) {
            Review review = data.get();
            rating.setReview(review);
            ratingRepository.save(rating);
            return rating;
        }
        return null;
    }

    @PutMapping("/api/rating/{id}")
    public Rating updateRating(@PathVariable("id") String id, @RequestBody Rating rating) {
        Rating oldRating = ratingRepository.findById(Integer.parseInt(id)).get();
        oldRating.updateRating(rating);
        return ratingRepository.save(oldRating);
    }

    @DeleteMapping("/api/rating/{id}")
    public void deleteRating(@PathVariable("id") String id) {
        ratingRepository.deleteById(Integer.parseInt(id));
    }

}
