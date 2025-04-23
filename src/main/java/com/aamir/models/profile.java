package com.aamir.models;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import com.aamir.abstractGame;
import java.util.ArrayList;

public class profile {
    private String username;
    private String userPlatform;
    private List<abstractGame> games;
    private Map<abstractGame, GameReview> reviews = new HashMap<>();


    public profile(String username, String userPlatform){
        this.username = username;
        this.userPlatform = userPlatform;
        this.games = new ArrayList<abstractGame>();
    }


    //Adding a game to the users library
    public void addGame(abstractGame game){
        games.add(game);
    }
    
    public String getUsername() {
        return username;
    }

    public String getUserPlatform() {
        return userPlatform;
    }


    //Searching for gamne in users library
    public List<abstractGame> searchGame(String keyword){
        List<abstractGame> result = new ArrayList<abstractGame>();
        for(abstractGame game : games){
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
    
    // Get a review for a specific game
    public GameReview getReviewForGame(abstractGame game) {
        return reviews.get(game);
    }
    
    // View all reviews
    public Map<abstractGame, GameReview> getAllReviews() {
        return reviews;
    }
    
}
