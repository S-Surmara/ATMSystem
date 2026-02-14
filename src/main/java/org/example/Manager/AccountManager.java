package org.example.Manager;

import org.example.Entity.Account;
import org.example.Persistance.AccountBackend;

public class AccountManager {
    private AccountBackend accountBackend;

    public AccountManager(AccountBackend accountBackend) {
        if (accountBackend == null) {
            throw new IllegalArgumentException("AccountBackend cannot be null");
        }
        this.accountBackend = accountBackend;
    }

    public void createAccount(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null");
        }
        accountBackend.createAccount(account);
        System.out.println("Account created successfully: " + account.getAccountId());
    }

    public void addBalance(String accountId, double balance) {
        if (balance <= 0) {
            throw new IllegalArgumentException("Balance to add must be positive");
        }
        accountBackend.updateBalance(accountId, balance);
        System.out.println("Added ₹" + balance + " to account " + accountId);
    }

    public void subtractBalance(String accountId, double balance) {
        if (balance <= 0) {
            throw new IllegalArgumentException("Balance to subtract must be positive");
        }

        double currentBalance = accountBackend.getAccountBalance(accountId);
        if (currentBalance < balance) {
            throw new IllegalStateException("Insufficient balance");
        }

        accountBackend.updateBalance(accountId, -balance);
        System.out.println("Deducted ₹" + balance + " from account " + accountId);
    }

    public double getBalance(String accountId) {
        return accountBackend.getAccountBalance(accountId);
    }

    public void deleteAccount(String accountId) {
        accountBackend.deleteAccount(accountId);
        System.out.println("Account deleted: " + accountId);
    }

    public Account getAccount(String accountId) {
        return accountBackend.getAccount(accountId);
    }
}
