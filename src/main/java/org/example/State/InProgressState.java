package org.example.State;

import org.example.Entity.Machine;
import org.example.Entity.Transaction;
import org.example.Service.AuthenticationService;

public class InProgressState extends MachineState{
    public InProgressState(AuthenticationService authenticationService, Machine machine) {
        super(authenticationService,machine);
    }

    public void insertCard() {
        System.out.println("already card inserted");
    }
    public void enterPin(int pin) {
        System.out.println("already entered pin");
    }
    public void selectTransaction(Transaction transaction){
        this.transaction = transaction;
    }
    public void enterAmountAndExecuteTransaction(int amount){
        transaction.execute(amount);
        machine.setNextMachineState(new CompletedState(authenticationService,machine));
    }
    public void ejectCard(){
        System.out.println("transaction ongoing , can't eject card now");
    }
}
