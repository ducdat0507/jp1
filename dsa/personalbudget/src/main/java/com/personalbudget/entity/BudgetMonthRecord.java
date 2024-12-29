package com.personalbudget.entity;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import com.personalbudget.wheel.Index;

public class BudgetMonthRecord {
    private YearMonth time;
    private List<BudgetEntry> entries;
    private Index<String, BudgetEntry> categoryIndex;

    private Map<String, Double> totalProfits;
    private Map<String, Double> totalExpenses;

    public BudgetMonthRecord(YearMonth time) {
        this.time = time;
        entries = new ArrayList<>();
        categoryIndex = new Index<>();
        totalProfits = new HashMap<>();
        totalExpenses = new HashMap<>();
    }

    public void add(BudgetEntry entry) {
        if (entries.size() == 0) entries.add(entry);
        else if (entry.getInstant().compareTo(entries.get(entries.size() - 1).getInstant()) > 0) entries.add(entry);
        else {
            int index = Collections.binarySearch(entries, entry, Comparator.comparing(BudgetEntry::getInstant));
            if (index < 0) index = -index - 1;
            entries.add(index, entry);
        }

        String category = entry.getCategory();
        categoryIndex.add(category, entry);

        if (entry.isIncome()) {
            if (totalProfits.containsKey(category)) totalProfits.put(category, totalProfits.get(category) + entry.getAmount());
            else totalProfits.put(category, entry.getAmount());
        } else {
            if (totalExpenses.containsKey(category)) totalExpenses.put(category, totalExpenses.get(category) + entry.getAmount());
            else totalExpenses.put(category, entry.getAmount());
        }
        
    }
    
    public YearMonth getTime() {
        return time;
    }
    public void setTime(YearMonth time) {
        this.time = time;
    }

    public Map<String, Double> getAllCategoriesMonthlyProfits() {
        return Collections.unmodifiableMap(totalProfits);
    }
    public double getOneCategoryMonthlyProfits(String category) {
        return totalProfits.containsKey(category) ? totalProfits.get(category) : 0;
    }
    public Map<String, Double> getAllCategoriesMonthlyExpenses() {
        return Collections.unmodifiableMap(totalExpenses);
    }
    public double getOneCategoryMonthlyExpenses(String category) {
        return totalExpenses.containsKey(category) ? totalExpenses.get(category) : 0;
    }

    public Stream<BudgetEntry> entryStream() {
        return entries.stream();
    }
}
