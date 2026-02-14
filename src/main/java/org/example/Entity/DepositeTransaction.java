package org.example.Entity;

import org.example.Enums.TransactionStatus;

import java.time.LocalDate;

public class DepositeTransaction extends Transaction{
    public DepositeTransaction(Account account, LocalDate date, TransactionStatus status){
        super(account,date,status);
    }
    @Override
    public void execute(int amount) {

    }
}
