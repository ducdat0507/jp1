import entity.CurrentAccount;
import entity.SavingsAccount;

public class Main {
    public static void main(String[] args) {
        double ibal, damt, wamt;
        ibal = 1000.00;
        SavingsAccount savingsAccount= new SavingsAccount("SA001", ibal);
        System.out.println(String.format("Savings account: Initial Balace: $%.2f", ibal));
        damt= 500.00;
        savingsAccount.deposit(damt);
        wamt= 250.00;
        savingsAccount.withdraw(wamt);
        wamt= 1600.00;
        System.out.println(String.format("Try to withdraw: $%.2f", wamt));
        savingsAccount.withdraw(wamt);
        System.out.println();
        ibal = 5000.00;
        CurrentAccount currentAccount = new CurrentAccount("PA001", ibal);
        System.out.println(String.format("Current account: Initial Balace: $%.2f", ibal));
        damt= 2500.00;
        currentAccount.deposit(1000.0);
        wamt= 1250.00;
        currentAccount.withdraw(3000.0);
        wamt= 6000.00;
        System. out. println(String.format("Try to withdraw: $%.2f", wamt));
        savingsAccount.withdraw(wamt);
    }
}
