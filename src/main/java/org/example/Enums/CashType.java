package org.example.Enums;

public enum CashType {
    HUNDRED(100),
    FIVE_HUNDRED(500),
    THOUSAND(1000),
    TWO_THOUSAND(2000);

    private final int value;

    CashType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
