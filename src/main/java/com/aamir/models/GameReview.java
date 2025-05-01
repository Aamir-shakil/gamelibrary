package com.aamir.models;
import java.io.Serializable;

/**
 * Represents a review of a game, including a textual review and a numeric rating.
 * This class implements {@link Serializable} to allow game reviews to be saved and loaded.
 */

public class GameReview implements Serializable {
    private static final long serialVersionUID = 1L;
    private String reviewText;
    private int rating; // Rating out of 5

    public GameReview(String reviewText, int rating) {
        this.reviewText = reviewText;
        this.rating = rating;
    }

    //Returns the text of the review.
    public String getReviewText() {
        return reviewText;
    }

    //Returns the rating of the review.
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

    /**
     * Returns a string representation of the review in the format:
     * "Rating: x/5 - reviewText".
     */
    @Override
    public String toString() {
        return "Rating: " + rating + "/5 - " + reviewText;
    }
}
