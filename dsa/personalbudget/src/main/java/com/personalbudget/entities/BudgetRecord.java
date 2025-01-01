package com.personalbudget.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

public class BudgetRecord {
    private List<BudgetMonthRecord> entries;

    private String currency;
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public final Set<String> incomeCategories = new TreeSet<>();
    public Set<String> getIncomeCategories() { return incomeCategories; }
    public final Set<String> expenseCategories = new TreeSet<>();
    public Set<String> getExpenseCategories() { return expenseCategories; }

    public BudgetRecord() {
        entries = new ArrayList<>();
    }

    public void addEntry(BudgetEntry entry) {
        LocalDate date = entry.getDate();
        YearMonth yearMonth = YearMonth.of(date.getYear(), date.getMonth());

        BudgetMonthRecord monthRecord = new BudgetMonthRecord(yearMonth);
        int index = Collections.binarySearch(entries, monthRecord, Comparator.comparing(BudgetMonthRecord::getTime));
        if (index >= 0) monthRecord = entries.get(index);
        else entries.add(-index - 1, monthRecord);

        monthRecord.add(entry);

        if (entry.isIncome()) incomeCategories.add(entry.getCategory());
        else expenseCategories.add(entry.getCategory());
    }

    public boolean removeEntry(BudgetEntry entry) {
        LocalDate date = entry.getDate();
        YearMonth yearMonth = YearMonth.of(date.getYear(), date.getMonth());

        BudgetMonthRecord monthRecord = new BudgetMonthRecord(yearMonth);
        int index = Collections.binarySearch(entries, monthRecord, Comparator.comparing(BudgetMonthRecord::getTime));
        if (index >= 0) monthRecord = entries.get(index);
        else return false;

        return monthRecord.remove(entry);
    }

    public Stream<BudgetEntry> entryStream() {
        return entries.stream().flatMap(BudgetMonthRecord::entryStream);
    }

    // Day 

    public Stream<BudgetEntry> dayEntryStream(LocalDate date) {
        BudgetMonthRecord monthRecord = getMonthEntry(YearMonth.of(date.getYear(), date.getMonth()));
        if (monthRecord == null) return Stream.empty();
        return monthRecord.getDayEntries(date.getDayOfMonth()).stream();
    }

    public Stream<BudgetEntry> dayEntryByCategoryStream(LocalDate date, boolean isIncome, String category) {
        BudgetMonthRecord monthRecord = getMonthEntry(YearMonth.of(date.getYear(), date.getMonth()));
        if (monthRecord == null) return Stream.empty();
        return monthRecord.getDayEntriesByCategory(date.getDayOfMonth(), isIncome, category).stream();
    }
    
    public BudgetSummary getDaySummary(LocalDate date) {
        BudgetSummary summary = new BudgetSummary();

        BudgetMonthRecord monthRecord = getMonthEntry(YearMonth.of(date.getYear(), date.getMonth()));
        if (monthRecord == null) return summary;

        Map<String, Double> incomeByCategory = summary.getIncomeByCategory();
        Map<String, Double> expenseByCategory = summary.getExpenseByCategory();
        
        monthRecord.getDayEntries(date.getDayOfMonth()).stream().forEach(entry -> {
            if (entry.isIncome()) {
                summary.setTotalIncomes(summary.getTotalIncomes() + entry.getAmount());
                double sum = incomeByCategory.getOrDefault(entry.getCategory(), 0.0);
                incomeByCategory.put(entry.getCategory(), sum + entry.getAmount());
            } else {
                summary.setTotalExpenses(summary.getTotalExpenses() + entry.getAmount());
                double sum = expenseByCategory.getOrDefault(entry.getCategory(), 0.0);
                expenseByCategory.put(entry.getCategory(), sum + entry.getAmount());
            }
        });

        return summary;
    }

    // Month

    private BudgetMonthRecord getMonthEntry(YearMonth yearMonth) {
        BudgetMonthRecord monthRecord = new BudgetMonthRecord(yearMonth);
        int index = Collections.binarySearch(entries, monthRecord, Comparator.comparing(BudgetMonthRecord::getTime));

        if (index < 0) return null;
        else return entries.get(index);
    }

    public Stream<BudgetEntry> monthEntryStream(YearMonth yearMonth) {
        BudgetMonthRecord monthRecord = getMonthEntry(yearMonth);

        if (monthRecord == null) return Stream.empty();
        else return monthRecord.entryStream();
    }

    public Stream<BudgetEntry> monthEntryByCategoryStream(YearMonth yearMonth, boolean isIncome, String category) {
        BudgetMonthRecord monthRecord = getMonthEntry(yearMonth);
        if (monthRecord == null) return Stream.empty();
        else return monthRecord.getEntriesByCategory(isIncome, category).stream();
    }
    
    public BudgetSummary getMonthSummary(YearMonth yearMonth) {
        BudgetSummary summary = new BudgetSummary();

        BudgetMonthRecord monthRecord = getMonthEntry(yearMonth);
        if (monthRecord == null) return summary;

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

    // Year

    private List<BudgetMonthRecord> getRecordsInYear(int year) {
        BudgetMonthRecord monthRecord = new BudgetMonthRecord(YearMonth.of(year, Month.JANUARY));
        int index = Collections.binarySearch(entries, monthRecord, Comparator.comparing(BudgetMonthRecord::getTime));
        if (index < 0) index = -index - 1;

        List<BudgetMonthRecord> records = new ArrayList<>();
        while (index < entries.size() && entries.get(index).getTime().getYear() == year) {
            records.add(entries.get(index));
            index++;
        }
        return records;
    }

    public Stream<BudgetEntry> yearEntryStream(int year) {
        return getRecordsInYear(year).stream().flatMap(x -> x.entryStream());
    }
    public Stream<BudgetEntry> yearEntryByCategoryStream(int year, boolean isIncome, String category) {
        return getRecordsInYear(year).stream().flatMap(x -> x.getEntriesByCategory(isIncome, category).stream());
    }
    
    public BudgetSummary getYearSummary(int year) {
        BudgetSummary summary = new BudgetSummary();

        getRecordsInYear(year).forEach(monthRecord -> {
            summary.setIncomeByCategory(monthRecord.getAllCategoriesMonthlyIncomes());
            summary.getIncomeByCategory().entrySet().forEach(entry -> {
                summary.setTotalIncomes(summary.getTotalIncomes() + entry.getValue());
            });
            summary.setExpenseByCategory(monthRecord.getAllCategoriesMonthlyExpenses());
            summary.getExpenseByCategory().entrySet().forEach(entry -> {
                summary.setTotalExpenses(summary.getTotalExpenses() + entry.getValue());
            });
        });

        return summary;
    }
}
