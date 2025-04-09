package com.aamir.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import com.aamir.abstractGame;
import com.aamir.models.*;

public class mainController {
    @FXML private TextField titleField;
    @FXML private TextField genreField;
    @FXML private TextField platformField;
    @FXML private TextField yearField;
    @FXML private TextField developerField;
    @FXML private ChoiceBox<String> gameTypeChoice;
    @FXML private TableView<abstractGame> gameTableView;

    private profile currentUser = new profile("Player1", "PC");

    @FXML
    private void handleAddGame() {
        // Grab values
        String title = titleField.getText();
        String genre = genreField.getText();
        String platform = platformField.getText();
        int year = Integer.parseInt(yearField.getText());
        String developer = developerField.getText();
        String type = gameTypeChoice.getValue();

        abstractGame game;
        if ("SinglePlayer".equals(type)) {
            game = new singlePlayer(title, genre, platform, year, developer);
        } else if ("Multiplayer".equals(type)) {
            game = new multiplayer(title, genre, platform, year, developer);
        } 

        currentUser.addGame(game);
        // Refresh TableView or print for now
        System.out.println("Game added: " + game.getTitle());
    }
}
