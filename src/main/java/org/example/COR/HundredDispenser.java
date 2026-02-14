package org.example.COR;

import org.example.Enums.CashType;
import org.example.Persistance.CashRepositry;

public class HundredDispenser extends CashDispenser{
    CashRepositry cashRepositry;

    public HundredDispenser(CashRepositry cashRepositry){
        this.cashRepositry = cashRepositry;
    }
    @Override
    public void dispense(int amount) {
        int hundred_needed_count = amount / 100;
        int hundred_cnt = cashRepositry.getCashCount(CashType.HUNDRED);

        int dispensed = Math.min(hundred_needed_count, hundred_cnt);
        int remainingAmount = amount - dispensed * 100;

        if (remainingAmount > 0) {
            if (nextCashDispenser != null) {
                nextCashDispenser.dispense(remainingAmount);
            } else {
                System.out.println("Cannot dispense remaining amount: " + remainingAmount);
            }
        } else {
            System.out.println("Dispensed " + dispensed * 100 + " in hundreds");
        }
    }
}
