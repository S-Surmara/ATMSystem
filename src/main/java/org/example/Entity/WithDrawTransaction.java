package org.example.Entity;

import org.example.Enums.TransactionStatus;
import org.example.Persistance.CashRepository;
import org.example.COR.CashDispenser;
import org.example.COR.HundredDispenser;
import org.example.COR.ThousandDispenser;
import org.example.COR.TwoThousandDispenser;

import java.time.LocalDate;

public class WithDrawTransaction extends Transaction {
    private CashDispenser cashDispenser;
    private CashRepository cashRepository;

    public WithDrawTransaction(Account account, LocalDate date, TransactionStatus status,
                               CashRepository cashRepository) {
        super(account, date, status);
        this.cashRepository = cashRepository;

        // Setup Chain of Responsibility
        cashDispenser = new TwoThousandDispenser(cashRepository);
        cashDispenser.setNext(new ThousandDispenser(cashRepository));
        cashDispenser.getNext().setNext(new HundredDispenser(cashRepository));
    }

    @Override
    public void execute(double amount) {
        try {
            setAmount(amount);

            // Validation
            if (amount <= 0) {
                System.out.println("Invalid amount");
                setStatus(TransactionStatus.FAILED);
                return;
            }

            if (amount % 100 != 0) {
                System.out.println("Amount must be multiple of 100");
                setStatus(TransactionStatus.FAILED);
                return;
            }

            // Check account balance
            if (getAccount().getBalance() < amount) {
                System.out.println("Insufficient balance");
                setStatus(TransactionStatus.FAILED);
                return;
            }

            // Dispense cash
            System.out.println("Processing withdrawal of " + amount);
            cashDispenser.dispense((int)amount);

            // Update account balance
            getAccount().setBalance(getAccount().getBalance() - amount);

            setStatus(TransactionStatus.SUCCESS);
            System.out.println("Withdrawal successful. New balance: " + getAccount().getBalance());

        } catch (Exception e) {
            setStatus(TransactionStatus.FAILED);
            System.out.println("Withdrawal failed: " + e.getMessage());
        }
    }
}
