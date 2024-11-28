module com.hello {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.hello to javafx.fxml;
    exports com.hello;
}
