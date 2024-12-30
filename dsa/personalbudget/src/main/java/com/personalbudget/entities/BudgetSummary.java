package com.personalbudget.entities;

import java.util.HashMap;
import java.util.Map;

public class BudgetSummary {
    private double totalIncomes = 0;
    private Map<String, Double> incomeByCategory = new HashMap<>();
    private double totalExpenses = 0;
    private Map<String, Double> expenseByCategory = new HashMap<>();

    public double getTotalIncomes() {
        return totalIncomes;
    }
    public void setTotalIncomes(double totalIncomes) {
        this.totalIncomes = totalIncomes;
    }
    public Map<String, Double> getIncomeByCategory() {
        return incomeByCategory;
    }
    public void setIncomeByCategory(Map<String, Double> incomeByCategory) {
        this.incomeByCategory = Map.copyOf(incomeByCategory);
    }
    public double getTotalExpenses() {
        return totalExpenses;
    }
    public void setTotalExpenses(double totalExpenses) {
        this.totalExpenses = totalExpenses;
    }
    public Map<String, Double> getExpenseByCategory() {
        return expenseByCategory;
    }
    public void setExpenseByCategory(Map<String, Double> expenseByCategory) {
        this.expenseByCategory = Map.copyOf(expenseByCategory);
    }

    
}
