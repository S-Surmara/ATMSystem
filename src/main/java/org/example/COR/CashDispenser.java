package org.example.COR;

/**
 * Abstract base class for Chain of Responsibility pattern
 * Each concrete dispenser handles a specific denomination
 */
public abstract class CashDispenser {
    protected CashDispenser nextCashDispenser;

    public abstract void dispense(int amount);

    public void setNext(CashDispenser nextCashDispenser) {
        this.nextCashDispenser = nextCashDispenser;
    }

    public CashDispenser getNext() {
        return this.nextCashDispenser;
    }
}
