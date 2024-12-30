package com.personalbudget.controller.screens;

import java.text.NumberFormat;

import com.personalbudget.entities.BudgetSummary;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SummaryScreenController extends AbstractScreenController {

    private BudgetSummary summary;

    @FXML private Label totalProfitLabel;
    @FXML private Label totalExpenseLabel;
    @FXML private Label totalBalanceLabel;

    @Override
    protected void onParentSet() {
        updateSummary();
    }

    public void updateSummary() 
    {
        summary = parent.getRecord().getMonthSummary();

        updateUI();
    }

    public void updateUI() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        totalProfitLabel.setText(formatter.format(summary.getTotalProfits()));
        totalExpenseLabel.setText(formatter.format(summary.getTotalExpenses()));
    }
}
