package com.personalbudget.controller.screens;

import java.text.NumberFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Currency;
import java.util.stream.Stream;

import com.personalbudget.constants.DateRange;
import com.personalbudget.constants.SortCriteria;
import com.personalbudget.controller.forms.EntryFilterFormController;
import com.personalbudget.entities.BudgetEntry;
import com.personalbudget.entities.BudgetFilters;
import com.personalbudget.i18n.I18n;
import com.personalbudget.wheel.QuickSort;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class RecordScreenController extends AbstractScreenController {

    @FXML private TableView<BudgetEntry> tableView;
    @FXML private TableColumn<BudgetEntry, LocalDate> tableTimeColumn;
    @FXML private TableColumn<BudgetEntry, String> tableCategoryColumn;
    @FXML private TableColumn<BudgetEntry, String> tableChangeColumn;
    @FXML private TableColumn<BudgetEntry, String> tableNoteColumn;

    private NumberFormat currencyFormatter;

    private BudgetFilters filters = new BudgetFilters();
    private SortCriteria sortCriteria = SortCriteria.DATE;

    public BudgetFilters getFilters() {
        return filters;
    }
    public void setFilters(BudgetFilters filters) {
        this.filters = filters;
        updateTable();
    }
    public SortCriteria getSortCriteria() {
        return sortCriteria;
    }
    public void setSortCriteria(SortCriteria sortCriteria) {
        this.sortCriteria = sortCriteria;
        updateTable();
    }

    @Override
    protected void onParentSet() {
        tableTimeColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        tableChangeColumn.setCellValueFactory(cell -> {
            return new SimpleStringProperty(currencyFormatter.format(cell.getValue().getDelta()));
        });
        tableNoteColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        tableView.getSelectionModel().selectedItemProperty().addListener((prop, from, to) -> {
            parent.openEditEntryForm(to);
        });
        tableView.setRowFactory(messageTable -> {
            TableRow<BudgetEntry> row = new TableRow<>();
            ChangeListener<? super Object> listener = (prop, from, to) -> {
                BudgetEntry item = row.getItem();
                int index = row.getIndex();
                String odd = index % 2 == 0 ? "" : "-odd";
                if (item == null) {
                    row.getStyleClass().clear(); 
                } else if (item.isIncome()){
                    row.getStyleClass().setAll("income-row" + odd); 
                } else {
                    row.getStyleClass().setAll("expense-row" + odd);
                }
            };
            row.itemProperty().addListener(listener);
            row.indexProperty().addListener(listener);
            return row;
        });
    }

    public void updateTable() {
        Stream<BudgetEntry> stream = null;
        LocalDate from = parent.getDateFrom();

        currencyFormatter = NumberFormat.getCurrencyInstance(I18n.getLocale());
        currencyFormatter.setCurrency(Currency.getInstance(parent.getRecord().getCurrency()));

        DateRange range = parent.getDateRange();

        if (filters.getCategory() != null && !filters.getCategory().isBlank()) {
            if (parent.getDateRange() == DateRange.DAY) {
                stream = parent.getRecord().dayEntryByCategoryStream(from,
                    filters.isContainsIncome(), filters.getCategory()
                );
            } else if (parent.getDateRange() == DateRange.MONTH) {
                stream = parent.getRecord().monthEntryByCategoryStream(YearMonth.of(from.getYear(), from.getMonth()),
                    filters.isContainsIncome(), filters.getCategory()
                );
            } else if (parent.getDateRange() == DateRange.YEAR) {
                stream = parent.getRecord().yearEntryByCategoryStream(from.getYear(),
                    filters.isContainsIncome(), filters.getCategory()
                );
            }
        } else {
            if (parent.getDateRange() == DateRange.DAY) {
                stream = parent.getRecord().dayEntryStream(from);
            } else if (parent.getDateRange() == DateRange.MONTH) {
                stream = parent.getRecord().monthEntryStream(YearMonth.of(from.getYear(), from.getMonth()));
            } else if (parent.getDateRange() == DateRange.YEAR) {
                stream = parent.getRecord().yearEntryStream(from.getYear());
            }

            if (!filters.isContainsIncome()) stream = stream.filter(x -> !x.isIncome());
            if (!filters.isContainsExpense()) stream = stream.filter(x -> x.isIncome());    
        }
        BudgetEntry[] list = stream.toArray(BudgetEntry[]::new);
        if (sortCriteria != SortCriteria.DATE) QuickSort.sort(list, sortCriteria.getComparator());

        if (stream != null) {
            tableView.setItems(FXCollections.observableArrayList(list));
        }

        System.out.println(tableView.getItems().size());
    }

    public void handleFilterButtonClicked() {
        EntryFilterFormController filterForm = parent.getCurrentOrSetForm(EntryFilterFormController.class, "filter");
        filterForm.setRecordController(this);
    }
}
