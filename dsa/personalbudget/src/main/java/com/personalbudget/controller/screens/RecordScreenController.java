package com.personalbudget.controller.screens;

import java.text.NumberFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Currency;
import java.util.stream.Stream;

import com.personalbudget.constants.DateRange;
import com.personalbudget.entities.BudgetEntry;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class RecordScreenController extends AbstractScreenController {

    @FXML private TableView<BudgetEntry> tableView;
    @FXML private TableColumn<BudgetEntry, Instant> tableTimeColumn;
    @FXML private TableColumn<BudgetEntry, String> tableCategoryColumn;
    @FXML private TableColumn<BudgetEntry, String> tableBalanceColumn;
    @FXML private TableColumn<BudgetEntry, String> tableNoteColumn;

    private NumberFormat currencyFormatter;

    @Override
    protected void onParentSet() {
        tableTimeColumn.setCellValueFactory(new PropertyValueFactory<>("instant"));
        tableCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        tableBalanceColumn.setCellValueFactory(cell -> {
            return new SimpleStringProperty(currencyFormatter.format(cell.getValue().getDelta()));
        });
        tableNoteColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
    }

    public void updateTable() {
        Stream<BudgetEntry> stream = null;
        LocalDate from = parent.getDateFrom();

        currencyFormatter = NumberFormat.getCurrencyInstance();
        currencyFormatter.setCurrency(Currency.getInstance(parent.getRecord().getCurrency()));

        if (parent.getDateRange() == DateRange.MONTH) {
            stream = parent.getRecord().monthEntryStream(YearMonth.of(from.getYear(), from.getMonth()));
        }

        if (stream != null) {
            tableView.setItems(FXCollections.observableList(stream.toList()));
        }

        System.out.println(tableView.getItems().size());
    }
}
