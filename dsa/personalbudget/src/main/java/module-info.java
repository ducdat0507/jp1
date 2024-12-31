module com.personalbudget {
    requires javafx.controls;
    requires javafx.fxml;
    requires net.datafaker;

    opens com.personalbudget to javafx.fxml;
    opens com.personalbudget.controller to javafx.fxml;
    opens com.personalbudget.controller.screens to javafx.fxml;
    opens com.personalbudget.entities to javafx.base;
    exports com.personalbudget;
}
