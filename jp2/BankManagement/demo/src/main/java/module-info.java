module com.bankman {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.bankman to javafx.fxml;
    exports com.bankman;
}
