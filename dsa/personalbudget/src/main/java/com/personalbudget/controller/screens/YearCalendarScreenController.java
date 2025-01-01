package com.personalbudget.controller.screens;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Locale;

import com.personalbudget.constants.DateRange;
import com.personalbudget.i18n.I18n;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class YearCalendarScreenController extends AbstractScreenController {

    @FXML private GridPane calendarGrid;

    @Override
    protected void onParentSet() {
        // Do nothing
    }

    public void updateCalendar() 
    {
        LocalDate from = parent.getDateFrom();
        Year year = Year.of(from.getYear());

        calendarGrid.getChildren().clear();

        for (int a = 1; a <= 12; a++) 
        {
            YearMonth yearMonth = year.atMonth(a);

            VBox cell = new VBox();
            calendarGrid.add(cell, (a - 1) % 3, (a - 1) / 3);
            cell.getStyleClass().add("calendar-cell");

            Label cellLabel = new Label(yearMonth.getMonth().getDisplayName(TextStyle.FULL, I18n.getLocale()));
            cell.getChildren().add(cellLabel);
            cell.getStyleClass().add("big-text");

            cell.setOnMouseClicked((ev) -> {
                parent.setDateRange(DateRange.MONTH, yearMonth.atDay(1));
            });
        }
    }
}
