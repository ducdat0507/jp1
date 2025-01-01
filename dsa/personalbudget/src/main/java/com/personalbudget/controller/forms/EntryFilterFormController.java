package com.personalbudget.controller.forms;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Currency;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.personalbudget.constants.DateRange;
import com.personalbudget.constants.SortCriteria;
import com.personalbudget.controller.screens.RecordScreenController;
import com.personalbudget.entities.BudgetEntry;
import com.personalbudget.entities.BudgetFilters;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

public class EntryFilterFormController extends AbstractFormController {

    private RecordScreenController recordController;

    @FXML private CheckBox typeIncomeBox;
    @FXML private CheckBox typeExpenseBox;
    @FXML private ComboBox<String> categoryField;
    @FXML private ComboBox<SortCriteria> sortByField;

    @Override
    protected void onParentSet() {
        typeIncomeBox.selectedProperty().addListener((prop, from, to) -> updateCategories());
        typeExpenseBox.selectedProperty().addListener((prop, from, to) -> updateCategories());
        sortByField.getItems().setAll(SortCriteria.values());
        sortByField.valueProperty().addListener((prop, from, to) -> {
            if (to == null) return;
            if (recordController.getSortCriteria() == to) return;
            sortEntries(to);
        });
    }

    public void updateCategories() {
        boolean allSelected = typeIncomeBox.isSelected() && typeExpenseBox.isSelected();
        if (allSelected) {
            categoryField.setValue("");
            categoryField.setDisable(true);
        } else {
            Set<String> categories = new HashSet<>();
            if (typeIncomeBox.isSelected()) categories.addAll(parent.getRecord().incomeCategories);
            if (typeExpenseBox.isSelected()) categories.addAll(parent.getRecord().expenseCategories);
            categories.add("");
            categoryField.setItems(FXCollections.observableList(categories.stream().toList()));
            categoryField.setDisable(false);
        }
    }

    public void setFilters(BudgetFilters filters) {
        typeIncomeBox.setSelected(filters.isContainsIncome());
        typeExpenseBox.setSelected(filters.isContainsExpense());
        categoryField.setValue(filters.getCategory());
        sortByField.setValue(recordController.getSortCriteria());
    }

    public void setRecordController(RecordScreenController recordController) {
        this.recordController = recordController;
        setFilters(recordController.getFilters());
    }

    public void sortEntries(SortCriteria criteria) {
        recordController.setSortCriteria(criteria);
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

        recordController.setFilters(makeFilters());
    }

    public List<String> validate() {
        List<String> errors = new ArrayList<>();

        // Validate type
        if (!typeIncomeBox.isSelected() && !typeExpenseBox.isSelected()) {
            errors.add(I18n.get("forms.filters.validation.type.empty"));
        }

        return errors; 
    }

    public BudgetFilters makeFilters() {
        try {
            BudgetFilters filters = new BudgetFilters();
            filters.setContainsIncome(typeIncomeBox.isSelected());
            filters.setContainsExpense(typeExpenseBox.isSelected());
            filters.setCategory(categoryField.getValue());
            return filters;
        } catch (Exception e) {
            return null;
        }
    }
}
