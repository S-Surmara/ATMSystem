package org.example.COR;

import org.example.Enums.CashType;
import org.example.Persistance.CashRepository;

public class ThousandDispenser extends CashDispenser {
    private CashRepository cashRepository;

    public ThousandDispenser(CashRepository cashRepository) {
        this.cashRepository = cashRepository;
    }

    @Override
    public void dispense(int amount) {
        int thousandNeededCount = amount / 1000;
        int thousandAvailable = cashRepository.getCashCount(CashType.THOUSAND);
        int dispensed = Math.min(thousandNeededCount, thousandAvailable);
        int remainingAmount = amount - (dispensed * 1000);

        if (dispensed > 0) {
            System.out.println("Dispensed " + (dispensed * 1000) + " in 1000 notes");
            cashRepository.updateCashCount(CashType.THOUSAND, -dispensed);
        }

        if (remainingAmount > 0) {
            if (nextCashDispenser != null) {
                nextCashDispenser.dispense(remainingAmount);
            } else {
                System.out.println("Cannot dispense remaining amount: " + remainingAmount);
            }
        }
    }
}
