package com.aamir.models;

import com.aamir.abstractGame;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class singlePlayer extends abstractGame {
    private IntegerProperty storyCompleted;

    public singlePlayer(String title, String genre, String platform, int releaseYear, String developer) {
        super(title, genre, platform, releaseYear, developer);
        this.storyCompleted = new SimpleIntegerProperty(0);
    }

    @Override
    public void updateProgress() {
        // Not used here, but required by abstract class
    }

    public void updateProgress(int progress) {
        if (progress >= 0 && progress <= 100) {
            this.storyCompleted.set(progress);
            System.out.println("You have completed " + progress + "% of the Campaign for " + getTitle());
        } else {
            System.out.println("Invalid input. Please enter a number between 0 and 100.");
        }
    }

    public int getStoryCompleted() {
        return storyCompleted.get();
    }

    public IntegerProperty storyCompletedProperty() {
        return storyCompleted;
    }

    public void setStoryCompleted(int progress) {
        this.storyCompleted.set(progress);
    }
}
