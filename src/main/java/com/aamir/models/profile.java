package com.aamir.models;
import java.util.List;

import com.aamir.abstractGame;

import java.util.ArrayList;

public class profile {
    private String username;
    private String userPlatform;
    private List<abstractGame> games;


    public profile(String username, String userPlatform){
        this.username = username;
        this.userPlatform = userPlatform;
        this.games = new ArrayList<abstractGame>();
    }


    //Adding a game to the users library
    public void addGame(abstractGame game){
        games.add(game);
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
    
}
