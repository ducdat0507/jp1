package com.personalbudget.controller.screens;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Currency;

import com.personalbudget.constants.DateRange;
import com.personalbudget.constants.SortCriteria;
import com.personalbudget.entities.BudgetSummary;
import com.personalbudget.i18n.I18n;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;

public class SummaryScreenController extends AbstractScreenController {

    private BudgetSummary summary;

    @FXML private Label totalIncomeLabel;
    @FXML private Label totalExpenseLabel;
    @FXML private Label totalBalanceLabel;

    @FXML private PieChart incomeByCategoryChart;
    @FXML private PieChart expenseByCategoryChart;

    @Override
    protected void onParentSet() {
        // Do nothing
    }

    public void updateSummary() 
    {
        DateRange range = parent.getDateRange();
        LocalDate from = parent.getDateFrom();
        LocalDate to = parent.getDateTo();

        if (from.compareTo(to) == 0) {
            if (range == DateRange.DAY) {
                summary = parent.getRecord().getDaySummary(from);
            } else if (range == DateRange.MONTH) {
                summary = parent.getRecord().getMonthSummary(YearMonth.of(from.getYear(), from.getMonth()));
            } else if (range == DateRange.YEAR) {
                summary = parent.getRecord().getYearSummary(from.getYear());
            }
        }

        updateUI();
    }

    public void updateUI() {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(I18n.getLocale());
        currencyFormatter.setCurrency(Currency.getInstance(parent.getRecord().getCurrency()));

        totalIncomeLabel.setText(currencyFormatter.format(summary.getTotalIncomes()));
        totalExpenseLabel.setText(currencyFormatter.format(summary.getTotalExpenses()));
        totalBalanceLabel.setText(currencyFormatter.format(summary.getTotalIncomes() - summary.getTotalExpenses()));

        incomeByCategoryChart.setData(FXCollections.observableList(
            summary.getIncomeByCategory().entrySet().stream().map(
                i -> new PieChart.Data(i.getKey(), i.getValue())
            ).toList()
        ));
        expenseByCategoryChart.setData(FXCollections.observableList(
            summary.getExpenseByCategory().entrySet().stream().map(
                i -> new PieChart.Data(i.getKey(), i.getValue())
            ).toList()
        ));
    }
}
