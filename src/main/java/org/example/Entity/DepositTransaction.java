package org.example.Entity;

import org.example.Enums.CashType;
import org.example.Enums.TransactionStatus;
import org.example.Persistance.CashRepository;
import java.time.LocalDate;

public class DepositTransaction extends Transaction {
    private CashRepository cashRepository;

    public DepositTransaction(Account account, LocalDate date, TransactionStatus status,
                              CashRepository cashRepositry) {
        super(account, date, status);
        this.cashRepository = cashRepositry;
    }

    @Override
    public void execute(double amount) {
        try {
            setAmount(amount);

            // Validation
            if (amount <= 0) {
                System.out.println("Invalid deposit amount");
                setStatus(TransactionStatus.FAILED);
                return;
            }

            if (amount % 100 != 0) {
                System.out.println("Deposit amount must be multiple of 100");
                setStatus(TransactionStatus.FAILED);
                return;
            }

            System.out.println("Processing deposit of " + amount);

            // Update account balance
            getAccount().setBalance(getAccount().getBalance() + amount);

            // Update ATM cash inventory (assuming notes are provided)
            // This is simplified - in real system, user would specify note denominations
            updateCashInventory((int)amount);

            setStatus(TransactionStatus.SUCCESS);
            System.out.println("Deposit successful. New balance: " + getAccount().getBalance());

        } catch (Exception e) {
            setStatus(TransactionStatus.FAILED);
            System.out.println("Deposit failed: " + e.getMessage());
        }
    }

    private void updateCashInventory(int amount) {
        // Simplified: Add to cash repository
        // In real scenario, ATM would count deposited notes
        int twoThousands = amount / 2000;
        amount %= 2000;
        int thousands = amount / 1000;
        amount %= 1000;
        int hundreds = amount / 100;

        if (twoThousands > 0) {
            cashRepository.updateCashCount(CashType.TWO_THOUSAND, twoThousands);
        }
        if (thousands > 0) {
            cashRepository.updateCashCount(CashType.THOUSAND, thousands);
        }
        if (hundreds > 0) {
            cashRepository.updateCashCount(CashType.HUNDRED, hundreds);
        }
    }
}
