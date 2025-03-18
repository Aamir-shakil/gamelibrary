package com.aamir;


//Common attributes and methods for all games in library
public abstract class abstractGame implements playable {
    protected String title;
    protected String platform;
    protected String developer;
    protected String genre;
    protected int releaseYear;

    //Constructor
    public abstractGame(String title, String genre, String platform, int releaseYear, String developer) {
        this.title = title;
        this.genre = genre;
        this.platform = platform;
        this.releaseYear = releaseYear;
        this.developer = developer;
    }


    public String getTitle(){
        return title;
    }

    public abstract void updateProgress();

    
}

