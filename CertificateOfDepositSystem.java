import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class CertificateOfDepositSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Welcome user
        System.out.println("Welcome to the Certificate of Deposit system!");

        // Ask for account number
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine().trim();

        // Check if the account exists
        boolean accountExists = checkAccountExists(accountNumber);
        if (accountExists) {
            System.out.println("Account found.");
            // Proceed with Certificate of Deposit creation...
            createCertificateOfDeposit(scanner);
        } else {
            System.out.println("Account does not exist. Please provide a valid account number.");
        }

        // Close scanner
        scanner.close();

        // Print goodbye message after CD interface runs
        System.out.println("\nThank you for using the Certificate of Deposit system. Goodbye!");
    }

    public static void createCertificateOfDeposit(Scanner scanner) {
        System.out.println("\n\nCertificate of Deposit Interface\n");

        // Provide menu of term lengths and interest rates
        int[] termLengths = {6, 12, 24, 36, 48, 60};
        double[] interestRates = {4.35, 5.00, 4.30, 4.10, 4.05, 4.00};

        System.out.println("Available term lengths and interest rates:");
        for (int i = 0; i < termLengths.length; i++) {
            System.out.println(termLengths[i] + " Months, " + String.format("%.2f", interestRates[i]) + "%");
        }

        int termMonths;
        double interestRate;
        boolean termValid = false;

        do {
            // Prompt user to enter their desired term length
            System.out.print("\nEnter desired term length (in months): ");
            termMonths = scanner.nextInt();

            interestRate = getInterestRate(termLengths, interestRates, termMonths);
            if (interestRate != 0.0) {
                termValid = true;
            } else {
                System.out.println("Invalid term length. Please select from the available options.");
            }
        } while (!termValid);

        double initialDeposit;
        do {
            // Prompt user to enter initial deposit amount
            System.out.print("\nEnter initial deposit amount (must be at least $1,000): $");
            initialDeposit = scanner.nextDouble();
            // Mandate users to enter amount >= $1,000
            if (initialDeposit < 1000) {
                System.out.println("The initial deposit must be at least $1,000. Please re-enter.");
            }
        } while (initialDeposit < 1000);

        // Calculate maturity amount and store in variable
        double maturityAmount = initialDeposit * Math.pow(1 + (interestRate / 100), termMonths / 12.0);

        // Provide an overview of the CD, including deposit, term, rate, etc.
        System.out.println("\nCertificate of Deposit created successfully!");
        System.out.println("Initial Deposit: $" + String.format("%.2f", initialDeposit));
        System.out.println("Term: " + termMonths + " months");
        System.out.println("Interest Rate: " + String.format("%.2f", interestRate) + "%");

        // Query user if they wish to withdraw money before maturity
        System.out.print("\nDo you wish to withdraw money before maturity? (yes/no): ");
        scanner.nextLine();
        String withdrawResponse = scanner.nextLine().trim().toLowerCase();

        // If the user withdraws before maturity, run method that calculates penalty
        if (withdrawResponse.equals("yes")) {
            withdrawBeforeMaturity(scanner, initialDeposit, interestRate, termMonths);
        } else {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("Your CD has been successfully completed.");

                    System.out.println("\nFinal Balance (including interest): $" + String.format("%.2f", maturityAmount));
                    timer.cancel();
                    
                    System.out.print("\nDo you want to create another Certificate of Deposit? (yes/no): ");
                    String response = scanner.nextLine().trim().toLowerCase();
                    
                    if (response.equals("yes")) {
                        createCertificateOfDeposit(scanner);
                    } else {
                        System.out.println("\nThank you for using the Certificate of Deposit system. Goodbye!");
                    }
                }
            }, 110000);
            System.out.println("CD will mature in one minute.");
        }
    }

    public static void withdrawBeforeMaturity(Scanner scanner, double initialDeposit, double interestRate, int termMonths) {
        // Enable user to enter withdrawal amount (penalty is proportionate to withdrawal amount)
        System.out.print("Enter withdrawal amount: $");
        double withdrawalAmount = scanner.nextDouble();
            // Calculate withdrawal penalty
        double penalty = withdrawalAmount * (interestRate / 100 / 12) * termMonths;
        double remainingBalance = initialDeposit - penalty;

        System.out.println("Penalty Amount: $" + String.format("%.2f", penalty));
        System.out.println("Remaining Balance: $" + String.format("%.2f", remainingBalance));
    }

    public static double getInterestRate(int[] termLengths, double[] interestRates, int termMonths) {
        for (int i = 0; i < termLengths.length; i++) {
            if (termMonths == termLengths[i]) {
                return interestRates[i];
            }
        }
        return 0.0; // Default to 0% if term length is not found
    }

    public static void writeToCSV(String filename, int userId, double initialDeposit, int termMonths, double interestRate, double maturityAmount) throws IOException {
        FileWriter csvWriter = new FileWriter(filename, true);

        // Write data to CSV file
        csvWriter.append(String.format("%d,%d,%.2f,%.2f,%.2f\n", userId, termMonths, interestRate, initialDeposit, maturityAmount));
        csvWriter.flush();
        csvWriter.close();
    }


    public static boolean checkAccountExists(String accountNumber) {
        try (BufferedReader reader = new BufferedReader(new FileReader("accounts.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 1 && parts[0].equals(accountNumber)) {
                    return true; // Account found
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return false; // Account not found
}
}

