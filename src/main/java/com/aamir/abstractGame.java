package com.aamir;

import com.aamir.models.playable;
import javafx.beans.property.*;

public abstract class abstractGame implements playable {
    protected StringProperty title;
    protected StringProperty platform;
    protected StringProperty developer;
    protected StringProperty genre;
    protected IntegerProperty releaseYear;

    public abstractGame(String title, String genre, String platform, int releaseYear, String developer) {
        this.title = new SimpleStringProperty(title);
        this.genre = new SimpleStringProperty(genre);
        this.platform = new SimpleStringProperty(platform);
        this.releaseYear = new SimpleIntegerProperty(releaseYear);
        this.developer = new SimpleStringProperty(developer);
    }

    // Getters for TableView
    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public String getPlatform() {
        return platform.get();
    }

    public StringProperty platformProperty() {
        return platform;
    }

    public String getDeveloper() {
        return developer.get();
    }

    public StringProperty developerProperty() {
        return developer;
    }

    public String getGenre() {
        return genre.get();
    }

    public StringProperty genreProperty() {
        return genre;
    }

    public int getReleaseYear() {
        return releaseYear.get();
    }

    public IntegerProperty releaseYearProperty() {
        return releaseYear;
    }

    public abstract void updateProgress();
}
