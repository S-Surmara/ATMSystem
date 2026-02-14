package org.example.Service;

import org.example.Entity.Card;
import org.example.Persistance.AccountBackend;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class AuthenticationService {
    private Map<String, Card> cardDatabase; // cardNumber -> Card
    private AccountBackend accountBackend;
    private Map<String, Integer> failedAttempts; // cardNumber -> attempt count
    private static final int MAX_ATTEMPTS = 3;

    public AuthenticationService(AccountBackend accountBackend) {
        this.accountBackend = accountBackend;
        this.cardDatabase = new HashMap<>();
        this.failedAttempts = new HashMap<>();
    }

    public void registerCard(Card card) {
        cardDatabase.put(card.getCardNumber(), card);
    }

    public boolean validate(Card card, int pin) {
        String cardNumber = card.getCardNumber();

        // Check if card is blocked
        if (isCardBlocked(cardNumber)) {
            System.out.println("Card is blocked due to multiple failed attempts");
            return false;
        }

        // Check if card exists
        Card storedCard = cardDatabase.get(cardNumber);
        if (storedCard == null) {
            System.out.println("Invalid card");
            recordFailedAttempt(cardNumber);
            return false;
        }

        // Check if card is expired
        if (storedCard.isExpired()) {
            System.out.println("Card is expired");
            return false;
        }

        // Validate PIN
        if (!storedCard.validatePin(pin)) {
            System.out.println("Invalid PIN");
            recordFailedAttempt(cardNumber);
            return false;
        }

        // Check if linked account exists
        if (!accountBackend.accountExists(storedCard.getAccountId())) {
            System.out.println("Account not found");
            return false;
        }

        // Reset failed attempts on successful authentication
        failedAttempts.remove(cardNumber);
        return true;
    }

    private void recordFailedAttempt(String cardNumber) {
        int attempts = failedAttempts.getOrDefault(cardNumber, 0) + 1;
        failedAttempts.put(cardNumber, attempts);

        if (attempts >= MAX_ATTEMPTS) {
            System.out.println("Card blocked after " + MAX_ATTEMPTS + " failed attempts");
        } else {
            System.out.println("Failed attempts: " + attempts + "/" + MAX_ATTEMPTS);
        }
    }

    private boolean isCardBlocked(String cardNumber) {
        return failedAttempts.getOrDefault(cardNumber, 0) >= MAX_ATTEMPTS;
    }

    public void unblockCard(String cardNumber) {
        failedAttempts.remove(cardNumber);
        System.out.println("Card unblocked: " + cardNumber);
    }
}
