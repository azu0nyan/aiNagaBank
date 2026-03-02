package org.example.nagabank.tools;

import dev.langchain4j.agent.tool.Tool;
import org.example.nagabank.service.BankService;

/**
 * Banking tools for LangChain4j AI Agent
 * These methods are automatically exposed as tools to the AI
 */
public class BankingTools {
    
    private final BankService bankService;
    
    public BankingTools(BankService bankService) {
        this.bankService = bankService;
    }
    
    @Tool("Withdraw money from a customer's account")
    public String withdraw(String customerId, double amount) {
        System.out.println("[TOOL CALL] withdraw(customerId=" + customerId + ", amount=" + amount + ")");
        String result = bankService.withdraw(customerId, amount);
        System.out.println("[TOOL RETURN] " + result);
        return result;
    }
    
    @Tool("Deposit money into a customer's account")
    public String deposit(String customerId, double amount) {
        System.out.println("[TOOL CALL] deposit(customerId=" + customerId + ", amount=" + amount + ")");
        String result = bankService.deposit(customerId, amount);
        System.out.println("[TOOL RETURN] " + result);
        return result;
    }
    
    @Tool("Take a loan from the NAGA-BANK (decreases bank reserves)")
    public String takeLoan(String customerId, double amount) {
        System.out.println("[TOOL CALL] takeLoan(customerId=" + customerId + ", amount=" + amount + ")");
        String result = bankService.takeLoan(customerId, amount);
        System.out.println("[TOOL RETURN] " + result);
        return result;
    }
    
    @Tool("Pay back a loan to the NAGA-BANK")
    public String payLoan(String customerId, double amount) {
        System.out.println("[TOOL CALL] payLoan(customerId=" + customerId + ", amount=" + amount + ")");
        String result = bankService.payLoan(customerId, amount);
        System.out.println("[TOOL RETURN] " + result);
        return result;
    }
    
    @Tool("Pay ALL loans with ALL available balance - become a debt slave to the Nagas! (HOMM3 reference)")
    public String payLoansAsSlave(String customerId) {
        System.out.println("[TOOL CALL] payLoansAsSlave(customerId=" + customerId + ")");
        String result = bankService.payLoansAsSlave(customerId);
        System.out.println("[TOOL RETURN] " + result);
        return result;
    }
    
    @Tool("Get customer's current account balance")
    public String getBalance(String customerId) {
        System.out.println("[TOOL CALL] getBalance(customerId=" + customerId + ")");
        String result = bankService.getBalance(customerId);
        System.out.println("[TOOL RETURN] " + result);
        return result;
    }
    
    @Tool("Get customer's loan information")
    public String getLoanInfo(String customerId) {
        System.out.println("[TOOL CALL] getLoanInfo(customerId=" + customerId + ")");
        String result = bankService.getLoanInfo(customerId);
        System.out.println("[TOOL RETURN] " + result);
        return result;
    }
    
    @Tool("Increase bank's total reserves (for incassators delivering cash)")
    public String increaseBankBalance(double amount) {
        System.out.println("[TOOL CALL] increaseBankBalance(amount=" + amount + ")");
        String result = bankService.increaseBankBalance(amount);
        System.out.println("[TOOL RETURN] " + result);
        return result;
    }
    
    @Tool("Decrease bank's total reserves (for withdrawals from bank vault)")
    public String decreaseBankBalance(double amount) {
        System.out.println("[TOOL CALL] decreaseBankBalance(amount=" + amount + ")");
        String result = bankService.decreaseBankBalance(amount);
        System.out.println("[TOOL RETURN] " + result);
        return result;
    }
    
    @Tool("Get bank's total reserves (for CEOs and staff)")
    public String getBankBalance() {
        System.out.println("[TOOL CALL] getBankBalance()");
        String result = bankService.getBankBalance();
        System.out.println("[TOOL RETURN] " + result);
        return result;
    }
    
    @Tool("Get list of all customers with their balances and loans (for staff)")
    public String getCustomerList() {
        System.out.println("[TOOL CALL] getCustomerList()");
        String result = bankService.getCustomerList();
        System.out.println("[TOOL RETURN] " + result);
        return result;
    }
}
