package org.example.COR;

import org.example.Enums.CashType;
import org.example.Persistance.CashRepositry;

public class TwoThousandDispenser extends CashDispenser{

    CashRepositry cashRepositry;

    public TwoThousandDispenser(CashRepositry cashRepositry){
        this.cashRepositry = cashRepositry;
    }
    @Override
    public void dispense(int amount) {
        int two_thousand_needed_count = amount / 2000;
        int two_thousand_cnt = cashRepositry.getCashCount(CashType.TWO_THOUSAND);
        if(two_thousand_cnt >= two_thousand_needed_count){
            System.out.println("Dispensed total cash");
            // in business we will call abstraction layer to dispense the cash
        } else {
            System.out.println("Amount can't be processed , insufficient amount in ATM");
            // in business we will refund the amount with error message
        }
    }
}
