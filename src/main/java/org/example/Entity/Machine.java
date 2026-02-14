package org.example.Entity;

import org.example.Persistance.CashRepository;
import org.example.Service.AuthenticationService;
import org.example.State.IdleState;
import org.example.State.MachineState;

public class Machine {
    private String machineId;
    private String location;
    private MachineState machineState;
    private CashRepository cashRepository;
    private AuthenticationService authenticationService;

    public Machine(String machineId, String location, CashRepository cashRepository,
                   AuthenticationService authenticationService) {
        this.machineId = machineId;
        this.location = location;
        this.cashRepository = cashRepository;
        this.authenticationService = authenticationService;
        // Initialize to IdleState
        this.machineState = new IdleState(authenticationService, this);
    }

    public String getMachineId() {
        return machineId;
    }

    public String getLocation() {
        return location;
    }

    public MachineState getMachineState() {
        return machineState;
    }

    public void setNextMachineState(MachineState machineState) {
        this.machineState = machineState;
    }

    public CashRepository getCashRepositry() {
        return cashRepository;
    }

    // Delegate to state pattern
    public void insertCard() {
        machineState.insertCard();
    }

    public void enterPin(int pin) {
        machineState.enterPin(pin);
    }

    public void selectTransaction(Transaction transaction) {
        machineState.selectTransaction(transaction);
    }

    public void enterAmountAndExecuteTransaction(double amount) {
        machineState.enterAmountAndExecuteTransaction(amount);
    }

    public void ejectCard() {
        machineState.ejectCard();
    }
}
