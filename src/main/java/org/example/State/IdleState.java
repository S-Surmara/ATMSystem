package org.example.State;

import org.example.Entity.Card;
import org.example.Entity.Machine;
import org.example.Entity.Transaction;
import org.example.Service.AuthenticationService;

import java.time.LocalDate;
import java.util.UUID;

public class IdleState extends MachineState{
    Card card;
    public IdleState(AuthenticationService authenticationService, Machine machine){
        super(authenticationService,machine);
    }
    public void insertCard() {
        System.out.println("Inserted card successfully");
        card = new Card(UUID.randomUUID().toString(), LocalDate.of(2050,12,29));
    }
    public void enterPin(int pin) {
        System.out.println("validating pin");
        boolean authenticated = authenticationService.validate(card,pin);
        if(authenticated){
            System.out.println("authenticated user successfully");
            machine.setNextMachineState(new InProgressState(authenticationService,machine));
        }
    }
    public void selectTransaction(Transaction transaction){
        System.out.println("Invalid state , first authenticate yourself");
    }
    public void enterAmountAndExecuteTransaction(int amount){
        System.out.println("Invalid state , first authenticate yourself");
    }
    public void ejectCard(){
        System.out.println("Invalid state , first authenticate yourself");
    }
}
