package com.personalbudget.entities;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import com.personalbudget.wheel.Index;

public class BudgetMonthRecord {
    private YearMonth time;
    private List<BudgetEntry> entries;

    private Index<String, BudgetEntry> incomeCategoryIndex;
    private Index<String, BudgetEntry> expenseCategoryIndex;

    private Map<String, Double> totalIncomes;
    private Map<String, Double> totalExpenses;

    public BudgetMonthRecord(YearMonth time) {
        this.time = time;
        entries = new ArrayList<>();
        incomeCategoryIndex = new Index<>();
        expenseCategoryIndex = new Index<>();
        totalIncomes = new HashMap<>();
        totalExpenses = new HashMap<>();
    }

    public void add(BudgetEntry entry) {
        if (entries.size() == 0) entries.add(entry);
        else if (entry.getDate().compareTo(entries.get(entries.size() - 1).getDate()) >= 0) entries.add(entry);
        else {
            int index = Collections.binarySearch(entries, entry, Comparator.comparing(BudgetEntry::getDate));
            if (index < 0) index = -index - 1;
            entries.add(index, entry);
        }

        String category = entry.getCategory();
        (entry.isIncome() ? incomeCategoryIndex : expenseCategoryIndex).add(category, entry);

        if (entry.isIncome()) {
            if (totalIncomes.containsKey(category)) totalIncomes.put(category, totalIncomes.get(category) + entry.getAmount());
            else totalIncomes.put(category, entry.getAmount());
        } else {
            if (totalExpenses.containsKey(category)) totalExpenses.put(category, totalExpenses.get(category) + entry.getAmount());
            else totalExpenses.put(category, entry.getAmount());
        }
    }

    public boolean remove(BudgetEntry entry) {
        int index = findIndex(entries, entry);
        if (index < 0) return false;

        System.out.println(entries.size() + " " + index);
        entries.remove(index);
        System.out.println(entries.size());

        String category = entry.getCategory();
        (entry.isIncome() ? incomeCategoryIndex : expenseCategoryIndex).remove(category, entry);

        if (entry.isIncome()) {
            totalIncomes.put(category, totalIncomes.get(category) - entry.getAmount());
        } else {
            totalExpenses.put(category, totalExpenses.get(category) - entry.getAmount());
        }

        return true;
    }

    public List<BudgetEntry> getEntriesByCategory(boolean isIncome, String category) {
        if (isIncome) return incomeCategoryIndex.get(category);
        else return expenseCategoryIndex.get(category);
    }

    public List<BudgetEntry> getDayEntriesByCategory(int day, boolean isIncome, String category) {
        return getDayEntries(getEntriesByCategory(isIncome, category), day);
    }
    public List<BudgetEntry> getDayEntries(int day) {
        return getDayEntries(entries, day);
    }

    private List<BudgetEntry> getDayEntries(List<BudgetEntry> entries, int day) {
        BudgetEntry target = new BudgetEntry.Income();
        target.setDate(time.atDay(day));

        List<BudgetEntry> list = new LinkedList<>();

        int index = Collections.binarySearch(entries, target, Comparator.comparing(BudgetEntry::getDate));
        if (index < 0) index = -index - 1;

        int index2 = index;
        while (index2 >= 0 && entries.get(index2).getDate().compareTo(target.getDate()) == 0) {
            list.addFirst(entries.get(index2));
            index2--;
        }
        index2 = index + 1;
        while (index2 < entries.size() && entries.get(index2).getDate().compareTo(target.getDate()) == 0) {
            list.addLast(entries.get(index2));
            index2++;
        }
        return list;
    }

    private static int findIndex(List<BudgetEntry> list, BudgetEntry target) {
        int index = Collections.binarySearch(list, target, Comparator.comparing(BudgetEntry::getDate));
        if (index < 0) return -1;

        int index2 = index;
        while (target != list.get(index2)) {
            index2--;
            if (index2 < 0) break;
            if (list.get(index).getDate().compareTo(target.getDate()) < 0) break;
            if (target == list.get(index2)) return index2;
        }
        while (target != list.get(index)) {
            index++;
            if (index >= list.size()) return -1;
            if (list.get(index).getDate().compareTo(target.getDate()) > 0) return -1;
        }
        return index;
    }
    
    public YearMonth getTime() {
        return time;
    }
    public void setTime(YearMonth time) {
        this.time = time;
    }

    public Map<String, Double> getAllCategoriesMonthlyIncomes() {
        return Collections.unmodifiableMap(totalIncomes);
    }
    public double getOneCategoryMonthlyIncomes(String category) {
        return totalIncomes.containsKey(category) ? totalIncomes.get(category) : 0;
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
