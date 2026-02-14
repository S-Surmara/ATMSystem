package org.example.Entity;

import org.example.Enums.TransactionStatus;

import java.time.LocalDate;
import java.util.UUID;

public abstract class Transaction {
    String transactionId;
    Account account;

    LocalDate date;
    TransactionStatus status;

    public Transaction(Account account, LocalDate date, TransactionStatus status){
        this.transactionId = UUID.randomUUID().toString();
        this.account = account;
        this.date = date;
        this.status = status;
    }

    public abstract void execute(int amount);
}
