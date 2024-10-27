package service;

import entity.Account;

import java.util.regex.*;

public class ValidationService {

    public static double validateNumber(String string) {
        Pattern pattern = Pattern.compile("^-?\\d+(\\.\\d*)?$");
        Matcher matcher = pattern.matcher(string);
        if (matcher.matches()) return Double.parseDouble(string);
        System.out.println("\033[31m"+"Input must be a number"+"\033[0m");
        throw new IllegalArgumentException("Input must be a number");
    }

    public static boolean validateDeposit(Account account, double amount) {
        if (amount <= 0) {
            System.out.println("\033[31m"+"Amount must be greater than 0"+"\033[0m");
            return false;
        }
        if (amount >= account.getBalance()) {
            System.out.println("\033[31m"+"Amount exceed current balance"+"\033[0m");
            return false;
        }
        return true;
    }

    public static boolean validateWithdraw(Account account, double amount) {
        if (amount <= 0) {
            System.out.println("\033[31m"+"Amount must be greater than 0"+"\033[0m");
            return false;
        }
        return true;
    }
}
