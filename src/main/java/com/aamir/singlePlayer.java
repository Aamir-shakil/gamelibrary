package com.aamir;

public class singlePlayer extends abstractGame {
    private int storyCompleted = 0;

    public singlePlayer(String title, String genre, String platform, int releaseYear, String developer){
        //inheritance- inheriting attributes from parent abstract class
        super(title, genre, platform, releaseYear, developer);
    }

    public void updateProgress(){
        storyCompleted++;
        System.out.println("You have completed " + storyCompleted + "%" + " of the Campaign of " + title);


    }

    
}
