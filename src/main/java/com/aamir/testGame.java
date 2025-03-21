package com.aamir;

import java.util.List;

//Testing to see if the singlePlayer class works
public class testGame {
    public static void main(String[] args) {
        // singlePlayer game1 = new singlePlayer("The Last of Us", "Action-Adventure", "PlayStation", 2013, "Naughty Dog");
        // multiplayer game2 = new multiplayer("Call of Duty: Warzone", "Shooter", "PlayStation & Xbox", 2020, "Infinity Ward");
        // game1.updateProgress();
        // game2.updateProgress();
        profile aamirShakil = new profile("Aamir", "PC");
        aamirShakil.addGame(new singlePlayer("Elden Ring", "RPG", "PS5", 2022, "FromSoftware"));
         List<abstractGame> searchResults = aamirShakil.searchGame("Elden");

        // Output the search results
        System.out.println("Search Results:");
        for (abstractGame game : searchResults) {
            System.out.println("Found " + game.getTitle()); 
        }


    }
    
}
