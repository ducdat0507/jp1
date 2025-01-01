package com.personalbudget.constants;

public enum DateRange {
    DAY("Day"),
    MONTH("Month"),
    YEAR("Year"),
    MULTI_YEAR("Multi-Year");

    private String label;
    DateRange(String label) { this.label = label; }
    public String getLabel() { return this.label; }
}
