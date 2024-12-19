module com.particle {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.particle to javafx.fxml;
    exports com.particle;
}
