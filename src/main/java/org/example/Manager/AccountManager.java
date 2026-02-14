package org.example.Manager;

import org.example.Entity.Account;
import org.example.Persistance.AccountBackend;

public class AccountManager {
    AccountBackend acccountBackend;

    public AccountManager(AccountBackend acccountBackend){
        this.acccountBackend = acccountBackend;
    }

    public void createAccount(Account account){
        acccountBackend.createAccount(account);
    }

    public void addBalance(String accountId,int balance) {
        acccountBackend.addBalanceAccount(accountId,balance);
    }

    public void subBalance(String accountId,int balance) {
        acccountBackend.addBalanceAccount(accountId,-balance);
    }

    public void deleteAccount(String accountId) {
        acccountBackend.deleteAccount(accountId);
    }
}
