package com.dsa.sort.forms;

import java.time.ZoneId;
import com.dsa.sort.entities.Person;
import net.datafaker.Faker;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class GenerateFormController extends FormController {

    @FXML private TextField countField;

    @FXML
    private void onGenerateButtonClick() {
        String countStr = countField.getText();
        if (countStr == null || countStr.isEmpty()) {
            showValidationError("Count is required");
            return;
        }
        int count = 0;
        try { count = Integer.parseInt(countStr); }
        catch (NumberFormatException e) {
            showValidationError("Count muse be an integer");
            return;
        }
        if (count <= 0) {
            showValidationError("Count must be greater than 0");
            return;
        }

        Faker faker = new Faker();
        for (int i = 0; i < count; i++) getParent().addPerson(new Person(
            faker.name().firstName(),
            faker.name().lastName(),
            faker.timeAndDate().birthday()
        ));
    }
}
