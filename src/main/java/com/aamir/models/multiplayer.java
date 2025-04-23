package com.aamir.models;

import com.aamir.abstractGame;

public class multiplayer extends abstractGame {
    private int wins = 0;
    private int losses = 0;

    public multiplayer(String title, String genre, String platform, int releaseYear, String developer) {
        super(title, genre, platform, releaseYear, developer);
    }

    @Override
    public void updateProgress() {
        // Not used in this context
    }

    public void updateProgress(int wins, int losses) {
        this.wins = wins;
        this.losses = losses;
        System.out.println("You have " + wins + " wins and " + losses + " losses in " + title);
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }
}
