package com.personalbudget.entities;

import java.time.OffsetDateTime;
import java.time.YearMonth;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

public class BudgetRecord {
    private List<BudgetMonthRecord> entries;

    private String currency;
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public final Set<String> incomeCategories = new TreeSet<>();
    public Set<String> getIncomeCategories() { return Collections.unmodifiableSet(incomeCategories); }
    public final Set<String> expenseCategories = new TreeSet<>();
    public Set<String> getExpenseCategories() { return Collections.unmodifiableSet(expenseCategories); }

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

        if (entry.isIncome()) incomeCategories.add(entry.getCategory());
        else expenseCategories.add(entry.getCategory());
    }

    public void removeEntry(BudgetEntry entry) {
        OffsetDateTime date = entry.getInstant().atOffset(ZoneOffset.ofHours(0));
        YearMonth yearMonth = YearMonth.of(date.getYear(), date.getMonth());

        BudgetMonthRecord monthRecord = new BudgetMonthRecord(yearMonth);
        int index = Collections.binarySearch(entries, monthRecord, Comparator.comparing(BudgetMonthRecord::getTime));
        if (index >= 0) monthRecord = entries.get(index);
        else return;

        monthRecord.remove(entry);
    }

    public Stream<BudgetEntry> entryStream() {
        return entries.stream().flatMap(BudgetMonthRecord::entryStream);
    }

    public Stream<BudgetEntry> monthEntryStream(YearMonth yearMonth) {
        BudgetMonthRecord monthRecord = new BudgetMonthRecord(yearMonth);
        int index = Collections.binarySearch(entries, monthRecord, Comparator.comparing(BudgetMonthRecord::getTime));

        if (index < 0) return Stream.empty();
        else return entries.get(index).entryStream();
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
