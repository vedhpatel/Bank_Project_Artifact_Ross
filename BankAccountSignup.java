import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Scanner;

public class BankAccountSignup {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Prompt user to select bank location
        System.out.println("Welcome to the bank account signup!");
        System.out.println("Please select your bank location from the following options:");
        System.out.println("1. Old Bridge");
        System.out.println("2. Dallas");
        System.out.println("3. Los Angeles");
        System.out.println("4. Detroit");
        System.out.println("5. Denver");
        int locationChoice = Integer.parseInt(getInputWithValidation("Enter the number corresponding to your bank location: ", "^[1-5]$"));

        // Determine account ID prefix based on selected location
        String accountIdPrefix;
        switch (locationChoice) {
            case 1:
                accountIdPrefix = "0";
                break;
            case 2:
                accountIdPrefix = "2";
                break;
            case 3:
                accountIdPrefix = "4";
                break;
            case 4:
                accountIdPrefix = "6";
                break;
            case 5:
                accountIdPrefix = "8";
                break;
            default:
                accountIdPrefix = "";
        }

        // Get user input for account creation
        System.out.println("\nPlease provide the following information for your account:");

        // Get user input for each field, validate, and append to CSV
        String firstName = getInputWithValidation("First Name: ", "^[a-zA-Z]+$");
        String lastName = getInputWithValidation("Last Name: ", "^[a-zA-Z]+$");
        String ssn = getInputWithValidation("Social Security Number (XXX-XX-XXXX): ", "^\\d{3}-\\d{2}-\\d{4}$");
        String dob = getDateInputWithValidation("Date of Birth (MM/DD/YYYY): ");
        String email = getInputWithValidation("Email Address: ", "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        String phoneNumber = getInputWithValidation("Cell Phone Number (+1 (123) 456-7890): ", "^\\+1 \\(\\d{3}\\) \\d{3}-\\d{4}$");
        double openingDeposit = Double.parseDouble(getInputWithValidation("Opening Deposit (Minimum $100.00): $", "^\\d+(\\.\\d+)?$"));

        // Verify user information
        System.out.println("\nPlease verify the provided information:");
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Social Security Number: " + ssn);
        System.out.println("Date of Birth: " + dob);
        System.out.println("Email Address: " + email);
        System.out.println("Cell Phone Number: " + phoneNumber);
        System.out.println("Opening Deposit: $" + openingDeposit);

        // Confirm user's information
        if (!getConfirmation("Is the information correct? (yes/no): ")) {
            System.out.println("Please run the program again and provide the correct information.");
            return;
        }

        // Validate user information
        String accountId = generateAccountId(accountIdPrefix); // Generate account ID
        String password = getPasswordWithValidation(); // Get password

        // Encrypt password in Base64 format
        String encryptedPassword = Base64.getEncoder().encodeToString(password.getBytes());

        if (isApproved(dob, ssn, openingDeposit)) {
            System.out.println("Congratulations! Your account has been approved.");

            // Create an account
            BankAccount account = new BankAccount(accountId, firstName, lastName, ssn, dob, email, phoneNumber, openingDeposit);
            // Output account information to CSV file
            writeAccountToCSV(account);
            // Output account ID and password to passwords.csv
            writePasswordToCSV(accountId, encryptedPassword);

            // Provide account ID to user
            System.out.println("Your account has been successfully created.");
            System.out.println("Your account ID is: " + accountId);
            System.out.println("Please remember to save your account ID for future reference.");
        } else {
            System.out.println("Sorry, your account cannot be created. Please make sure you meet all requirements.");
        }
    }

    private static boolean isApproved(String dob, String ssn, double openingDeposit) {
        // Check if the user is 18 or older
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        try {
            Date birthDate = dateFormat.parse(dob);
            Date currentDate = new Date();
            long ageInMillis = currentDate.getTime() - birthDate.getTime();
            long ageInYears = ageInMillis / (1000L * 60 * 60 * 24 * 365);
            if (ageInYears < 18) {
                System.out.println("You must be 18 or older to open an account.");
                return false;
            }
        } catch (ParseException e) {
            return false;
        }

        // Check if the DOB is not earlier than 1900
        try {
            Date dobDate = dateFormat.parse(dob);
            Date oldestAllowedDate = dateFormat.parse("01/01/1900");
            if (dobDate.before(oldestAllowedDate)) {
                System.out.println("Invalid date of birth. Please provide a valid date.");
                return false;
            }
        } catch (ParseException e) {
            return false;
        }

        // Check if the opening deposit is $100.00 or more
        if (openingDeposit < 100.00) {
            System.out.println("Opening deposit must be at least $100.00.");
            return false;
        }

        // Check if the SSN provided does not match any other in the CSV
        try (BufferedReader reader = new BufferedReader(new FileReader("accounts.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4 && parts[3].equals(ssn)) {
                    System.out.println("A user with this Social Security Number already exists.");
                    return false; // SSN already exists
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private static String getPasswordWithValidation() {
        String password;
        while (true) {
            System.out.print("Create a password (must be at least 8 characters and contain at least one special character): ");
            password = scanner.nextLine();
            if (password.length() < 8) {
                System.out.println("Invalid password! Password must be at least 8 characters long.");
                continue;
            } else if (!password.matches(".*[!@#$%^&*()\\[\\]{};:',.?/|`~\"\\\\].*")) {
                System.out.println("Invalid password! Password must contain at least one special character.");
                continue;
            }
            break; // Password meets requirements, exit loop
        }
        return password;
    }

    private static String getDateInputWithValidation(String prompt) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        dateFormat.setLenient(false); // Ensure strict date parsing
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                dateFormat.parse(input);
                return input;
            } catch (ParseException e) {
                System.out.println("Invalid date format! Please provide a valid date (MM/DD/YYYY).");
            }
        }
    }

    private static boolean getConfirmation(String prompt) {
        while (true) {
            System.out.print(prompt);
            String response = scanner.nextLine().trim().toLowerCase();
            if (response.equals("yes") || response.equals("y")) {
                return true;
            } else if (response.equals("no") || response.equals("n")) {
                return false;
            } else {
                System.out.println("Invalid response. Please enter 'yes' or 'no'.");
            }
        }
    }

    private static String getInputWithValidation(String prompt, String regex) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            if (!input.matches(regex)) {
                System.out.println("Invalid input! Please try again.");
            } else {
                return input;
            }
        }
    }

    private static String generateAccountId(String prefix) {
        return "ACCT" + prefix + System.currentTimeMillis();
    }

    private static void writeAccountToCSV(BankAccount account) {
        // Write account information to CSV file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("accounts.csv", true))) {
            File file = new File("accounts.csv");
            if (!file.exists()) {
                // Write the CSV header syntax as a comment in the first line
                writer.write("# Account num, First Name, Last Name, SSN, DOB, Email, Phone, Initial Deposit, Mortgage Loan, Money Transfer, Foreign Currency, ATM, Automobile Loan, Savings Account, Money Market Account, Certificate of Deposit, Home Equity Loan, Credit Card, Checking Account, Debit Card, Commercial Loan, Federal Treasury Bonds, Annuities Investment, Safe Deposit Box, Payroll\n# For your account, a boolean value is set to false by default. If your user decides to create an account, set your account's boolean value to true.\n# Below is an example user. Once signup is properly running, all signups will be appended below the example user.\nACCTXXXXXXXXXXXXXX,FirstName,LastName,XXX-XX-XXXX,01/01/2000,example@mail.com,+1 (123) 456-7890,100.00,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false\n");
            }
            // Append account information to the CSV file
            writer.write("\n" + account.toCSV());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writePasswordToCSV(String accountId, String encryptedPassword) {
        // Write account ID and password to passwords.csv
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("passwords.csv", true))) {
            writer.write("\n" + accountId + "," + encryptedPassword);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class BankAccount {
        private String accountId;
        private String firstName;
        private String lastName;
        private String ssn;
        private String dob;
        private String email;
        private String phoneNumber;
        private double initialDeposit;
        private boolean mortgageLoan;
        private boolean moneyTransfer;
        private boolean foreignCurrency;
        private boolean atm;
        private boolean automobileLoan;
        private boolean savingsAccount;
        private boolean moneyMarketAccount;
        private boolean certificateOfDeposit;
        private boolean homeEquityLoan;
        private boolean creditCard;
        private boolean checkingAccount;
        private boolean debitCard;
        private boolean commercialLoan;
        private boolean federalTreasuryBonds;
        private boolean annuitiesInvestment;
        private boolean safeDepositBox;
        private boolean payroll;

        public BankAccount() {
            accountId = "ACCTXXXXXXXXXXXXXX";
            firstName = "firstName";
            lastName = "lastName";
            ssn = "XXX-XX-XXXX";
            dob = "MM/DD/YYYY";
            email = "example@mail.com";
            phoneNumber = "+1 (123) 456-7890";
            initialDeposit = 100.00;
            mortgageLoan = false;
            moneyTransfer = false;
            foreignCurrency = false;
            atm = false;
            automobileLoan = false;
            savingsAccount = false;
            moneyMarketAccount = false;
            certificateOfDeposit = false;
            homeEquityLoan = false;
            creditCard = false;
            checkingAccount = false;
            debitCard = false;
            commercialLoan = false;
            federalTreasuryBonds = false;
            annuitiesInvestment = false;
            safeDepositBox = false;
            payroll = false;
        }

        public BankAccount(String accountId, String firstName, String lastName, String ssn, String dob, String email, String phoneNumber, double initialDeposit) {
            this.accountId = accountId;
            this.firstName = firstName;
            this.lastName = lastName;
            this.ssn = ssn;
            this.dob = dob;
            this.email = email;
            this.phoneNumber = phoneNumber;
            this.initialDeposit = initialDeposit;
            this.mortgageLoan = false;
            this.moneyTransfer = false;
            this.foreignCurrency = false;
            this.atm = false;
            this.automobileLoan = false;
            this.savingsAccount = false;
            this.moneyMarketAccount = false;
            this.certificateOfDeposit = false;
            this.homeEquityLoan = false;
            this.creditCard = false;
            this.checkingAccount = false;
            this.debitCard = false;
            this.commercialLoan = false;
            this.federalTreasuryBonds = false;
            this.annuitiesInvestment = false;
            this.safeDepositBox = false;
            this.payroll = false;
        }

        // Getter methods
        public String getAccountId() {
            return accountId;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getSsn() {
            return ssn;
        }

        public String getDob() {
            return dob;
        }

        public String getEmail() {
            return email;
        }

        public String toCSV() {
            // Convert account information to CSV format
            return String.format("%s,%s,%s,%s,%s,%s,%s,%.2f,%b,%b,%b,%b,%b,%b,%b,%b,%b,%b,%b,%b,%b,%b,%b,%b,%b",
                    accountId, firstName, lastName, ssn, dob, email, phoneNumber, initialDeposit,
                    mortgageLoan, moneyTransfer, foreignCurrency, atm, automobileLoan, savingsAccount,
                    moneyMarketAccount, certificateOfDeposit, homeEquityLoan, creditCard, checkingAccount,
                    debitCard, commercialLoan, federalTreasuryBonds, annuitiesInvestment, safeDepositBox, payroll);
        }
    }
}