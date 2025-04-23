package com.aamir.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import com.aamir.abstractGame;
import com.aamir.models.*;

public class mainController {

    @FXML private TextField titleField;
    @FXML private TextField genreField;
    @FXML private TextField platformField;
    @FXML private TextField developerField;
    @FXML private TextField yearField;
    @FXML private ChoiceBox<String> gameTypeChoice;
    @FXML private TextField progressField;
    @FXML private TextField winsField;
    @FXML private TextField lossesField;
    @FXML private TableView<abstractGame> gameTableView;
    @FXML private TableColumn<abstractGame, String> titleColumn;
    @FXML private TableColumn<abstractGame, String> genreColumn;
    @FXML private TableColumn<abstractGame, String> platformColumn;
    @FXML private TableColumn<abstractGame, String> developerColumn;
    @FXML private TableColumn<abstractGame, Integer> yearColumn;

    // NEW: For review system
    @FXML private ChoiceBox<Integer> ratingChoice;
    @FXML private TextArea reviewArea;

    private ObservableList<abstractGame> gameList = FXCollections.observableArrayList();

    // NEW: Add a user profile
    private profile currentUser = new profile("Player1", "PC");

    @FXML
    public void initialize() {
        gameTypeChoice.getItems().addAll("SinglePlayer", "Multiplayer");
        ratingChoice.getItems().addAll(1, 2, 3, 4, 5);

        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        platformColumn.setCellValueFactory(new PropertyValueFactory<>("platform"));
        developerColumn.setCellValueFactory(new PropertyValueFactory<>("developer"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("releaseYear"));

        gameTableView.setItems(gameList);
    }

    @FXML
    private void handleAddGame() {
        try {
            String title = titleField.getText();
            String genre = genreField.getText();
            String platform = platformField.getText();
            String developer = developerField.getText();
            String yearText = yearField.getText();
            String type = gameTypeChoice.getValue();

            if (title.isEmpty() || genre.isEmpty() || platform.isEmpty() || developer.isEmpty() || yearText.isEmpty() || type == null) {
                showAlert("Input Error", "Please check all fields and try again.");
                return;
            }

            int year = Integer.parseInt(yearText);
            abstractGame game = null;

            if (type.equals("SinglePlayer")) {
                int progress = Integer.parseInt(progressField.getText());
                singlePlayer spGame = new singlePlayer(title, genre, platform, year, developer);
                spGame.updateProgress(progress);
                game = spGame;
            } else if (type.equals("Multiplayer")) {
                int wins = Integer.parseInt(winsField.getText());
                int losses = Integer.parseInt(lossesField.getText());
                multiplayer mpGame = new multiplayer(title, genre, platform, year, developer);
                mpGame.updateProgress(wins, losses);
                game = mpGame;
            }

            if (game != null) {
                gameList.add(game);
                currentUser.addGame(game); // Add to user's library
                clearFields();
                showAlert("Success", "Game added successfully.");
            }

        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter valid numbers in numeric fields.");
        }
    }

    @FXML
    private void handleSubmitReview() {
        abstractGame selectedGame = gameTableView.getSelectionModel().getSelectedItem();

        if (selectedGame == null) {
            showAlert("No Selection", "Please select a game to review.");
            return;
        }

        Integer rating = ratingChoice.getValue();
        String reviewText = reviewArea.getText();

        if (rating == null || reviewText == null || reviewText.trim().isEmpty()) {
            showAlert("Incomplete Review", "Please provide both a rating and a review.");
            return;
        }

        currentUser.reviewGame(selectedGame, reviewText.trim(), rating);
        showAlert("Success", "Review submitted for: " + selectedGame.getTitle());
        ratingChoice.setValue(null);
        reviewArea.clear();
    }

    private void clearFields() {
        titleField.clear();
        genreField.clear();
        platformField.clear();
        developerField.clear();
        yearField.clear();
        progressField.clear();
        winsField.clear();
        lossesField.clear();
        gameTypeChoice.setValue(null);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
