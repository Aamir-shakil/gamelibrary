package com.aamir.models;

import com.aamir.abstractGame;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class multiplayer extends abstractGame {
    private IntegerProperty wins;
    private IntegerProperty losses;

    public multiplayer(String title, String genre, String platform, int releaseYear, String developer) {
        super(title, genre, platform, releaseYear, developer);
        this.wins = new SimpleIntegerProperty(0);
        this.losses = new SimpleIntegerProperty(0);
    }

    @Override
    public void updateProgress() {
        // Not used here
    }

    public void updateProgress(int wins, int losses) {
        this.wins.set(wins);
        this.losses.set(losses);
        System.out.println("You have " + wins + " wins and " + losses + " losses in " + getTitle());
    }

    public int getWins() {
        return wins.get();
    }

    public IntegerProperty winsProperty() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins.set(wins);
    }

    public int getLosses() {
        return losses.get();
    }

    public IntegerProperty lossesProperty() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses.set(losses);
    }
}
