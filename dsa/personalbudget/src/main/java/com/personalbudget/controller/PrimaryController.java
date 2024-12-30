package com.personalbudget.controller;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZonedDateTime;

import com.personalbudget.App;
import com.personalbudget.constants.DateRange;
import com.personalbudget.controller.screens.MonthCalendarScreenController;
import com.personalbudget.controller.screens.ScreenController;
import com.personalbudget.controller.screens.SummaryScreenController;
import com.personalbudget.entities.BudgetEntry;
import com.personalbudget.entities.BudgetRecord;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import net.datafaker.Faker;

public class PrimaryController {
    private BudgetRecord record = new BudgetRecord();

    private DateRange dateRange = DateRange.DAY;
    private LocalDate dateFrom = YearMonth.now().atDay(1);
    private LocalDate dateTo = YearMonth.now().atDay(1);

    @FXML private ToggleButton summaryTabButton;
    @FXML private ToggleButton calendarTabButton;
    @FXML private ToggleButton recordTabButton;
    private ToggleButton currentTabButton;

    @FXML
    private Pane screenPane;
    private ScreenController screenController;

    public BudgetRecord getRecord() { 
        return record; 
    }
    public DateRange getDateRange() {
        return dateRange;
    }
    public LocalDate getDateFrom() {
        return dateFrom;
    }
    public LocalDate getDateTo() {
        return dateTo;
    }
    

    @FXML
    private void initialize() {
        Faker faker = new Faker();
        Instant timeFrom = ZonedDateTime.parse("2024-01-01T00:00:00Z").toInstant();
        for (int a = 0; a < 1000; a++) 
        {
            record.addEntry(new BudgetEntry.Income(
                Instant.ofEpochMilli(faker.number().numberBetween(timeFrom.toEpochMilli(), Instant.now().toEpochMilli())),
                faker.electricalComponents().electromechanical(),
                faker.electricalComponents().electromechanical(),
                faker.number().numberBetween(1200, 2000000)
            ));
            record.addEntry(new BudgetEntry.Expense(
                Instant.ofEpochMilli(faker.number().numberBetween(timeFrom.toEpochMilli(), Instant.now().toEpochMilli())),
                faker.electricalComponents().electromechanical(),
                faker.electricalComponents().electromechanical(),
                faker.number().numberBetween(1200, 1800000)
            ));
        }
        record.entryStream().forEach(x -> System.out.println(x.toString()));

        // 
        ToggleGroup tabGroup = new ToggleGroup();
        tabGroup.getToggles().addAll(summaryTabButton, calendarTabButton, recordTabButton);
        tabGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> prop, Toggle from, Toggle to) {
                setTab((ToggleButton)to);
            }
        });

        setTab(summaryTabButton);
    }

    private void setScreen(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("screens/" + fxml + ".fxml"));
        Pane form = fxmlLoader.load();
        screenPane.getChildren().setAll(form);
        form.prefWidthProperty().bind(screenPane.widthProperty());
        form.prefHeightProperty().bind(screenPane.heightProperty());
        screenController = fxmlLoader.getController();
        screenController.setParent(this);
    }

    private boolean setScreenSafe(String fxml) {
        try { setScreen(fxml); return true; }
        catch (IOException e) { e.printStackTrace(); return false; }
    }

    private void updateTabUI() {
        summaryTabButton.setDisable(currentTabButton == summaryTabButton);
        calendarTabButton.setDisable(currentTabButton == calendarTabButton);
        recordTabButton.setDisable(currentTabButton == recordTabButton);
    }

    private void setTab(ToggleButton tabButton) {
        currentTabButton = tabButton;
        tabButton.setSelected(true);

        if (currentTabButton == summaryTabButton) {
            if (screenController instanceof SummaryScreenController || setScreenSafe("summary")) {
                ((SummaryScreenController)screenController).updateSummary();
            }
        }
        else if (currentTabButton == calendarTabButton) {
            if (screenController instanceof MonthCalendarScreenController || setScreenSafe("month-calendar")) {
                ((MonthCalendarScreenController)screenController).updateCalendar();
            }
        }

        updateTabUI();
    }
}
