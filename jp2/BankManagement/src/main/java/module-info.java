module com.bankman {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.mysql;

    opens com.bankman to javafx.fxml;
    exports com.bankman;
}
