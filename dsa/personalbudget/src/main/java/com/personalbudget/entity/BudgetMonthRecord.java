package com.personalbudget.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.personalbudget.wheel.Index;

public class BudgetMonthRecord {
    private List<BudgetEntry> entries;
    private Index<String, BudgetEntry> categoryIndex;

    public BudgetMonthRecord() {
        entries = new ArrayList<>();
        categoryIndex = new Index<>();
    }

    public void add(BudgetEntry entry) {
        if (entry.getInstant().compareTo(entries.get(entries.size() - 1).getInstant()) > 0) entries.add(entry);
        else {
            int index = Collections.binarySearch(entries, entry, Comparator.comparing(BudgetEntry::getInstant));
            if (index < 0) index = -index - 1;
            entries.add(index, entry);
        }

        categoryIndex.add(entry.getCategory(), entry);
    }
    
}
