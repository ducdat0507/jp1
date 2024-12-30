package com.personalbudget.controller.screens;

import java.text.NumberFormat;

import com.personalbudget.controller.PrimaryController;
import com.personalbudget.entities.BudgetRecord;
import com.personalbudget.entities.BudgetSummary;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public abstract class AbstractScreenController implements ScreenController {
    protected PrimaryController parent;

    public void setParent(PrimaryController parent) 
    {
        this.parent = parent;
        onParentSet();
    }

    protected abstract void onParentSet();
}
