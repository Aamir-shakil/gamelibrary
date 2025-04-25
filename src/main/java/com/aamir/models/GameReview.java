package com.aamir.models;
import java.io.Serializable;

public class GameReview implements Serializable {
    private static final long serialVersionUID = 1L;
    private String reviewText;
    private int rating; // Rating out of 5

    public GameReview(String reviewText, int rating) {
        this.reviewText = reviewText;
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public int getRating() {
        return rating;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public void setRating(int rating) {
        if (rating >= 1 && rating <= 5) {
            this.rating = rating;
        }
    }

    @Override
    public String toString() {
        return "Rating: " + rating + "/5 - " + reviewText;
    }
}
