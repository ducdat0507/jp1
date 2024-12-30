package com.personalbudget.entities;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BudgetSummary {
    private double totalProfits = 0;
    private Map<String, Double> profitByCategory = new HashMap<>();
    private double totalExpenses = 0;
    private Map<String, Double> expenseByCategory = new HashMap<>();

    public double getTotalProfits() {
        return totalProfits;
    }
    public void setTotalProfits(double totalProfits) {
        this.totalProfits = totalProfits;
    }
    public Map<String, Double> getProfitByCategory() {
        return profitByCategory;
    }
    public void setProfitByCategory(Map<String, Double> profitByCategory) {
        this.profitByCategory = Map.copyOf(profitByCategory);
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
