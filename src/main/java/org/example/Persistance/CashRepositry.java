package org.example.Persistance;

import org.example.Enums.CashType;

import java.util.HashMap;
import java.util.Map;

public class CashRepositry {
    Map<CashType,Integer> cashTypeMap;
    public CashRepositry(){
        cashTypeMap = new HashMap<CashType,Integer>();
    }

    public int getCashCount(CashType cashType){
        return cashTypeMap.get(cashType);
    }
}
