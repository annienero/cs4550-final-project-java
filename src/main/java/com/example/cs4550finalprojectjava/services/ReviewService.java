package com.example.cs4550finalprojectjava.services;


import com.example.cs4550finalprojectjava.models.Follow;
import com.example.cs4550finalprojectjava.models.Review;
import com.example.cs4550finalprojectjava.models.Song;
import com.example.cs4550finalprojectjava.models.User;
import com.example.cs4550finalprojectjava.repositories.FollowRepository;
import com.example.cs4550finalprojectjava.repositories.ReviewRepository;
import com.example.cs4550finalprojectjava.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.cs4550finalprojectjava.services.UserService.USER;

@RestController
@CrossOrigin(origins = "*", maxAge=3600, allowCredentials = "true")
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    SongRepository songRepository;

    @Autowired
    FollowRepository followRepository;

    @GetMapping("/api/review")
    public List<Review> findAllReviews() {
        return (List<Review>) reviewRepository.findAll();
    }

    @GetMapping("/api/review/{id}")
    public Review findReviewById(@PathVariable("id") String id) {
        int reviewId = Integer.parseInt(id);
        return reviewRepository.findById(reviewId).get();
    }

    @PostMapping("/api/song/{songId}/review")
    public Review createReview(@PathVariable("songId") String songId, @RequestBody Review review, HttpSession session) {
        Optional<Song> data = songRepository.findById(Integer.parseInt(songId));
        if(data.isPresent()) {
            Song song = data.get();
            review.setSong(song);
            User u = (User) session.getAttribute(USER);
            review.setUser(u);
            review.setUsername(u.getUsername());
            review.setSongTitle(song.getTitle());
            review.setSongArtist(song.getArtistName());
            reviewRepository.save(review);
            return review;
        }
        return null;
    }

    @PutMapping("/api/review/{reviewId}")
    public Review updateReview(@PathVariable("reviewId") String reviewId, @RequestBody Review review) {
        Review oldReview = reviewRepository.findById(Integer.parseInt(reviewId)).get();
        oldReview.updateReview(review);
        return reviewRepository.save(oldReview);
    }

    @DeleteMapping("/api/review/{reviewId}")
    public void deleteReview(@PathVariable("reviewId") String reviewId) {
        reviewRepository.deleteById(Integer.parseInt(reviewId));
    }

    @GetMapping("/api/song/{songId}/review")
    public List<Review> findAllReviewsForSong(@PathVariable("songId") String songId) {
        Optional<Song> data = songRepository.findById(Integer.parseInt(songId));
        if(data.isPresent()) {
            Song song = data.get();
            return song.getReviews();
        }
        return null;
    }

    @GetMapping("/api/review/follow")
    public List<Review> findFollowedReviews(HttpSession session) {
        User curUser = (User) session.getAttribute(USER);
        if (curUser != null) {
            List<Review> reviews = new ArrayList<Review>();
            for (User u : followRepository.getFollowing(curUser.getId())) {
                reviews.addAll(u.getReviews());
            }
            return reviews;
        }
        return null;
    }
}
