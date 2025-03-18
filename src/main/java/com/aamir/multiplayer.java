package com.aamir;
import java.util.Scanner;

public class multiplayer extends abstractGame {
    private int wins = 0;
    private int losses = 0;

    public multiplayer(String title, String genre, String platform, int releaseYear, String developer){
        //inheritance- inheriting attributes from parent abstract class
        super(title, genre, platform, releaseYear, developer);
    }
    @Override
    public void updateProgress(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of wins you have for " + title);
        wins = scanner.nextInt();
        System.out.println("Enter the number of losses you have for " + title);
        losses = scanner.nextInt();
        System.out.println("You have " + wins + " wins and " + losses + " losses in " + title);
  
        
        
    }
}

