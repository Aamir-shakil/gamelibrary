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
        try {
            String title = titleField.getText();
            String genre = genreField.getText();
            String platform = platformField.getText();
            int year = Integer.parseInt(yearField.getText());
            String developer = developerField.getText();
            String type = gameTypeChoice.getValue();

            if (type == null || type.isEmpty()) {
                showAlert("Error", "Please select a game type.");
                return;
            }

            abstractGame game;
            if ("SinglePlayer".equals(type)) {
                game = new singlePlayer(title, genre, platform, year, developer);
            } else if ("Multiplayer".equals(type)) {
                game = new multiplayer(title, genre, platform, year, developer);
            } else {
                showAlert("Error", "Unsupported game type.");
                return;
            }

            currentUser.addGame(game);
            gameList.add(game); // Update the TableView
            clearFields();

            System.out.println("Game added: " + game.getTitle());
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter a valid year.");
        } catch (Exception e) {
            showAlert("Error", "Something went wrong: " + e.getMessage());
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