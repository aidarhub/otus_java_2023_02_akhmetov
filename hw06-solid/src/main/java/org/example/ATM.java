package org.example;

public interface ATM {
    void deposit(int denomination, int count);
    boolean withdraw(int sum);
    int getBalance();
}