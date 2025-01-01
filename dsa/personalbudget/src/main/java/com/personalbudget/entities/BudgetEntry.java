package com.personalbudget.entities;

import java.text.NumberFormat;
import java.time.Instant;
import java.time.LocalDate;

public abstract class BudgetEntry {
    private LocalDate date;
    private String category;
    private String description;
    private double amount;

    public static class Income extends BudgetEntry {
        public Income() {}
        public Income(LocalDate date, String category, String description, double amount) {
            super(date, category, description, amount);
        }

        @Override
        public boolean isIncome() {
            return true;
        }

        @Override
        public double getDelta() {
            return getAmount();
        }

        @Override
        public String toString() {
            return "Income [date = " + getDate() + ", category = " + getCategory() + ", description = " + getDescription() + ", amount = " + getAmount() + "]";
        }
    }

    public static class Expense extends BudgetEntry {
        public Expense() {}
        public Expense(LocalDate date, String category, String description, double amount) {
            super(date, category, description, amount);
        }

        @Override
        public boolean isIncome() {
            return false;
        }

        @Override
        public double getDelta() {
            return -getAmount();
        }

        @Override
        public String toString() {
            return "Expense [date = " + getDate() + ", category = " + getCategory() + ", description = " + getDescription() + ", amount = " + getAmount() + "]";
        }
    }

    protected BudgetEntry() {}
    protected BudgetEntry(LocalDate date, String category, String description, double amount) {
        this.date = date;
        this.category = category;
        this.description = description;
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public abstract boolean isIncome();
    public abstract double getDelta();


}
