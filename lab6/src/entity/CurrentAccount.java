package entity;

public class CurrentAccount extends BankAccount {

    public CurrentAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    public void deposit(double amount) {
        if (amount <= 0) {
            System.err.println("\033[91m"+"Amount must be greater than zero"+"\033[0m");
            return;
        }
        setBalance(getBalance() + amount);
        System.out.println(String.format("Successfully deposit $%.2f", amount));
        System.out.println(String.format("Current balance is $%.2f", getBalance()));
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.err.println("\033[91m"+"Amount must be greater than zero"+"\033[0m");
            return;
        }
        if (amount > getBalance()) {
            System.err.println("\033[91m"+"Amount exceeds current balance"+"\033[0m");
            return;
        }
        setBalance(getBalance() - amount);
        System.out.println(String.format("Successfully withdrawn $%.2f", amount));
        System.out.println(String.format("Current balance is $%.2f", getBalance()));
    }
}
