package org.example.Persistance;

import org.example.Entity.Account;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountBackend {
    private Map<String, Account> accountMap;

    public AccountBackend() {
        accountMap = new HashMap<>();
    }

    public List<Account> getAccountList() {
        return new ArrayList<>(accountMap.values());
    }

    public Account getAccount(String accountId) {
        return accountMap.get(accountId);
    }

    public double getAccountBalance(String accountId) {
        Account account = accountMap.get(accountId);
        if (account == null) {
            throw new IllegalArgumentException("Account not found: " + accountId);
        }
        return account.getBalance();
    }

    public void updateBalance(String accountId, double amount) {
        Account account = accountMap.get(accountId);
        if (account == null) {
            throw new IllegalArgumentException("Account not found: " + accountId);
        }
        account.setBalance(account.getBalance() + amount);
    }

    public void deleteAccount(String accountId) {
        if (!accountMap.containsKey(accountId)) {
            throw new IllegalArgumentException("Account not found: " + accountId);
        }
        accountMap.remove(accountId);
    }

    public void createAccount(Account account) {
        if (accountMap.containsKey(account.getAccountId())) {
            throw new IllegalArgumentException("Account already exists: " + account.getAccountId());
        }
        accountMap.put(account.getAccountId(), account);
    }

    public boolean accountExists(String accountId) {
        return accountMap.containsKey(accountId);
    }
}
