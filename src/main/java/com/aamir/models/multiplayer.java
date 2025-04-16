package com.aamir.models;

import com.aamir.abstractGame;

public class multiplayer extends abstractGame {
    private int wins = 0;
    private int losses = 0;

    public multiplayer(String title, String genre, String platform, int releaseYear, String developer){
        //inheritance- inheriting attributes from parent abstract class
        super(title, genre, platform, releaseYear, developer);
    }


    @Override
    public void updateProgress() {}

    public void updateProgress(int wins, int losses) {
        this.wins = wins;
        this.losses = losses;
        System.out.println("You have " + wins + " wins and " + losses + " losses in " + title);
    }
}
