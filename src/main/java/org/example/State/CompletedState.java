package org.example.State;

import org.example.Entity.Machine;
import org.example.Entity.Transaction;
import org.example.Service.AuthenticationService;

public class CompletedState extends MachineState{
    public CompletedState(AuthenticationService authenticationService, Machine machine) {
        super(authenticationService,machine);
    }

    public void insertCard() {
        System.out.println("Transaction Completed");
    }
    public void enterPin(int pin) {
        System.out.println("Transaction Completed");
    }
    public void selectTransaction(Transaction transaction){
        System.out.println("Transaction Completed");
    }
    public void enterAmountAndExecuteTransaction(int amount){
        System.out.println("Transaction Completed");
    }
    public void ejectCard(){
        System.out.println("Ejected card successfully");
        machine.setNextMachineState(new IdleState(authenticationService,machine));
    }
}
