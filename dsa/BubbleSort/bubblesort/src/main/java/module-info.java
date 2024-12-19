module com.dsa.bubblesort {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.dsa.bubblesort to javafx.fxml;
    exports com.dsa.bubblesort;
}
