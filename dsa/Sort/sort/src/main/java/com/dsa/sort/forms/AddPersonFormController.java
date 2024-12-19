package com.dsa.sort.forms;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.function.Supplier;

import com.dsa.sort.entities.Person;
import com.dsa.sort.sorts.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddPersonFormController extends FormController {

    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private DatePicker dateOfBirthField;

    @FXML
    private void onAddButtonClick() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        LocalDate dateOfBirth = dateOfBirthField.getValue();

        if (firstName == null || firstName.isEmpty()) {
            showPopupError("Validation failed", "First name is required");
            return;
        }
        if (lastName == null || lastName.isEmpty()) {
            showPopupError("Validation failed", "Last name is required");
            return;
        }
        if (dateOfBirth == null) {
            showPopupError("Validation failed", "Date of birth is required");
            return;
        }


        getParent().addPerson(new Person(firstName, lastName, dateOfBirth));
    }
}
