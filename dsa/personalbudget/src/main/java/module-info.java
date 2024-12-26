module com.personalbudget {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.personalbudget to javafx.fxml;
    exports com.personalbudget;
}
