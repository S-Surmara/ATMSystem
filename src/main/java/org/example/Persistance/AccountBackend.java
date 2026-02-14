package org.example.Persistance;

import org.example.Entity.Account;

import java.util.List;

public abstract class AccountBackend {
    List<Account> accountList;

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    public abstract int getAccountBalance(String accountId);
    public abstract void addBalanceAccount(String accountId,int balance);

    public abstract void deleteAccount(String accountId);

    public abstract void createAccount(Account account);
}
