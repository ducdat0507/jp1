package com.personalbudget.controller.forms;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Currency;
import java.util.List;

import com.personalbudget.constants.DateRange;
import com.personalbudget.entities.BudgetEntry;
import com.personalbudget.entities.BudgetSummary;
import com.personalbudget.i18n.I18n;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

public class EntryEditFormController extends AbstractFormController {
    private BudgetEntry oldEntry;

    @FXML private Label titleLabel;

    @FXML private ToggleGroup typeGroup;
    @FXML private RadioButton typeIncomeRadio;
    @FXML private RadioButton typeExpenseRadio;
    @FXML private TextField amountField;
    @FXML private Label currencyLabel;
    @FXML private ComboBox<String> categoryField;
    @FXML private DatePicker dateField;
    @FXML private TextArea notesField;

    @FXML private Button deleteButton;
    @FXML private Button submitButton;

    @Override
    protected void onParentSet() {
        currencyLabel.setText(parent.getRecord().getCurrency());
        typeGroup.selectedToggleProperty().addListener((prop, from, to) -> {
            updateCategories();
        });
    }

    public void setIsIncome(boolean isIncome) {
        (isIncome ? typeIncomeRadio : typeExpenseRadio).setSelected(true);
    }

    public void setToday() {
        dateField.setValue(LocalDate.now());
    }

    public void updateCategories() {
        categoryField.setItems(FXCollections.observableList((
            typeIncomeRadio.isSelected() ? parent.getRecord().getIncomeCategories() : parent.getRecord().getExpenseCategories()
        ).stream().toList()));
    }

    public void setOldEntry(BudgetEntry oldEntry) {
        this.oldEntry = oldEntry;
        if (oldEntry != null) {
            titleLabel.setText(I18n.get("forms.entry.edit_title"));
            deleteButton.setVisible(true);
            submitButton.setText(I18n.get("forms.common.actions.apply"));

            (oldEntry.isIncome() ? typeIncomeRadio : typeExpenseRadio).setSelected(true);
            amountField.setText(Double.toString(oldEntry.getAmount()));
            categoryField.setValue(oldEntry.getCategory());
            dateField.setValue(oldEntry.getDate());
            notesField.setText(oldEntry.getDescription());
        } else {
            titleLabel.setText(I18n.get("forms.entry.add_title"));
            deleteButton.setVisible(false);
            submitButton.setText(I18n.get("forms.common.actions.add"));

            amountField.setText("");
            categoryField.setValue("");
            dateField.setValue(LocalDate.now());
            notesField.setText("");
        }
    }

    public void handleSubmit() {
        List<String> errors = validate();
        if (!errors.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle(I18n.get("dialogs.title.error"));
            alert.setHeaderText(I18n.get("dialogs.validation.fail.title"));
            alert.setContentText(String.join("\n", errors));
            alert.show();
            return;
        }

        BudgetEntry entry = makeEntry();
        if (entry == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle(I18n.get("dialogs.title.error"));
            alert.setHeaderText(I18n.get("dialogs.validation.fail.title"));
            alert.setContentText(I18n.get("dialogs.validation.fail.description"));
            alert.show();
            return;
        }

        if (oldEntry != null) parent.getRecord().removeEntry(oldEntry);
        parent.getRecord().addEntry(entry);

        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle(I18n.get("dialogs.title.success"));
            alert.setHeaderText(I18n.get(oldEntry == null ? "dialogs.entry.add_success" : "dialogs.entry.edit_success"));
            alert.show();
        }
        parent.updateScreen();
        close();
    }

    public void handleDelete() {
        {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle(I18n.get("dialogs.title.confirm"));
            alert.setHeaderText(I18n.get("dialogs.entry.delete_confirm.title"));
            alert.setContentText(I18n.get("dialogs.entry.delete_confirm.description"));
            alert.showAndWait();

            if (alert.getResult() == ButtonType.CANCEL) return;
        }

        boolean result = parent.getRecord().removeEntry(oldEntry);

        if (result) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle(I18n.get("dialogs.title.success"));
            alert.setHeaderText(I18n.get("dialogs.entry.delete_success"));
            alert.show();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle(I18n.get("dialogs.title.error"));
            alert.setHeaderText(I18n.get("dialogs.entry.delete_fail"));
            alert.show();
        }

        parent.updateScreen();
        close();
    }

    public List<String> validate() {
        List<String> errors = new ArrayList<>();

        // Validate type
        if (typeGroup.getSelectedToggle() == null) {
            errors.add(I18n.get("forms.entry.validation.type.empty"));
        }
        
        // Validate amount
        if (amountField.getText().isBlank()) {
            errors.add(I18n.get("forms.entry.validation.amount.empty"));
        } else try {
            double amount = Double.parseDouble(amountField.getText().trim());
            if (amount <= 0) {
                errors.add(I18n.get("forms.entry.validation.amount.too_low"));
            }
        } catch (NumberFormatException e) {
            errors.add(I18n.get("forms.entry.validation.amount.not_number"));
        }
        
        // Validate category
        if (categoryField.getValue() == null || categoryField.getValue().isBlank()) {
            errors.add(I18n.get("forms.entry.validation.category.empty"));
        }
        
        // Validate date
        if (dateField.getValue() == null) {
            errors.add(I18n.get("forms.entry.validation.date.empty"));
        }

        return errors; 
    }

    public BudgetEntry makeEntry() {
        try {
            BudgetEntry entry = typeIncomeRadio.isSelected() ? new BudgetEntry.Income() : new BudgetEntry.Expense();
            entry.setDate(dateField.getValue());
            entry.setAmount(Double.parseDouble(amountField.getText().trim()));
            entry.setCategory(categoryField.getValue().trim());
            entry.setDescription(notesField.getText().trim());
            return entry;
        } catch (Exception e) {
            return null;
        }
    }
}
