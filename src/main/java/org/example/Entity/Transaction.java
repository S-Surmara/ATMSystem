package org.example.Entity;

import org.example.Enums.TransactionStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public abstract class Transaction {
    private String transactionId;
    private Account account;
    private LocalDateTime timestamp;
    private TransactionStatus status;
    private double amount;

    public Transaction(Account account, LocalDate date, TransactionStatus status) {
        this.transactionId = UUID.randomUUID().toString();
        this.account = account;
        this.timestamp = LocalDateTime.now();
        this.status = status;
    }

    public abstract void execute(double amount);

    public String getTransactionId() {
        return transactionId;
    }

    public Account getAccount() {
        return account;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public double getAmount() {
        return amount;
    }

    protected void setAmount(double amount) {
        this.amount = amount;
    }
}
