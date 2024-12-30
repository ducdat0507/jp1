package com.personalbudget.controller.screens;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Locale;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class MonthCalendarScreenController extends AbstractScreenController {

    @FXML private GridPane calendarGrid;

    @Override
    protected void onParentSet() {
        // Do nothing
    }

    public void updateCalendar() 
    {
        LocalDate from = parent.getDateFrom();
        YearMonth yearMonth = YearMonth.of(from.getYear(), from.getMonth());
        int daysCount = yearMonth.lengthOfMonth();

        calendarGrid.getChildren().clear();

        for (int a = 1; a <= 7; a++) 
        {
            Label cellLabel = new Label(DayOfWeek.of(a).getDisplayName(TextStyle.FULL_STANDALONE, Locale.ENGLISH));
            calendarGrid.add(cellLabel, a - 1, 0);
            GridPane.setHgrow(cellLabel, Priority.ALWAYS);
            GridPane.setVgrow(cellLabel, Priority.ALWAYS);
            cellLabel.setMaxHeight(Double.MAX_VALUE);
            cellLabel.setMaxWidth(Double.MAX_VALUE);
            cellLabel.getStyleClass().add("calendar-head");
            cellLabel.setAlignment(Pos.CENTER);
        }

        int row = 1;
        for (int a = 1; a <= daysCount; a++) 
        {
            LocalDate day = yearMonth.atDay(a);

            VBox cell = new VBox();
            calendarGrid.add(cell, day.getDayOfWeek().getValue() - 1, row);
            cell.getStyleClass().add("calendar-cell");

            Label cellLabel = new Label(Integer.toString(a));
            cell.getChildren().add(cellLabel);
            cell.getStyleClass().add("big-text");

            if (day.getDayOfWeek().getValue() == 7) row++;
        }
    }
}
