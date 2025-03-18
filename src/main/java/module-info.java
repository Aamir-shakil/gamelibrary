module com.aamir {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.aamir to javafx.fxml;
    exports com.aamir;
}
