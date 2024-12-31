package com.personalbudget.controller.screens;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Currency;

import com.personalbudget.constants.DateRange;
import com.personalbudget.entities.BudgetEntry;
import com.personalbudget.entities.BudgetSummary;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;

public class EntryEditFormController extends AbstractFormController {
    public BudgetEntry oldEntry;

    @Override
    protected void onParentSet() {
        // Do nothing
    }

    public void setOldEntry(BudgetEntry oldEntry) {
        this.oldEntry = oldEntry;
    }
}
