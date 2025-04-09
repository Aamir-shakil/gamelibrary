package com.aamir.models;
import java.util.Scanner;

import com.aamir.abstractGame;

public class singlePlayer extends abstractGame {
    private int storyCompleted = 0;

    public singlePlayer(String title, String genre, String platform, int releaseYear, String developer){
        //inheritance- inheriting attributes from parent abstract class
        super(title, genre, platform, releaseYear, developer);
    }
    @Override
    public void updateProgress(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the percentage of the story you have completed for " + title);
        int newprogress = scanner.nextInt();
        if(storyCompleted <= 100 && storyCompleted >= 0){
            storyCompleted = newprogress;
            System.out.println("You have completed " + storyCompleted + "%" + " of the Campaign for " + title);
        }else{
            System.out.println("Invalid input. Please enter a whole number between 0 and 100");
        }
        
        


    }

    
}
