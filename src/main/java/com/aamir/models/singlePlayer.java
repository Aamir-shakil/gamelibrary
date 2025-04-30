package com.aamir.models;

import com.aamir.abstractGame;
import java.io.Serializable;

public class singlePlayer extends abstractGame implements Serializable {
    private static final long serialVersionUID = 1L;

    private int storyCompleted = 0;

    public singlePlayer(String title, String genre, String platform, int releaseYear, String developer) {
        super(title, genre, platform, releaseYear, developer);
    }

    @Override
    public void updateProgress() {
        // Not used in this context
    }

    public void updateProgress(int progress) {
        if (progress >= 0 && progress <= 100) {
            this.storyCompleted = progress;
            System.out.println("You have completed " + storyCompleted + "% of the Campaign for " + title);
        } else {
            System.out.println("Invalid input. Please enter a number between 0 and 100.");
        }
    }

    public int getStoryCompleted() {
        return storyCompleted;
    }

    @Override
    public String getType() {
        return "SinglePlayer";
}
}
