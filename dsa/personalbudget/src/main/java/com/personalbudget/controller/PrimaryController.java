package com.personalbudget.controller;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;

import com.personalbudget.App;
import com.personalbudget.entity.BudgetEntry;
import com.personalbudget.entity.BudgetMonthRecord;

import javafx.fxml.FXML;
import net.datafaker.Faker;

public class PrimaryController {
    private BudgetMonthRecord test = new BudgetMonthRecord();

    @FXML
    private void initialize() {
        Faker faker = new Faker();
        test.add(new BudgetEntry(
            faker.date().between(OffsetDateTime.parse("2024-01-01T00:00:00Z"), Date.now())
        ));
    }
}
