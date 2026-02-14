package org.example.State;

import org.example.Entity.Machine;
import org.example.Entity.Transaction;
import org.example.Service.AuthenticationService;

public class CompletedState extends MachineState {

    public CompletedState(AuthenticationService authenticationService, Machine machine) {
        super(authenticationService, machine);
    }

    @Override
    public void insertCard() {
        System.out.println("Transaction completed. Please eject card first.");
    }

    @Override
    public void enterPin(int pin) {
        System.out.println("Transaction completed. Please eject card first.");
    }

    @Override
    public void selectTransaction(Transaction transaction) {
        System.out.println("Transaction completed. Please eject card first.");
    }

    @Override
    public void enterAmountAndExecuteTransaction(double amount) {
        System.out.println("Transaction completed. Please eject card first.");
    }

    @Override
    public void ejectCard() {
        if (transaction != null) {
            System.out.println("\n=== Transaction Receipt ===");
            System.out.println("Transaction ID: " + transaction.getTransactionId());
            System.out.println("Type: " + transaction.getClass().getSimpleName());
            System.out.println("Amount: ₹" + transaction.getAmount());
            System.out.println("Status: " + transaction.getStatus());
            System.out.println("Time: " + transaction.getTimestamp());
            System.out.println("Account: " + transaction.getAccount().getAccountId());
            System.out.println("New Balance: ₹" + transaction.getAccount().getBalance());
            System.out.println("========================\n");
        }

        System.out.println("Card ejected successfully. Thank you!");
        machine.setNextMachineState(new IdleState(authenticationService, machine));
    }
}
