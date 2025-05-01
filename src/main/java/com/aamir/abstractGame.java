package com.aamir;

import com.aamir.models.playable;
import java.io.Serializable;

// Common attributes and methods for all games in library
public abstract class abstractGame implements playable, Serializable {
    private static final long serialVersionUID = 1L;

    protected String title;
    protected String platform;
    protected String developer;
    protected String genre;
    protected int releaseYear;

    // Constructor
    public abstractGame(String title, String genre, String platform, int releaseYear, String developer) {
        this.title = title;
        this.genre = genre;
        this.platform = platform;
        this.releaseYear = releaseYear;
        this.developer = developer;
    }
    //returns title
    public String getTitle() {
        return title;
    }
    //returns genre 
    public String getGenre() {
        return genre;
    }

    public String getPlatform() {
        return platform;
    }

    public String getDeveloper() {
        return developer;
    }

    public int getReleaseYear() {
        return releaseYear;
    }
    //Returns type of game, is implemented by subclasses singleplayer and multiplayer to be overridden
    public String getType() {
        return "Unknown";
    }
    //Abstract method for updating game progress.
    public abstract void updateProgress();
}
