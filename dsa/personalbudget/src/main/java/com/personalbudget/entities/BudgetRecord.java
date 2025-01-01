package com.personalbudget.entities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Stream;

import org.json.JSONObject;
import org.json.JSONWriter;

public class BudgetRecord {
    private Map<YearMonth, BudgetMonthRecord> entries;

    private String currency;
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { 
        this.currency = currency;
        isDirty = true;
    }

    private final Set<String> incomeCategories = new TreeSet<>();
    public Set<String> getIncomeCategories() { return incomeCategories; }
    private final Set<String> expenseCategories = new TreeSet<>();
    public Set<String> getExpenseCategories() { return expenseCategories; }

    private boolean isDirty;
    private Path realPath;

    public BudgetRecord() {
        entries = new TreeMap<>();
    }

    public void addEntry(BudgetEntry entry) {
        LocalDate date = entry.getDate();
        YearMonth yearMonth = YearMonth.of(date.getYear(), date.getMonth());

        BudgetMonthRecord monthRecord = getMonthEntry(yearMonth);
        monthRecord.add(entry);

        if (entry.isIncome()) incomeCategories.add(entry.getCategory());
        else expenseCategories.add(entry.getCategory());

        isDirty = true;
    }

    public boolean removeEntry(BudgetEntry entry) {
        LocalDate date = entry.getDate();
        YearMonth yearMonth = YearMonth.of(date.getYear(), date.getMonth());

        BudgetMonthRecord monthRecord = getMonthEntry(yearMonth);


        boolean success = monthRecord.remove(entry);
        isDirty = isDirty || success;
        return success;
    }

    public Stream<BudgetEntry> entryStream() {
        return entries.values().stream().flatMap(BudgetMonthRecord::entryStream);
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
        BudgetMonthRecord record = entries.getOrDefault(yearMonth, null);
        if (record == null) entries.put(yearMonth, record = new BudgetMonthRecord(yearMonth, realPath));
        return record;
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
        List<BudgetMonthRecord> records = new ArrayList<>();
        for (int a = 1; a <= 12; a++) {
            records.add(getMonthEntry(YearMonth.of(year, a)));
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

    // Persistance 

    public void bindFolder(String name) {
        realPath = Path.of(System.getProperty("user.home"), ".personalbudget/records", name);
        System.out.println(realPath.toString());
        File directory = realPath.toFile();
        if (!directory.exists()) System.out.println(directory.mkdirs());
        load();
    }

    public void load() {
        File config = realPath.resolve("config").toFile();
        if (config.exists()) {
            try {
                FileReader reader = new FileReader(config);
                BufferedReader bReader = new BufferedReader(reader);
                JSONObject obj = new JSONObject(bReader.readLine());
                bReader.close(); reader.close();

                setCurrency(obj.getString("currency"));
                incomeCategories.clear();
                obj.getJSONArray("income_categories").forEach(x -> incomeCategories.add((String)x));
                expenseCategories.clear();
                obj.getJSONArray("expense_categories").forEach(x -> expenseCategories.add((String)x));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            isDirty = true;
        }
    }

    public void save() {
        if (!isDirty) return;

        File config = realPath.resolve("config").toFile();
        try {
            if (!config.exists()) config.createNewFile();
            FileWriter writer = new FileWriter(config);
            JSONWriter jw = new JSONWriter(writer);

            jw.object();
            jw.key("currency").value(currency);
            jw.key("income_categories").value(incomeCategories);
            jw.key("expense_categories").value(expenseCategories);
            jw.endObject();

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM", Locale.ENGLISH);
        entries.forEach((ym, month) -> month.save(
            realPath.resolve("data-" + month.getTime().format(formatter)
        )));

        isDirty = false;
    }
}
