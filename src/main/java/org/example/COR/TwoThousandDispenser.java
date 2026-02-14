package org.example.COR;

import org.example.Enums.CashType;
import org.example.Persistance.CashRepository;

public class TwoThousandDispenser extends CashDispenser {
    private CashRepository cashRepository;

    public TwoThousandDispenser(CashRepository cashRepository) {
        this.cashRepository = cashRepository;
    }

    @Override
    public void dispense(int amount) {
        int twoThousandNeededCount = amount / 2000;
        int twoThousandAvailable = cashRepository.getCashCount(CashType.TWO_THOUSAND);
        int dispensed = Math.min(twoThousandNeededCount, twoThousandAvailable);
        int remainingAmount = amount - (dispensed * 2000);

        if (dispensed > 0) {
            System.out.println("Dispensed " + (dispensed * 2000) + " in 2000 notes");
            cashRepository.updateCashCount(CashType.TWO_THOUSAND, -dispensed);
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
