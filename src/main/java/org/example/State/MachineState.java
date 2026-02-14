package org.example.State;

import org.example.Entity.Machine;
import org.example.Entity.Transaction;
import org.example.Service.AuthenticationService;

public abstract class MachineState {
    AuthenticationService authenticationService;
    Machine machine;
    Transaction transaction;
    public MachineState(AuthenticationService authenticationService, Machine machine){
        this.authenticationService = authenticationService;
        this.machine = machine;
    }
    public abstract void insertCard();
    public abstract void enterPin(int pin);
    public abstract void selectTransaction(Transaction transaction);
    public abstract void enterAmountAndExecuteTransaction(int amount);
    public abstract void ejectCard();
}
