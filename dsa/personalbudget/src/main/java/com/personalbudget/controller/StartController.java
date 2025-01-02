package com.personalbudget.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.personalbudget.App;
import com.personalbudget.constants.DateRange;
import com.personalbudget.controller.forms.EntryEditFormController;
import com.personalbudget.controller.forms.EntryFilterFormController;
import com.personalbudget.controller.forms.FormController;
import com.personalbudget.controller.screens.MonthCalendarScreenController;
import com.personalbudget.controller.screens.MultiYearCalendarScreenController;
import com.personalbudget.controller.screens.RecordScreenController;
import com.personalbudget.controller.screens.ScreenController;
import com.personalbudget.controller.screens.SummaryScreenController;
import com.personalbudget.controller.screens.YearCalendarScreenController;
import com.personalbudget.entities.BudgetEntry;
import com.personalbudget.entities.BudgetRecord;
import com.personalbudget.i18n.I18n;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import net.datafaker.Faker;

public class StartController {
    @FXML private TextField openNameField;
    @FXML private TextField openPasswordField;

    @FXML private TextField createNameField;
    @FXML private TextField createPasswordField;
    @FXML private TextField createCurrencyField;

    @FXML
    private void handleOpenButtonClick() {
        List<String> errors = validateOpenForm();
        if (!errors.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle(I18n.get("dialogs.title.error"));
            alert.setHeaderText(I18n.get("dialogs.validation.fail.title"));
            alert.setContentText(String.join("\n", errors));
            alert.show();
            return;
        }

        BudgetRecord record = new BudgetRecord();
        boolean result = record.bindFolder(openNameField.getText(), openPasswordField.getText());

        if (result) {
            App.setRecord(record);
            try { App.setRoot("primary"); }
            catch (IOException e) { e.printStackTrace(); }
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle(I18n.get("dialogs.title.error"));
            alert.setHeaderText(I18n.get("dialogs.generic.error_login"));
            alert.setContentText(String.join("\n", errors));
            alert.show();
            return;
        }
    }

    @FXML
    private void handleCreateButtonClick() {
        List<String> errors = validateCreateForm();
        if (!errors.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle(I18n.get("dialogs.title.error"));
            alert.setHeaderText(I18n.get("dialogs.validation.fail.title"));
            alert.setContentText(String.join("\n", errors));
            alert.show();
            return;
        }

        BudgetRecord record = new BudgetRecord();
        
        // Initialize record
        record.setCurrency(createCurrencyField.getText()); 
        List<String> incomeCats = List.of(
            "Salary", "Other"
        );
        record.getIncomeCategories().addAll(incomeCats);
        List<String> expenseCats = List.of(
            "Housing", "Water", "Food", "Electricity", "Internet", "Other"
        );
        record.getExpenseCategories().addAll(expenseCats);

        boolean result = record.bindFolder(createNameField.getText(), createPasswordField.getText());

        if (result) {
            App.setRecord(record);
            try { App.setRoot("primary"); }
            catch (IOException e) { e.printStackTrace(); }
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle(I18n.get("dialogs.title.error"));
            alert.setHeaderText(I18n.get("dialogs.generic.error"));
            alert.setContentText(String.join("\n", errors));
            alert.show();
            return;
        }

    }

    public List<String> validateOpenForm() {
        List<String> errors = new ArrayList<>();

        // Validate name
        if (openNameField.getText().isBlank()) {
            errors.add(I18n.get("forms.start.validation.name.empty"));
        } else if (!isFilenameValid(openNameField.getText())) {
            errors.add(I18n.get("forms.start.validation.name.invalid"));
        } else {
            Path realPath = Path.of(System.getProperty("user.home"), ".personalbudget/records", openNameField.getText());
            if (!realPath.toFile().exists()) errors.add(I18n.get("forms.start.validation.name.not_exist"));
        }

        return errors; 
    }

    public List<String> validateCreateForm() {
        List<String> errors = new ArrayList<>();

        // Validate name
        if (createNameField.getText().isBlank()) {
            errors.add(I18n.get("forms.start.validation.name.empty"));
        } else if (!isFilenameValid(createNameField.getText())) {
            errors.add(I18n.get("forms.start.validation.name.invalid"));
        } else {
            Path realPath = Path.of(System.getProperty("user.home"), ".personalbudget/records", createNameField.getText());
            if (realPath.toFile().exists()) errors.add(I18n.get("forms.start.validation.name.exist"));
        }
        
        // Validate currency
        if (createCurrencyField.getText().isBlank()) {
            errors.add(I18n.get("forms.start.validation.currency.empty"));
        } else if (createCurrencyField.getText().trim().length() != 3) {
            errors.add(I18n.get("forms.start.validation.currency.invalid"));
        }

        return errors; 
    }


    public static boolean isFilenameValid(String file) {
        File f = new File(file);
        try {
          f.getCanonicalPath();
          return true;
        } catch (IOException e) {
          return false;
        }
    }
}
