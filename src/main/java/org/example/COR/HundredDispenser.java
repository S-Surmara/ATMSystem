package org.example.COR;

import org.example.Enums.CashType;
import org.example.Persistance.CashRepository;

public class HundredDispenser extends CashDispenser {
    private CashRepository cashRepository;

    public HundredDispenser(CashRepository cashRepository) {
        this.cashRepository = cashRepository;
    }

    @Override
    public void dispense(int amount) {
        int hundredNeededCount = amount / 100;
        int hundredAvailable = cashRepository.getCashCount(CashType.HUNDRED);
        int dispensed = Math.min(hundredNeededCount, hundredAvailable);
        int remainingAmount = amount - (dispensed * 100);

        if (dispensed > 0) {
            System.out.println("Dispensed " + (dispensed * 100) + " in 100 notes");
            cashRepository.updateCashCount(CashType.HUNDRED, -dispensed);
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
