module com.dsa.sort {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.dsa.sort to javafx.fxml;
    opens com.dsa.sort.forms to javafx.fxml;
    opens com.dsa.sort.entities to javafx.base;

    exports com.dsa.sort;
}
