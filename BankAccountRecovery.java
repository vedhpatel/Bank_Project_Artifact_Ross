import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Base64;

public class BankAccountRecovery {

    private static final String ACCOUNTS_FILE = "accounts.csv";
    private static final String PASSWORDS_FILE = "passwords.csv";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the bank account recovery!");
        System.out.println("1. Recover Account ID");
        System.out.println("2. Change Password");
        int choice = Integer.parseInt(getInput("Enter your choice: "));
        
        switch (choice) {
            case 1:
                String ssn = getInput("Enter your SSN: ");
                String accountId = recoverAccountIdBySSN(ssn);
                if (accountId != null) {
                    System.out.println("Your account ID: " + accountId);
                } else {
                    System.out.println("No account found with the provided SSN.");
                }
                break;
            case 2:
                String accountIdForChange = getInput("Enter your Account ID: ");
                String emailForChange = getInput("Enter your email address: ");
                String ssnForChange = getInput("Enter your SSN: ");
                changePassword(accountIdForChange, emailForChange, ssnForChange);
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    public static String recoverAccountIdBySSN(String ssn) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ACCOUNTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4 && parts[3].equals(ssn)) {
                    return parts[0]; // Account ID is at index 0
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void changePassword(String accountId, String email, String ssn) {
        // Retrieve encrypted password from passwords.csv
        String encryptedPassword = getPasswordFromAccountId(accountId);
        if (encryptedPassword == null) {
            System.out.println("Account ID not found.");
            return;
        }

        // Decrypt password
        String currentPassword = decryptPassword(encryptedPassword);

        // Validate the new password
        String newPassword;
        do {
            newPassword = getPassword("Enter your new password: ");
            if (newPassword.length() < 8 || !newPassword.matches(".*[!@#$%^&*()\\[\\]{};:',.?/|`~\"\\\\].*")) {
                System.out.println("Password must be at least 8 characters long and contain a special character.");
            }
        } while (newPassword.length() < 8 || !newPassword.matches(".*[!@#$%^&*()\\[\\]{};:',.?/|`~\"\\\\].*"));

        // Check if the new password is different from the current one
        if (newPassword.equals(currentPassword)) {
            System.out.println("New password must be different from the current password.");
            return;
        }

        // Update password in passwords.csv
        updatePasswordInFile(accountId, newPassword);
        System.out.println("Password changed successfully.");
    }

    private static String getPasswordFromAccountId(String accountId) {
        try (BufferedReader reader = new BufferedReader(new FileReader(PASSWORDS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2 && parts[0].equals(accountId)) {
                    return parts[1]; // Encrypted password is at index 1
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void updatePasswordInFile(String accountId, String newPassword) {
        try (BufferedReader reader = new BufferedReader(new FileReader(PASSWORDS_FILE));
             BufferedWriter writer = new BufferedWriter(new FileWriter(PASSWORDS_FILE + ".temp"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2 && parts[0].equals(accountId)) {
                    line = accountId + "," + encryptPassword(newPassword);
                }
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Replace original file with updated file
        try {
            java.nio.file.Files.move(java.nio.file.Paths.get(PASSWORDS_FILE + ".temp"), java.nio.file.Paths.get(PASSWORDS_FILE), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getPassword(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static String decryptPassword(String encryptedPassword) {
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedPassword);
        return new String(decodedBytes);
    }

    private static String encryptPassword(String password) {
        return Base64.getEncoder().encodeToString(password.getBytes());
    }
}