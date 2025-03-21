package com.aamir;
import java.util.List;
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



    public void addGame(abstractGame game){
        games.add(game);
    }
    
}
