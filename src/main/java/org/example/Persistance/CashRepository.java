package org.example.Persistance;

import org.example.Enums.CashType;
import java.util.HashMap;
import java.util.Map;

public class CashRepository {
    private Map<CashType, Integer> cashTypeMap;

    public CashRepository() {
        cashTypeMap = new HashMap<>();
        initializeCash();
    }

    private void initializeCash() {
        // Initialize with some default cash
        cashTypeMap.put(CashType.TWO_THOUSAND, 100);
        cashTypeMap.put(CashType.THOUSAND, 200);
        cashTypeMap.put(CashType.HUNDRED, 500);
    }

    public int getCashCount(CashType cashType) {
        return cashTypeMap.getOrDefault(cashType, 0);
    }

    public void updateCashCount(CashType cashType, int count) {
        int currentCount = getCashCount(cashType);
        int newCount = currentCount + count;

        if (newCount < 0) {
            throw new IllegalStateException("Cannot have negative cash count for " + cashType);
        }

        cashTypeMap.put(cashType, newCount);
    }

    public void setCashCount(CashType cashType, int count) {
        if (count < 0) {
            throw new IllegalArgumentException("Cash count cannot be negative");
        }
        cashTypeMap.put(cashType, count);
    }

    public int getTotalCash() {
        int total = 0;
        for (Map.Entry<CashType, Integer> entry : cashTypeMap.entrySet()) {
            total += entry.getKey().getValue() * entry.getValue();
        }
        return total;
    }

    public void displayCashInventory() {
        System.out.println("\n=== Cash Inventory ===");
        for (CashType type : CashType.values()) {
            int count = getCashCount(type);
            System.out.println(type + ": " + count + " notes (₹" + (type.getValue() * count) + ")");
        }
        System.out.println("Total: ₹" + getTotalCash());
        System.out.println("===================\n");
    }
}
