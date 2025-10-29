import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class CustodialAccount {
    // Declare instance variables for account number, balance, owner name, beneficiary name, and age
    private String accountNumber;
    private double balance;
    private int age;
    private static final String logFileName = "custodial_transactionlog.csv";

    // Constructor for CustodialAccount class that takes account number, initial balance, and age
    public CustodialAccount(String accountNumber, double initialBalance, int age) {
        // Initialize the account number, balance, and age instance variables
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.age = age;
    }

    // Getter and setter methods for owner name and beneficiary name
    public void setOwnerName(String ownerName) {
    }

    public void setBeneficiaryName(String beneficiaryName) {
    }

    // Method for depositing a given amount into the custodial account
    public void deposit(double amount) {
        // Check if the given amount is positive and if the user's age is greater than or equal to 18
        if (amount > 0 && age >= 18) {
            // Add the given amount to the balance instance variable
            balance += amount;
            // Print a message indicating that the deposit was successful and the new balance
            System.out.println("Deposit successful. New balance: " + balance);

            // Log the transaction
            logTransaction(accountNumber, "deposit", amount, 0);
        } else {
            // Print an error message if the given amount is not positive or if the user's age is less than 18
            System.out.println("Invalid deposit amount or user is underage.");
        }
    }

    // Method for withdrawing a given amount from the custodial account
    public void withdraw(double amount) {
        // Check if the given amount is positive and if there are sufficient funds in the account
        if (amount > 0 && balance >= amount) {
            // Subtract the given amount from the balance instance variable
            balance -= amount;
            // Print a message indicating that the withdrawal was successful and the new balance
            System.out.println("Withdrawal successful. New balance: " + balance);

            // Log the transaction
            logTransaction(accountNumber, "withdrawal", amount, 0);
        } else {
            // Print an error message if the given amount is not positive or if there are insufficient funds
            System.out.println("Invalid withdrawal amount or insufficient funds.");
        }
    }

    // Utility method to log transactions
    private static void logTransaction(String accountNumber, String transactionType, double amount, double fee) {
        File file = new File(logFileName);
        FileWriter fWriter = null;

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            fWriter = new FileWriter(file, true);

            Date timeStamp = new Date();
            String logMessage = timeStamp + "," + accountNumber + "," + transactionType + "," + amount + "," + fee;

            fWriter.write(logMessage + "\n");
            fWriter.close();
        } catch (IOException e) {
            System.out.println("ALERT: An error occurred when trying to log the transaction: " + e.getMessage());
        }
    }

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        // Create a new Scanner object to read input from the user
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter their age
        System.out.print("Enter your age: ");
int age = scanner.nextInt();

        // Check if the user's age is less than 18
        if (age < 18) {
            // Print an error message and exit the program if the user's age is less than 18
            System.out.println("You must be at least 18 years old to open a custodial account.");
            return;
        }

        // Create a new CustodialAccount object with the account number "12345", initial balance of 0, and the user's age
        CustodialAccount account = new CustodialAccount("12345", 0, age);

        // Prompt the user to enter the owner's name
        System.out.print("Enter the owner's name: ");
        String ownerName = scanner.next();

        // Set the owner name for the CustodialAccount object
        account.setOwnerName(ownerName);

        // Prompt the user to enter the beneficiary's name
        System.out.print("Enter the beneficiary's name: ");
        String beneficiaryName = scanner.next();

        // Set the beneficiary name for the CustodialAccount object
        account.setBeneficiaryName(beneficiaryName);

        // Prompt the user to enter a deposit amount
        System.out.print("Enter a deposit amount: ");
        double depositAmount = scanner.nextDouble();

        // Deposit the specified amount into the account
        account.deposit(depositAmount);

        // Close the scanner
        scanner.close();
    }
}