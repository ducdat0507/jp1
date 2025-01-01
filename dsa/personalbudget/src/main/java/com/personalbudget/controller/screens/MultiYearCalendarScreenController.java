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

public class MultiYearCalendarScreenController extends AbstractScreenController {

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

        for (int a = 0; a < 16; a++) 
        {
            Year newYear = year.plusYears(a);

            VBox cell = new VBox();
            calendarGrid.add(cell, a % 4, a / 4);
            cell.getStyleClass().add("calendar-cell");

            Label cellLabel = new Label(Integer.toString(newYear.getValue()));
            cell.getChildren().add(cellLabel);
            cell.getStyleClass().add("big-text");

            cell.setOnMouseClicked((ev) -> {
                parent.setDateRange(DateRange.YEAR, newYear.atDay(1));
            });
        }
    }
}
