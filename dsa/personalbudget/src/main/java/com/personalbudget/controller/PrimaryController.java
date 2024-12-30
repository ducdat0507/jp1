package com.personalbudget.controller;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZonedDateTime;

import com.personalbudget.App;
import com.personalbudget.constants.DateRange;
import com.personalbudget.controller.screens.AbstractScreenController;
import com.personalbudget.controller.screens.ScreenController;
import com.personalbudget.entities.BudgetEntry;
import com.personalbudget.entities.BudgetRecord;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import net.datafaker.Faker;

public class PrimaryController {
    private BudgetRecord record = new BudgetRecord();

    private DateRange dateRange = DateRange.DAY;
    private LocalDate dateFrom = YearMonth.now().atDay(1);
    private LocalDate dateTo = YearMonth.now().atDay(1);

    @FXML
    private Pane screenPane;
    private ScreenController screenController;

    public BudgetRecord getRecord() { 
        return record; 
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

        setScreenSafe("summary");
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
}
