package org.example;

import org.example.nagabank.service.BankService;

/**
 * Test the NAGA-BANK banking system
 * Run this to see the banking operations in action!
 */
public class TestNagaBank {
    public static void main(String[] args) {
        BankService bank = new BankService();
        
        System.out.println("=".repeat(70));
        System.out.println("🐍💰 Welcome to NAGA-BANK 💰🐍");
        System.out.println("Where the mighty Nagas guard your gold!");
        System.out.println("=".repeat(70));
        System.out.println();
        
        // Alice's banking operations
        System.out.println(">>> Alice arrives at the bank <<<");
        System.out.println(bank.deposit("alice", 5000));
        System.out.println(bank.getBalance("alice"));
        System.out.println(bank.takeLoan("alice", 2000));
        System.out.println(bank.getLoanInfo("alice"));
        System.out.println(bank.getBalance("alice"));
        System.out.println();
        
        // Bob's banking operations
        System.out.println(">>> Bob arrives at the bank <<<");
        System.out.println(bank.deposit("bob", 3000));
        System.out.println(bank.takeLoan("bob", 10000));
        System.out.println(bank.getLoanInfo("bob"));
        System.out.println();
        
        // Bob becomes a debt slave!
        System.out.println(">>> Bob decides to pay ALL his gold to the Nagas! <<<");
        System.out.println(bank.payLoansAsSlave("bob"));
        System.out.println(bank.getBalance("bob"));
        System.out.println(bank.getLoanInfo("bob"));
        System.out.println();
        
        // Charlie takes a loan and pays it back
        System.out.println(">>> Charlie arrives at the bank <<<");
        System.out.println(bank.deposit("charlie", 1000));
        System.out.println(bank.takeLoan("charlie", 500));
        System.out.println(bank.payLoan("charlie", 500));
        System.out.println(bank.getLoanInfo("charlie"));
        System.out.println();
        
        // Staff operations
        System.out.println(">>> Bank Staff checks the records <<<");
        System.out.println(bank.getCustomerList());
        System.out.println(bank.getBankBalance());
        System.out.println();
        
        // Incassator delivers cash
        System.out.println(">>> Incassator delivers cash to the vault <<<");
        System.out.println(bank.increaseBankBalance(50000));
        System.out.println();
        
        System.out.println("=".repeat(70));
        System.out.println("🐍 The Nagasss are pleased with today's business! 🐍");
        System.out.println("=".repeat(70));
    }
}
