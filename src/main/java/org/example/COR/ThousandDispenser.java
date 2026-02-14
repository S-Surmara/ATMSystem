package org.example.COR;

import org.example.Enums.CashType;
import org.example.Persistance.CashRepositry;

public class ThousandDispenser extends CashDispenser{
    CashRepositry cashRepositry;

    public ThousandDispenser(CashRepositry cashRepositry){
        this.cashRepositry = cashRepositry;
    }
    @Override
    public void dispense(int amount) {
        int thousand_needed_count = amount / 1000;
        int thousand_cnt = cashRepositry.getCashCount(CashType.THOUSAND);
        if(thousand_cnt >= thousand_needed_count){
            nextCashDispenser.dispense(amount - thousand_needed_count*1000);
        } else {
            int amountSub = 2000*thousand_cnt;
            nextCashDispenser.dispense(amount - amountSub);
        }
    }
}
