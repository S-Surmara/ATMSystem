package org.example;

import org.example.Entity.*;
import org.example.Enums.CashType;
import org.example.Enums.TransactionStatus;
import org.example.Manager.AccountManager;
import org.example.Persistance.AccountBackend;
import org.example.Persistance.CashRepository;
import org.example.Service.AuthenticationService;

import java.time.LocalDate;
import java.util.Scanner;

public class ATMApplication {
    private static Machine atm;
    private static AccountManager accountManager;
    private static CashRepository cashRepository;
    private static AuthenticationService authService;
    private static Card currentCard;

    public static void main(String[] args) {
        // Initialize system
        initializeSystem();

        // Run ATM
        runATM();
    }

    private static void initializeSystem() {
        System.out.println("Initializing ATM System...\n");

        // Setup persistence layer
        AccountBackend accountBackend = new AccountBackend();
        accountManager = new AccountManager(accountBackend);
        cashRepository = new CashRepository();

        // Setup authentication
        authService = new AuthenticationService(accountBackend);

        // Create sample accounts
        Account acc1 = new Account("ACC001", "John Doe", 50000);
        Account acc2 = new Account("ACC002", "Jane Smith", 75000);
        accountManager.createAccount(acc1);
        accountManager.createAccount(acc2);

        // Create and register sample cards
        Card card1 = new Card("1234567890123456", LocalDate.of(2028, 12, 31), 123, 1234, "ACC001");
        Card card2 = new Card("6543210987654321", LocalDate.of(2027, 6, 30), 456, 5678, "ACC002");
        authService.registerCard(card1);
        authService.registerCard(card2);

        // Initialize ATM machine
        atm = new Machine("ATM001", "Hyderabad - Banjara Hills", cashRepository, authService);

        System.out.println("System initialized successfully!");
        System.out.println("\n=== Sample Test Cards ===");
        System.out.println("Card 1: 1234567890123456, PIN: 1234 (Balance: ₹50,000)");
        System.out.println("Card 2: 6543210987654321, PIN: 5678 (Balance: ₹75,000)");
        System.out.println("=======================\n");

        cashRepository.displayCashInventory();
    }

    private static void runATM() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== Welcome to ATM ===");
            System.out.println("1. Insert Card");
            System.out.println("2. Enter PIN");
            System.out.println("3. Select Transaction");
            System.out.println("4. Eject Card");
            System.out.println("5. Check Cash Inventory (Admin)");
            System.out.println("6. Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    handleInsertCard(scanner);
                    break;
                case 2:
                    handleEnterPin(scanner);
                    break;
                case 3:
                    handleTransaction(scanner);
                    break;
                case 4:
                    atm.ejectCard();
                    currentCard = null;
                    break;
                case 5:
                    cashRepository.displayCashInventory();
                    break;
                case 6:
                    System.out.println("Thank you for using our ATM!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }

        scanner.close();
    }

    private static void handleInsertCard(Scanner scanner) {
        System.out.print("Enter card number: ");
        String cardNumber = scanner.next();

        // For demo, create a temporary card object
        // In real system, card data would be read from magnetic stripe
        currentCard = new Card(cardNumber, LocalDate.of(2028, 12, 31), 123, 0, "");
        atm.insertCard();
    }

    private static void handleEnterPin(Scanner scanner) {
        if (currentCard == null) {
            System.out.println("Please insert card first");
            return;
        }

        System.out.print("Enter PIN: ");
        int pin = scanner.nextInt();
        atm.enterPin(pin);
    }

    private static void handleTransaction(Scanner scanner) {
        System.out.println("\n=== Select Transaction Type ===");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.print("Choose: ");

        int type = scanner.nextInt();

        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();

        // Get account from authenticated card
        String accountId = currentCard.getAccountId();
        Account account = accountManager.getAccount(accountId);

        Transaction transaction;
        if (type == 1) {
            transaction = new WithDrawTransaction(account, LocalDate.now(),
                    TransactionStatus.PENDING, cashRepository);
        } else {
            transaction = new DepositTransaction(account, LocalDate.now(),
                    TransactionStatus.PENDING, cashRepository);
        }

        atm.selectTransaction(transaction);
        atm.enterAmountAndExecuteTransaction(amount);
    }
}

