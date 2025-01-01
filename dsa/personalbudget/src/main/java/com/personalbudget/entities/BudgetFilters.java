package com.personalbudget.entities;

public class BudgetFilters {
    private boolean containsIncome = true;
    private boolean containsExpense = true;
    private String category = "";

    public BudgetFilters() {}

    public boolean isContainsIncome() {
        return containsIncome;
    }
    public void setContainsIncome(boolean containsIncome) {
        this.containsIncome = containsIncome;
    }
    public boolean isContainsExpense() {
        return containsExpense;
    }
    public void setContainsExpense(boolean containsExpense) {
        this.containsExpense = containsExpense;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
}
