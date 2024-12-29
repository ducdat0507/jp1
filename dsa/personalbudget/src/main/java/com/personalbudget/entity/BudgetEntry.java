package com.personalbudget.entity;

import java.time.Instant;

public abstract class BudgetEntry {
    private Instant instant;
    private String category;
    private String description;
    private double amount;

    public static class Income extends BudgetEntry {
        public Income(Instant instant, String category, String description, double amount) {
            super(instant, category, description, amount);
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
            return "Income [instant = " + getInstant() + ", category = " + getCategory() + ", description = " + getDescription() + ", amount = " + getAmount() + "]";
        }
    }

    public static class Expense extends BudgetEntry {
        public Expense(Instant instant, String category, String description, double amount) {
            super(instant, category, description, amount);
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
            return "Expense [instant = " + getInstant() + ", category = " + getCategory() + ", description = " + getDescription() + ", amount = " + getAmount() + "]";
        }
    }

    protected BudgetEntry(Instant instant, String category, String description, double amount) {
        this.instant = instant;
        this.category = category;
        this.category = description;
        this.amount = amount;
    }

    public Instant getInstant() {
        return instant;
    }
    public void setInstant(Instant instant) {
        this.instant = instant;
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
