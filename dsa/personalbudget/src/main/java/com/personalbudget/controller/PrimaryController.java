package com.personalbudget.controller;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZonedDateTime;

import com.personalbudget.App;
import com.personalbudget.entity.BudgetEntry;
import com.personalbudget.entity.BudgetMonthRecord;
import com.personalbudget.entity.BudgetRecord;

import javafx.fxml.FXML;
import net.datafaker.Faker;

public class PrimaryController {
    private BudgetRecord test = new BudgetRecord();

    @FXML
    private void initialize() {
        Faker faker = new Faker();
        Instant timeFrom = ZonedDateTime.parse("2024-01-01T00:00:00Z").toInstant();
        for (int a = 0; a < 1000; a++) 
        {
            test.addEntry(new BudgetEntry.Income(
                Instant.ofEpochMilli(faker.number().numberBetween(timeFrom.toEpochMilli(), Instant.now().toEpochMilli())),
                faker.electricalComponents().electromechanical(),
                faker.electricalComponents().electromechanical(),
                faker.number().numberBetween(1200, 2000000)
            ));
            test.addEntry(new BudgetEntry.Expense(
                Instant.ofEpochMilli(faker.number().numberBetween(timeFrom.toEpochMilli(), Instant.now().toEpochMilli())),
                faker.electricalComponents().electromechanical(),
                faker.electricalComponents().electromechanical(),
                faker.number().numberBetween(1200, 1800000)
            ));
        }
        test.entryStream().forEach(x -> System.out.println(x.toString()));
    }
}
