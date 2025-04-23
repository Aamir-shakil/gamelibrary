package com.aamir;

import com.aamir.models.playable;

// Common attributes and methods for all games in library
public abstract class abstractGame implements playable {
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

    public String getTitle() {
        return title;
    }

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

    public abstract void updateProgress();
}
