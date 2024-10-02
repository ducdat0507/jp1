package service;

public abstract class TransactionService {
    public abstract void deposit(double amount);
    public abstract void withdraw(double amount);
}
