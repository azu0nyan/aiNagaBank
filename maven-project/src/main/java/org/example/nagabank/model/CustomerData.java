package org.example.nagabank.model;

/**
 * Stores financial data for each customer
 */
public class CustomerData {
    private double balance;
    private double loanAmount;

    public CustomerData() {
        this.balance = 0.0;
        this.loanAmount = 0.0;
    }

    public CustomerData(double balance, double loanAmount) {
        this.balance = balance;
        this.loanAmount = loanAmount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    @Override
    public String toString() {
        return "CustomerData{" +
                "balance=" + balance +
                ", loanAmount=" + loanAmount +
                '}';
    }
}
