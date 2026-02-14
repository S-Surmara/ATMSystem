package org.example.Entity;

import java.time.LocalDate;

public class Card {
    private String cardNumber;
    private LocalDate expiry;
    private int cvv;
    private int pin; // PIN for ATM authentication
    private String accountId; // Link card to account

    public Card(String cardNumber, LocalDate expiry, int cvv, int pin, String accountId) {
        if (cardNumber == null || cardNumber.length() > 4) {
            throw new IllegalArgumentException("Invalid card number");
        }
        if (expiry.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Card is expired");
        }
        if (cvv < 100 || cvv > 999) {
            throw new IllegalArgumentException("Invalid CVV");
        }
        if (pin < 1000 || pin > 9999) {
            throw new IllegalArgumentException("PIN must be 4 digits");
        }

        this.cardNumber = cardNumber;
        this.expiry = expiry;
        this.cvv = cvv;
        this.pin = pin;
        this.accountId = accountId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getExpiry() {
        return expiry;
    }

    public void setExpiry(LocalDate expiry) {
        this.expiry = expiry;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public boolean isExpired() {
        return expiry.isBefore(LocalDate.now());
    }

    public boolean validatePin(int inputPin) {
        return this.pin == inputPin;
    }
}
