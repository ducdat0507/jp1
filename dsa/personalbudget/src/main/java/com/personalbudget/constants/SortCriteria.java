package com.personalbudget.constants;

import java.util.Comparator;

import com.personalbudget.entities.BudgetEntry;
import com.personalbudget.i18n.I18n;

public enum SortCriteria {
    DATE(Comparator.comparing(BudgetEntry::getDate), "main.terms.date"),
    CATEGORY(Comparator.comparing(BudgetEntry::getCategory), "main.terms.category"),
    AMOUNT(Comparator.comparing(BudgetEntry::getAmount), "main.sort.by.amount");

    private String labelKey;
    private Comparator<BudgetEntry> comparator;
    SortCriteria(Comparator<BudgetEntry> comparator, String labelKey) { 
        this.labelKey = labelKey; 
        this.comparator = comparator;
    }
    public Comparator<BudgetEntry> getComparator() { return this.comparator; }
    public String getLabelKey() { return this.labelKey; }
    public String getLabel() { return I18n.get(this.labelKey); }
    public String toString() { return getLabel(); }
}
