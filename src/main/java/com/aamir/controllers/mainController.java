package com.aamir.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    @FXML private TextField progressField; 
    @FXML private TextField winsField;     
    @FXML private TextField lossesField;   

    private profile currentUser = new profile("Player1", "PC");
    private ObservableList<abstractGame> gameList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Populate choice box
        gameTypeChoice.getItems().addAll("SinglePlayer", "Multiplayer");
        
        // Bind TableView
        gameTableView.setItems(gameList);
    }




    @FXML
    private void handleAddGame() {
        
            String title = titleField.getText();
            String genre = genreField.getText();
            String platform = platformField.getText();
            int year = Integer.parseInt(yearField.getText());
            String developer = developerField.getText();
            String type = gameTypeChoice.getValue();

            abstractGame game = null;

            if ("SinglePlayer".equals(type)) {
                game = new singlePlayer(title, genre, platform, year, developer);
                try {
                    int progress = Integer.parseInt(progressField.getText());
                    ((singlePlayer) game).updateProgress(progress);  // Call overloaded method
                } catch (NumberFormatException e) {
                    System.out.println("Invalid story progress. Please enter a number.");
                }
            } else if ("Multiplayer".equals(type)) {
                game = new multiplayer(title, genre, platform, year, developer);
                try {
                    int wins = Integer.parseInt(winsField.getText());
                    int losses = Integer.parseInt(lossesField.getText());
                    ((multiplayer) game).updateProgress(wins, losses);  // Call overloaded method
                } catch (NumberFormatException e) {
                    System.out.println("Invalid wins/losses. Please enter valid numbers.");
                }
            }
        
            if (game != null) {
                currentUser.addGame(game);
                System.out.println("Game added: " + game.getTitle());
            }
        }

            private void clearFields() {
                titleField.clear();
                genreField.clear();
                platformField.clear();
                yearField.clear();
                developerField.clear();
                gameTypeChoice.setValue(null);
            }
        
            private void showAlert(String title, String message) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(title);
                alert.setHeaderText(null);
                alert.setContentText(message);
                alert.showAndWait();
            }
        }