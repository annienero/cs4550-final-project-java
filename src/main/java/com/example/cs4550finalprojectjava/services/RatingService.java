package com.example.cs4550finalprojectjava.services;


import com.example.cs4550finalprojectjava.models.Rating;
import com.example.cs4550finalprojectjava.models.Review;
import com.example.cs4550finalprojectjava.repositories.RatingRepository;
import com.example.cs4550finalprojectjava.repositories.ReviewRepository;
import com.example.cs4550finalprojectjava.repositories.SongRepository;
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
    @Autowired
    SongRepository songRepository;

    @GetMapping("/api/rating")
    public List<Rating> findAllRatings() {
        return (List<Rating>) ratingRepository.findAll();
    }

    @GetMapping("/api/rating/{id}")
    public Rating findRatingById(@PathVariable("id") String id) {
        int ratingId = Integer.parseInt(id);
        return ratingRepository.findById(ratingId).get();
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

    @PutMapping("/api/rating/{ratingId}")
    public Rating updateRating(@PathVariable("id") String id, @RequestBody Rating rating) {
        Rating oldRating = ratingRepository.findById(Integer.parseInt(id)).get();
        oldRating.updateRating(rating);
        return ratingRepository.save(oldRating);
    }

    @DeleteMapping("/api/rating/{ratingId}")
    public void deleteRating(@PathVariable("ratingId") String ratingId) {
        ratingRepository.deleteById(Integer.parseInt(ratingId));
    }

}
