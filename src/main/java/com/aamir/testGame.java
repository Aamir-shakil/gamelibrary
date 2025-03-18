package com.aamir;
//Testing to see if the singlePlayer class works
public class testGame {
    public static void main(String[] args) {
        singlePlayer game1 = new singlePlayer("The Last of Us", "Action-Adventure", "PlayStation", 2013, "Naughty Dog");
        multiplayer game2 = new multiplayer("Call of Duty: Warzone", "Shooter", "PlayStation & Xbox", 2020, "Infinity Ward");
        game1.updateProgress();
        game2.updateProgress();
    }
    
}
