package com.aamir.models;

import com.aamir.abstractGame;
import java.io.Serializable;
import java.util.*;

/**
 * Represents a user profile containing personal gaming information.
 * Stores the user's username, platform, game library, and associated reviews.
 * Implements {@link Serializable} to allow saving and loading profile data.
 */

public class profile implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String userPlatform;
    private List<abstractGame> games;
    private Map<abstractGame, GameReview> reviews = new HashMap<>();

    public profile(String username, String userPlatform) {
        this.username = username;
        this.userPlatform = userPlatform;
        this.games = new ArrayList<>();
    }

    //Fucntions Returning username, platform, games and reviews.
    @Override
    public String toString() {
        return username;
    }

    public void addGame(abstractGame game) {
        games.add(game);
    }

    public String getUsername() {
        return username;
    }

    public String getUserPlatform() {
        return userPlatform;
    }

    public List<abstractGame> getGames() {
        return games;
    }

    //Returns the list of games in the user's library.
    public List<abstractGame> searchGame(String keyword) {
        List<abstractGame> result = new ArrayList<>();
        for (abstractGame game : games) {
            if (game.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(game);
            }
        }
        return result;
    }

    //Adds or updates a review for a specific game.
    public void reviewGame(abstractGame game, String reviewText, int rating) {
        GameReview review = new GameReview(reviewText, rating);
        reviews.put(game, review);
    }

    //Retrieves the review for a specific game, if it exists.
    public GameReview getReviewForGame(abstractGame game) {
        return reviews.get(game);
    }

    public Map<abstractGame, GameReview> getAllReviews() {
        return reviews;
    }
}
