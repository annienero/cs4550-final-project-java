package com.example.cs4550finalprojectjava.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private RatingType ratingType;
    private int ratingValue;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="review_id")
    private Review review;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public RatingType getRatingType() {
        return ratingType;
    }

    public void setRatingType(RatingType ratingType) {
        this.ratingType = ratingType;
    }

    public int getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(int ratingValue) {
        this.ratingValue = ratingValue;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public void updateRating(Rating rating) {
        this.ratingValue = rating.ratingValue;
    }
}

