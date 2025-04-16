package com.aamir.models;
import java.util.Scanner;

import com.aamir.abstractGame;

public class singlePlayer extends abstractGame {
    private int storyCompleted = 0;

    public singlePlayer(String title, String genre, String platform, int releaseYear, String developer){
        //inheritance- inheriting attributes from parent abstract class
        super(title, genre, platform, releaseYear, developer);
    }
     // New method to accept progress from GUI
     public void updateProgress(int progress) {
        if (progress >= 0 && progress <= 100) {
            this.storyCompleted = progress;
            System.out.println("You have completed " + storyCompleted + "% of the Campaign for " + title);
        } else {
            System.out.println("Invalid input. Please enter a number between 0 and 100.");
        }
    }
}
