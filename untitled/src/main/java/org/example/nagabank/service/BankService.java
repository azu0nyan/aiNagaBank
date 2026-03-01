package org.example.nagabank.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.nagabank.model.CustomerData;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * NAGA-BANK Service - Where the Nagas guard your gold! 🐍💰
 * Core banking operations with HashMap-based storage + JSON persistence
 */
public class BankService {
    private static final String DATA_FILE = "nagabank_data.json";
    private final Map<String, CustomerData> customerDataMap;
    private double bankTotalBalance;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public BankService() {
        this.customerDataMap = new HashMap<>();
        this.bankTotalBalance = 100000.0; // Bank starts with 100k gold for loans
        loadData();
        System.out.println("🐍 Loaded " + customerDataMap.size() + " customers from persistence");
    }
    
    /**
     * Load data from JSON file
     */
    private void loadData() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            return;
        }
        
        try (Reader reader = new FileReader(file)) {
            Type type = new TypeToken<BankData>(){}.getType();
            BankData data = gson.fromJson(reader, type);
            if (data != null && data.customers != null) {
                customerDataMap.putAll(data.customers);
                bankTotalBalance = data.bankTotalBalance;
            }
        } catch (Exception e) {
            System.err.println("⚠️ Error loading data: " + e.getMessage());
        }
    }
    
    /**
     * Save data to JSON file
     */
    private void saveData() {
        try (Writer writer = new FileWriter(DATA_FILE)) {
            BankData data = new BankData();
            data.customers = new HashMap<>(customerDataMap);
            data.bankTotalBalance = bankTotalBalance;
            gson.toJson(data, writer);
        } catch (Exception e) {
            System.err.println("⚠️ Error saving data: " + e.getMessage());
        }
    }
    
    /**
     * Data container for JSON serialization
     */
    private static class BankData {
        Map<String, CustomerData> customers;
        double bankTotalBalance;
    }

    /**
     * Withdraw money from customer account
     */
    public String withdraw(String customerId, double amount) {
        if (amount <= 0) {
            return "Error: Amount must be positive!";
        }

        CustomerData data = getOrCreateCustomerData(customerId);
        
        if (data.getBalance() < amount) {
            return String.format("Insufficient fundsss! Your balance: %.2f gold, requested: %.2f gold", 
                    data.getBalance(), amount);
        }

        data.setBalance(data.getBalance() - amount);
        saveData();
        return String.format("Withdrawal successful! Withdrew %.2f gold. New balance: %.2f gold", 
                amount, data.getBalance());
    }

    /**
     * Deposit money to customer account
     */
    public String deposit(String customerId, double amount) {
        if (amount <= 0) {
            return "Error: Amount must be positive!";
        }

        CustomerData data = getOrCreateCustomerData(customerId);
        data.setBalance(data.getBalance() + amount);
        saveData();
        
        return String.format("Deposit successful! Deposited %.2f gold. New balance: %.2f gold", 
                amount, data.getBalance());
    }

    /**
     * Take a loan from the bank (decreases bank's total balance)
     */
    public String takeLoan(String customerId, double amount) {
        if (amount <= 0) {
            return "Error: Loan amount must be positive!";
        }

        if (amount > bankTotalBalance) {
            return String.format("The Nagasss cannot grant such a large loan! Bank reserves: %.2f gold", 
                    bankTotalBalance);
        }

        CustomerData data = getOrCreateCustomerData(customerId);
        
        // Add loan to customer's loan amount
        data.setLoanAmount(data.getLoanAmount() + amount);
        // Give cash to customer's balance
        data.setBalance(data.getBalance() + amount);
        // Decrease bank's reserves
        bankTotalBalance -= amount;
        saveData();

        return String.format("Loan approved! %.2f gold added to your account. Total debt: %.2f gold. The Nagasss will remember...", 
                amount, data.getLoanAmount());
    }

    /**
     * Pay back a loan
     */
    public String payLoan(String customerId, double amount) {
        if (amount <= 0) {
            return "Error: Payment amount must be positive!";
        }

        CustomerData data = getOrCreateCustomerData(customerId);
        
        if (data.getLoanAmount() == 0) {
            return "You have no debt! The Nagasss are pleased with you.";
        }

        if (data.getBalance() < amount) {
            return String.format("Insufficient fundsss to pay! Your balance: %.2f gold, payment: %.2f gold", 
                    data.getBalance(), amount);
        }

        double actualPayment = Math.min(amount, data.getLoanAmount());
        
        // Deduct from balance
        data.setBalance(data.getBalance() - actualPayment);
        // Reduce loan
        data.setLoanAmount(data.getLoanAmount() - actualPayment);
        // Return to bank reserves
        bankTotalBalance += actualPayment;
        saveData();

        if (data.getLoanAmount() == 0) {
            return String.format("Loan fully repaid! Paid %.2f gold. You are free from debt! The Nagasss blesss you!", 
                    actualPayment);
        } else {
            return String.format("Payment received! Paid %.2f gold. Remaining debt: %.2f gold", 
                    actualPayment, data.getLoanAmount());
        }
    }

    /**
     * Pay all loans with all available balance - become a debt slave to the Nagas! (HOMM3 reference)
     */
    public String payLoansAsSlave(String customerId) {
        CustomerData data = getOrCreateCustomerData(customerId);
        
        if (data.getLoanAmount() == 0) {
            return "You have no debt! No need to become a sslave to the Nagasss!";
        }

        if (data.getBalance() == 0) {
            return "You have no gold to pay! The Nagasss will wait... but not forever!";
        }

        double payment = Math.min(data.getBalance(), data.getLoanAmount());
        
        // Deduct all available balance
        data.setBalance(data.getBalance() - payment);
        // Reduce loan
        data.setLoanAmount(data.getLoanAmount() - payment);
        // Return to bank reserves
        bankTotalBalance += payment;
        saveData();

        if (data.getLoanAmount() == 0) {
            return String.format("You have sacrificed ALL your gold (%.2f) to pay your debt! You are free but poor... The Nagasss respect your dedication!", 
                    payment);
        } else {
            return String.format("You gave ALL your gold (%.2f) but still owe %.2f! You are now a sslave to the Nagasss until paid!", 
                    payment, data.getLoanAmount());
        }
    }

    /**
     * Get customer's current balance
     */
    public String getBalance(String customerId) {
        CustomerData data = getOrCreateCustomerData(customerId);
        return String.format("Account balance: %.2f gold", data.getBalance());
    }

    /**
     * Get customer's loan information
     */
    public String getLoanInfo(String customerId) {
        CustomerData data = getOrCreateCustomerData(customerId);
        if (data.getLoanAmount() == 0) {
            return "No outstanding loans. The Nagasss are pleased!";
        }
        return String.format("Outstanding loan: %.2f gold. The Nagasss await payment...", data.getLoanAmount());
    }

    /**
     * Increase bank's total balance (used for cash deliveries by incassators)
     */
    public String increaseBankBalance(double amount) {
        if (amount <= 0) {
            return "Error: Amount must be positive!";
        }
        bankTotalBalance += amount;
        saveData();
        return String.format("Bank reserves increased by %.2f gold! New bank balance: %.2f gold", 
                amount, bankTotalBalance);
    }

    /**
     * Decrease bank's total balance
     */
    public String decreaseBankBalance(double amount) {
        if (amount <= 0) {
            return "Error: Amount must be positive!";
        }
        if (amount > bankTotalBalance) {
            return String.format("Cannot decrease! Insufficient bank reserves: %.2f gold", bankTotalBalance);
        }
        bankTotalBalance -= amount;
        saveData();
        return String.format("Bank reserves decreased by %.2f gold. New bank balance: %.2f gold", 
                amount, bankTotalBalance);
    }

    /**
     * Get bank's total balance (for CEOs and staff)
     */
    public String getBankBalance() {
        return String.format("Bank total reserves: %.2f gold. The Naga vault is well-guarded!", bankTotalBalance);
    }

    /**
     * Get list of all customers (for staff)
     */
    public String getCustomerList() {
        if (customerDataMap.isEmpty()) {
            return "No customers yet. The Nagasss await their first visitor!";
        }

        StringBuilder sb = new StringBuilder("Customer List:\n");
        sb.append("=".repeat(50)).append("\n");
        
        for (Map.Entry<String, CustomerData> entry : customerDataMap.entrySet()) {
            sb.append(String.format("Customer ID: %s | Balance: %.2f gold | Loan: %.2f gold\n", 
                    entry.getKey(), 
                    entry.getValue().getBalance(), 
                    entry.getValue().getLoanAmount()));
        }
        
        return sb.toString();
    }

    /**
     * Get or create customer data
     */
    private CustomerData getOrCreateCustomerData(String customerId) {
        return customerDataMap.computeIfAbsent(customerId, k -> new CustomerData());
    }

    /**
     * Get customer data map (for testing/debugging)
     */
    public Map<String, CustomerData> getCustomerDataMap() {
        return customerDataMap;
    }
}
