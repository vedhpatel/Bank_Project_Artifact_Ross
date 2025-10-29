import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Base64;
import java.util.Scanner;

public class BankAccountLogin {

    private static final String PASSWORDS_FILE = "passwords.csv";
    private static final String ACCOUNTS_FILE = "accounts.csv";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException, ParseException {
        System.out.println("Welcome to the bank account login!");
    
        boolean loggedIn = false;
        while (!loggedIn) {
            // Prompt the user to enter their account ID and password
            String accountId = getInput("Enter your account ID (ACCTXXXXXXXXXXXXX): ");
            String password = getPassword("Enter your password: ");
    
            // Check if the entered credentials are valid
            String firstName = isValidCredentials(accountId, password);
            if (firstName != null) {
                System.out.println("Welcome, " + firstName + "!");
                loggedIn = true; // Set loggedIn to true to exit the loop
                    // Call the main menu of the bank program
                    MainMenu.showMainMenu(accountId); // Pass the account ID obtained from logging in
                } else {
                System.out.println("Invalid account ID or password. Please try again.");
                System.out.println("Would you like to try again? (yes/no)");
                String tryAgain = scanner.nextLine().trim().toLowerCase();
                if (!tryAgain.equals("yes") && !tryAgain.equals("y")) {
                    System.out.println("Exiting bank account login. Goodbye!");
                    return; // Exit the program if user chooses not to try again
                }
            }
        }
    }

    private static String isValidCredentials(String accountId, String enteredPassword) {
        try (BufferedReader reader = new BufferedReader(new FileReader(PASSWORDS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2 && parts[0].equals(accountId)) {
                    String encryptedPassword = parts[1];
                    String decryptedPassword = decryptPassword(encryptedPassword);
                    if (enteredPassword.equals(decryptedPassword)) {
                        // If password matches, retrieve the first name from accounts.csv
                        return getFirstNameFromAccount(accountId);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getFirstNameFromAccount(String accountId) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ACCOUNTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2 && parts[0].equals(accountId)) {
                    return parts[1]; // First name is at index 1
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String decryptPassword(String encryptedPassword) {
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedPassword);
        return new String(decodedBytes);
    }

    private static String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static String getPassword(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}