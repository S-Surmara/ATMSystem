package org.example.Entity;

import org.example.Enums.TransactionStatus;
import org.example.Persistance.CashRepositry;
import org.example.COR.CashDispenser;
import org.example.COR.HundredDispenser;
import org.example.COR.ThousandDispenser;
import org.example.COR.TwoThousandDispenser;

import java.time.LocalDate;

public class WithDrawTransaction extends Transaction{
    CashDispenser cashDispenser;
    CashRepositry cashRepositry;
    public WithDrawTransaction(Account account, LocalDate date, TransactionStatus status){
        super(account,date,status);
        cashDispenser = new TwoThousandDispenser(cashRepositry);
        cashDispenser.setNext(new ThousandDispenser(cashRepositry));
        cashDispenser.getNext().setNext(new HundredDispenser(cashRepositry));
    }
    @Override
    public void execute(int amount) {
        cashDispenser.dispense(amount);
    }
}
