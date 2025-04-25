package com.aamir.models;

import com.aamir.abstractGame;
import java.io.Serializable;
import java.util.*;

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

    public List<abstractGame> searchGame(String keyword) {
        List<abstractGame> result = new ArrayList<>();
        for (abstractGame game : games) {
            if (game.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(game);
            }
        }
        return result;
    }

    public void reviewGame(abstractGame game, String reviewText, int rating) {
        GameReview review = new GameReview(reviewText, rating);
        reviews.put(game, review);
    }

    public GameReview getReviewForGame(abstractGame game) {
        return reviews.get(game);
    }

    public Map<abstractGame, GameReview> getAllReviews() {
        return reviews;
    }
}
