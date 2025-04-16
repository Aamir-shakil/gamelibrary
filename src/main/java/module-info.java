module com.aamir {
    requires javafx.controls;
    requires javafx.fxml;

    // Allow JavaFX to reflectively access this package
    opens com.aamir to javafx.graphics, javafx.fxml;

    // Allow JavaFX to access controllers via FXML
    opens com.aamir.controllers to javafx.fxml;

    exports com.aamir;
}