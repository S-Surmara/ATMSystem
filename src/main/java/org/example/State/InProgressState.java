package org.example.State;

import org.example.Entity.Card;
import org.example.Entity.Machine;
import org.example.Entity.Transaction;
import org.example.Service.AuthenticationService;

public class InProgressState extends MachineState {
    private Card authenticatedCard;

    public InProgressState(AuthenticationService authenticationService, Machine machine, Card card) {
        super(authenticationService, machine);
        this.authenticatedCard = card;
    }

    @Override
    public void insertCard() {
        System.out.println("Card already inserted. Please complete or cancel current transaction.");
    }

    @Override
    public void enterPin(int pin) {
        System.out.println("Already authenticated. Please select transaction type.");
    }

    @Override
    public void selectTransaction(Transaction transaction) {
        if (transaction == null) {
            System.out.println("Invalid transaction");
            return;
        }
        this.transaction = transaction;
        System.out.println("Transaction selected: " + transaction.getClass().getSimpleName());
        System.out.println("Please enter amount");
    }

    @Override
    public void enterAmountAndExecuteTransaction(double amount) {
        if (transaction == null) {
            System.out.println("Please select transaction type first");
            return;
        }

        System.out.println("Executing transaction...");
        transaction.execute(amount);

        machine.setNextMachineState(new CompletedState(authenticationService, machine));
    }

    @Override
    public void ejectCard() {
        System.out.println("Cannot eject card. Transaction in progress. Please complete or cancel.");
    }

    public Card getAuthenticatedCard() {
        return authenticatedCard;
    }
}
