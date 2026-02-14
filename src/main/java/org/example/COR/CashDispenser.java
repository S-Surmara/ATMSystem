package org.example.COR;

public abstract class CashDispenser {
    CashDispenser nextCashDispenser;
    abstract public void dispense(int amount);
    public void setNext(CashDispenser nextCashDispenser) {
        this.nextCashDispenser = nextCashDispenser;
    }
    public CashDispenser getNext() { return this.nextCashDispenser; }
}
