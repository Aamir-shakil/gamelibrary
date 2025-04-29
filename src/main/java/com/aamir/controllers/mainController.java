package com.aamir.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

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
    @FXML private TextField usernameField;
    @FXML private TextField userPlatformField;
    @FXML private Label currentUserLabel;
    @FXML private ChoiceBox<String> userSelectChoiceBox;
    @FXML private ChoiceBox<String> searchFilterChoiceBox;
    @FXML private TextField searchField;
    @FXML private TextArea myReviewArea;
    @FXML private Button viewProgressButton;
    @FXML private Label gameProgressLabel;
    @FXML private Label progressViewLabel;

    // Review system UI components
    @FXML private ChoiceBox<Integer> ratingChoice;
    @FXML private TextArea reviewArea;

    private ObservableList<abstractGame> gameList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        gameTypeChoice.getItems().addAll("SinglePlayer", "Multiplayer");
        ratingChoice.getItems().addAll(1, 2, 3, 4, 5);
        
        searchFilterChoiceBox.setItems(FXCollections.observableArrayList("Title", "Genre", "Developer"));
        searchFilterChoiceBox.setValue("Title"); //default value

        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        platformColumn.setCellValueFactory(new PropertyValueFactory<>("platform"));
        developerColumn.setCellValueFactory(new PropertyValueFactory<>("developer"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("releaseYear"));


        gameTableView.setItems(gameList);

        gameTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            updateMyReviewDisplay(newSelection);
        });

        // Load saved profiles
        ProfileManager.loadAllProfiles();
        userSelectChoiceBox.getItems().setAll(ProfileManager.getAllProfileNames());
    }

    @FXML
    private void handleAddGame() {
        profile currentProfile = ProfileManager.getCurrentProfile();
        if (currentProfile == null) {
            showAlert("No Profile Selected", "Please create or switch to a profile first.");
            return;
        }

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

        try {
            int year = Integer.parseInt(yearText);
            abstractGame game = null;

            if (type.equals("SinglePlayer")) {
                String progressText = progressField.getText();
                if (progressText == null || progressText.trim().isEmpty()) {
                    showAlert("Input Error", "Please enter progress for SinglePlayer.");
                    return;
                }
                int progress = Integer.parseInt(progressText);
                singlePlayer spGame = new singlePlayer(title, genre, platform, year, developer);
                spGame.updateProgress(progress);
                game = spGame;
            } else if (type.equals("Multiplayer")) {
                String winsText = winsField.getText();
                String lossesText = lossesField.getText();
                if (winsText == null || lossesText == null || winsText.trim().isEmpty() || lossesText.trim().isEmpty()) {
                    showAlert("Input Error", "Please enter wins and losses for Multiplayer.");
                    return;
                }
                int wins = Integer.parseInt(winsText);
                int losses = Integer.parseInt(lossesText);
                multiplayer mpGame = new multiplayer(title, genre, platform, year, developer);
                mpGame.updateProgress(wins, losses);
                game = mpGame;
            }

            if (game != null) {
                gameList.add(game);
                currentProfile.addGame(game);
                ProfileManager.saveProfile(currentProfile);
                clearFields();
                showAlert("Success", "Game added successfully.");
            }

        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter valid numbers in numeric fields.");
        }
    }
    @FXML
    private void handleViewProgress() {
        abstractGame selectedGame = gameTableView.getSelectionModel().getSelectedItem();

        if (selectedGame == null) {
            gameProgressLabel.setText("Progress: Please select a game.");
            return;
        }

        if (selectedGame instanceof singlePlayer) {
            singlePlayer spGame = (singlePlayer) selectedGame;
            gameProgressLabel.setText("Progress: " + spGame.getStoryCompleted() + "% completed.");
        } else if (selectedGame instanceof multiplayer) {
            multiplayer mpGame = (multiplayer) selectedGame;
            gameProgressLabel.setText("Wins: " + mpGame.getWins() + " | Losses: " + mpGame.getLosses());
        } else {
            gameProgressLabel.setText("Progress info unavailable.");
        }
    }

    @FXML
    private void handleSubmitReview() {
        profile currentProfile = ProfileManager.getCurrentProfile();
        if (currentProfile == null) {
            showAlert("No Profile Selected", "Please select a profile first.");
            return;
        }

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

        currentProfile.reviewGame(selectedGame, reviewText.trim(), rating);
        ProfileManager.saveProfile(currentProfile);
        showAlert("Success", "Review submitted for: " + selectedGame.getTitle());

        ratingChoice.setValue(null);
        reviewArea.clear();
    }

    @FXML
    private void handleCreateProfile() {
        String username = usernameField.getText().trim();
        String platform = userPlatformField.getText().trim();
    
        if (username.isEmpty() || platform.isEmpty()) {
            showAlert("Error", "Please enter both username and platform.");
            return;
        }
    
        boolean created = ProfileManager.createProfile(username, platform);
        if (created) {
            profile currentProfile = ProfileManager.getCurrentProfile();
            currentUserLabel.setText("Current user: " + username);
            gameList.setAll(currentProfile.getGames());
            userSelectChoiceBox.getItems().add(username); // <== Update dropdown
            showAlert("Success", "Profile created and selected.");
        } else {
            showAlert("Error", "Username already exists. Try another.");
        }
    }
    @FXML
    private void handleSwitchToSelectedUser() {
        String selectedUsername = userSelectChoiceBox.getValue();

        if (selectedUsername != null && ProfileManager.selectProfile(selectedUsername)) {
            profile currentProfile = ProfileManager.getCurrentProfile();
            currentUserLabel.setText("Current user: " + selectedUsername);
            gameList.setAll(currentProfile.getGames());
            showAlert("Profile Loaded", "Welcome back, " + selectedUsername + "!");
        } else {
            showAlert("Error", "Profile not found.");
        }
    }

    @FXML
    private void handleSwitchProfile() {
        String username = usernameField.getText().trim();

        boolean switched = ProfileManager.selectProfile(username);
        if (switched) {
            profile currentProfile = ProfileManager.getCurrentProfile();
            currentUserLabel.setText("Current user: " + username);
            gameList.setAll(currentProfile.getGames());
            showAlert("Success", "Switched to profile: " + username);
        } else {
            showAlert("Error", "Profile not found.");
        }
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
    @FXML
    private void handleUpdateProgress() {
        System.out.println("Update Progress clicked");
    }


    @FXML
    private void handleSearch() {
        profile currentProfile = ProfileManager.getCurrentProfile();
        if (currentProfile == null) {
            showAlert("No Profile Selected", "Please create or switch to a profile first.");
            return;
        }

        String keyword = searchField.getText().trim().toLowerCase();
        String filter = searchFilterChoiceBox.getValue();

        if (keyword.isEmpty() || filter == null) {
            showAlert("Search Error", "Please enter a keyword and select a filter.");
            return;
        }

        List<abstractGame> allGames = currentProfile.getGames();
        List<abstractGame> filteredGames = new ArrayList<>();

        for (abstractGame game : allGames) {
            switch (filter) {
                case "Title":
                    if (game.getTitle().toLowerCase().contains(keyword)) {
                        filteredGames.add(game);
                    }
                    break;
                case "Developer":
                    if (game.getDeveloper().toLowerCase().contains(keyword)) {
                        filteredGames.add(game);
                    }
                    break;
                case "Genre":
                    if (game.getGenre().toLowerCase().contains(keyword)) {
                        filteredGames.add(game);
                    }
                    break;
            }
        }

        gameList.setAll(filteredGames);
    }
    private void updateMyReviewDisplay(abstractGame game) {
        profile currentProfile = ProfileManager.getCurrentProfile();
        if (currentProfile != null && game != null) {
            GameReview review = currentProfile.getReviewForGame(game);
            if (review != null) {
                myReviewArea.setText(review.toString());
            } else {
                myReviewArea.clear();
            }
        }
    }

}
