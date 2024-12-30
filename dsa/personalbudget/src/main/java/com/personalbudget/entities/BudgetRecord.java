package com.personalbudget.entities;

import java.time.OffsetDateTime;
import java.time.YearMonth;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class BudgetRecord {
    private List<BudgetMonthRecord> entries;

    public BudgetRecord() {
        entries = new ArrayList<>();
    }

    public void addEntry(BudgetEntry entry) {
        OffsetDateTime date = entry.getInstant().atOffset(ZoneOffset.ofHours(0));
        YearMonth yearMonth = YearMonth.of(date.getYear(), date.getMonth());

        BudgetMonthRecord monthRecord = new BudgetMonthRecord(yearMonth);
        int index = Collections.binarySearch(entries, monthRecord, Comparator.comparing(BudgetMonthRecord::getTime));
        if (index >= 0) monthRecord = entries.get(index);
        else entries.add(-index - 1, monthRecord);

        monthRecord.add(entry);
    }

    public Stream<BudgetEntry> entryStream() {
        return entries.stream().flatMap(BudgetMonthRecord::entryStream);
    }
    
    public BudgetSummary getMonthSummary(YearMonth yearMonth) {
        BudgetSummary summary = new BudgetSummary();

        BudgetMonthRecord monthRecord = new BudgetMonthRecord(yearMonth);
        int index = Collections.binarySearch(entries, monthRecord, Comparator.comparing(BudgetMonthRecord::getTime));

        if (index < 0) return summary;
        else monthRecord = entries.get(index);

        summary.setIncomeByCategory(monthRecord.getAllCategoriesMonthlyIncomes());
        summary.getIncomeByCategory().entrySet().forEach(entry -> {
            summary.setTotalIncomes(summary.getTotalIncomes() + entry.getValue());
        });
        summary.setExpenseByCategory(monthRecord.getAllCategoriesMonthlyExpenses());
        summary.getExpenseByCategory().entrySet().forEach(entry -> {
            summary.setTotalExpenses(summary.getTotalExpenses() + entry.getValue());
        });
        return summary;
    }
}
