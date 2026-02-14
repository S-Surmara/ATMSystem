package org.example.State;

import org.example.Entity.Card;
import org.example.Entity.Machine;
import org.example.Entity.Transaction;
import org.example.Service.AuthenticationService;

public class IdleState extends MachineState {
    private Card insertedCard;

    public IdleState(AuthenticationService authenticationService, Machine machine) {
        super(authenticationService, machine);
    }

    @Override
    public void insertCard() {
        System.out.println("Card inserted. Please enter PIN.");
        // In real implementation, card would be read from card reader
        // For now, we'll handle this in the main flow
    }

    public void insertCard(Card card) {
        if (card == null) {
            System.out.println("Invalid card");
            return;
        }
        this.insertedCard = card;
        System.out.println("Card inserted successfully: " + card.getCardNumber());
        System.out.println("Please enter PIN");
    }

    @Override
    public void enterPin(int pin) {
        if (insertedCard == null) {
            System.out.println("Please insert card first");
            return;
        }

        System.out.println("Validating PIN...");
        boolean authenticated = authenticationService.validate(insertedCard, pin);

        if (authenticated) {
            System.out.println("Authentication successful");
            machine.setNextMachineState(new InProgressState(authenticationService, machine, insertedCard));
        } else {
            System.out.println("Authentication failed");
            insertedCard = null; // Reset card
        }
    }

    @Override
    public void selectTransaction(Transaction transaction) {
        System.out.println("Invalid operation. Please insert card and authenticate first.");
    }

    @Override
    public void enterAmountAndExecuteTransaction(double amount) {
        System.out.println("Invalid operation. Please insert card and authenticate first.");
    }

    @Override
    public void ejectCard() {
        if (insertedCard != null) {
            System.out.println("Card ejected");
            insertedCard = null;
        } else {
            System.out.println("No card inserted");
        }
    }
}
