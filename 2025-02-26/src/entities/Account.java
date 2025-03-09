package entities;

import exceptions.BusinessException;

public class Account {

    private int number;
    private String holder;
    private double balance;
    private double withdrawLimit;

    public Account(int number, String holder, double balance, double withdrawLimit) {
        this.number = number;
        this.holder = holder;
        this.balance = balance;
        this.withdrawLimit = withdrawLimit;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public void withdraw(double amount) {
        if (amount > withdrawLimit) {
            throw new BusinessException("Withdraw error: The amount exceeds the withdraw limit.");
        }
        if (amount > balance) {
            throw new BusinessException("Withdraw error: Not enough balance.");
        }
        this.balance -= amount;
    }

    public double getBalance() {
        return balance;
    }
}
