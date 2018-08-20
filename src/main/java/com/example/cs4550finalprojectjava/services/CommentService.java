package com.example.cs4550finalprojectjava.services;

import com.example.cs4550finalprojectjava.models.Comment;
import com.example.cs4550finalprojectjava.models.Review;
import com.example.cs4550finalprojectjava.models.User;
import com.example.cs4550finalprojectjava.repositories.CommentRepository;
import com.example.cs4550finalprojectjava.repositories.ReviewRepository;
import com.example.cs4550finalprojectjava.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

import static com.example.cs4550finalprojectjava.services.UserService.USER;

@RestController
@CrossOrigin(origins = "*", maxAge=3600, allowCredentials = "true")
public class CommentService {
    
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    ReviewRepository reviewRepository;


    @GetMapping("/api/comment")
    public List<Comment> findAllComments() {
        return (List<Comment>) commentRepository.findAll();
    }

    @GetMapping("/api/comment/{id}")
    public Comment findCommentById(@PathVariable("id") String id) {
        int commentId = Integer.parseInt(id);
        return commentRepository.findById(commentId).get();
    }

    @PostMapping("/api/review/{reviewId}/comment")
    public Comment createComment(@PathVariable("reviewId") String reviewId, @RequestBody Comment comment, HttpSession session) {
        Optional<Review> data = reviewRepository.findById(Integer.parseInt(reviewId));
        if(data.isPresent()) {
            Review review = data.get();
            comment.setReview(review);
            User u = (User) session.getAttribute(USER);
            comment.setUser(u);
            comment.setUsername(u.getUsername());
            commentRepository.save(comment);
            return comment;
        }
        return null;
    }

    @PutMapping("/api/comment/{id}")
    public Comment updateComment(@PathVariable("id") String id, @RequestBody Comment comment) {
        Comment oldComment = commentRepository.findById(Integer.parseInt(id)).get();
        oldComment.updateComment(comment);
        return commentRepository.save(oldComment);
    }

    @DeleteMapping("/api/comment/{commentId}")
    public void deleteComment(@PathVariable("commentId") String commentId) {
        commentRepository.deleteById(Integer.parseInt(commentId));
    }

    @GetMapping("/api/review/{reviewId}/comment")
    public List<Comment> findAllCommentsForReview(@PathVariable("reviewId") String reviewId) {
        Optional<Review> data = reviewRepository.findById(Integer.parseInt(reviewId));
        if(data.isPresent()) {
            Review review = data.get();
            return review.getComments();
        }
        return null;
    }
}
