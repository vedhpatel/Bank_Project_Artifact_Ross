import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class mortgage {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        // get userID
        String userID = MainMenu.loggedInAccountId;

        int cred = getCreditScoreFromCSV("ccard.csv", userID);

        if (cred >= 620) {
            double loanAmount = -1; // Initialize loanAmount to a neg. value
            while (loanAmount <= 0) {
                System.out.println("What loan amount would you like to take out? (Enter in USD)");
                System.out.print("Loan Amount: $");
                loanAmount = scanner.nextDouble();
                scanner.nextLine(); // consume the newline character
                if (loanAmount <= 0) {
                    System.out.println("Please enter a loan amount greater than 0.");
                }
            }

            System.out.println("Would you like to make a down payment to your loan?");
            System.out.print("Enter 'y' for yes and 'n' for no: ");
            String answer = scanner.nextLine();
            
            double downPayment = 0.0;

            if (answer.equals("y")) {
                System.out.print("Please enter down payment amount: $");
                downPayment = scanner.nextDouble();

                loanAmount -= downPayment;
                scanner.nextLine(); // consume the newline character
                
                //asks the user what account they want to make the down payment from
                System.out.println("Which account would you like to make the down payment from? Enter 's' for savings account and 'c' for checking account: ");
                String dp = scanner.nextLine().toLowerCase();

                //reading checking and savings account csvs
                if (dp.equals("c")) {
                    // Subtract down payment from checking account
                    subtractFromChecking(userID, downPayment);
                } else if (dp.equals("s")) {
                    // Subtract down payment from savings account
                    subtractFromSavings(userID, downPayment);
                } else {
                    System.out.println("Invalid choice. Assuming savings account.");
                    subtractFromSavings(userID, downPayment);
                }


                System.out.println("This is your new loan amount: $" + loanAmount + " and your down payment has been subtracted from your chosen account balance");

            } else if (!answer.equals("n")) {
                System.out.println("Invalid input. Assuming no down payment.");
            }

            Fixed calculateMonthlyPayment = new Fixed();

            System.out.println("What type of loan would you like to take out?");
            System.out.println("Loan options:");
            System.out.println("----------------------------");
            System.out.println("fixed");
            System.out.println("standard adjustable");
            System.out.println("----------------------------");
            String loan = scanner.nextLine().toLowerCase();

            if (loan.equals("fixed")) {
                System.out.println("What type of fixed loan would you like to take out?");
                System.out.println("fixed loan options: ");
                System.out.println("----------------------------");
                System.out.println("30 year fixed");
                System.out.println("20 year fixed");
                System.out.println("15 year fixed");
                System.out.println("----------------------------");

                loan = scanner.nextLine().toLowerCase();
            } else if (loan.equals("standard adjustable")) {
                System.out.println("What type of adjustable loan would you like to take out?");
                System.out.println("adjustable loan options: ");
                System.out.println("-----------------------------");
                System.out.println("10/6 ARM");
                System.out.println("5/1 ARM");
                System.out.println("5/6 ARM");
                System.out.println("7/1 ARM");
                System.out.println("7/6 ARM");
                System.out.println("10/1 ARM");
                System.out.println("----------------------------");

                loan = scanner.nextLine().toLowerCase();
            } else {
                System.out.println("Please enter a valid loan type.");
                return;
            }

            double annualInterestRate = 0.0;
            int loanTermYears = 0;

            if (loan.equals("30 year fixed")) {
                loanTermYears = 30;
                annualInterestRate = 7.1;
            } else if (loan.equals("20 year fixed")) {
                loanTermYears = 20;
                annualInterestRate = 6.95;
            } else if (loan.equals("15 year fixed")) {
                loanTermYears = 15;
                annualInterestRate = 6.48;
            } else if (loan.equals("10/6 arm")) {
                loanTermYears = 30;
                annualInterestRate = 7.0;
                Adjustable adjustable = new Adjustable(loanAmount, annualInterestRate);
                adjustable.simulateAdjustableRateOverTime(10, 0.5); // Simulate 10/6 ARM behavior over 10 years w/ adjustments monthly
            } else if (loan.equals("5/1 arm")) {
                loanTermYears = 30; // Initial fixed-rate period of 30 years
                annualInterestRate = 6.5; // Initial fixed interest rate
                Adjustable adjustable = new Adjustable(loanAmount, annualInterestRate);
                adjustable.simulateAdjustableRateOverTime(5, 1); // Simulate 5/1 ARM behavior over 5 years w/ adjustments yearly
            } else if (loan.equals("5/6 arm")) {
                loanTermYears = 30; // Initial fixed-rate period of 30 years
                annualInterestRate = 6.25; // Initial fixed interest rate
                Adjustable adjustable = new Adjustable(loanAmount, annualInterestRate);
                adjustable.simulateAdjustableRateOverTime(5, 0.5); // Simulate 5/6 ARM behavior over 5 years with adjustments every 6 months
            } else if (loan.equals("7/1 arm")) {
                loanTermYears = 30; // Initial fixed-rate period of 30 years
                annualInterestRate = 6.75; // Initial fixed interest rate
                Adjustable adjustable = new Adjustable(loanAmount, annualInterestRate);
                adjustable.simulateAdjustableRateOverTime(7, 1); // Simulate 7/1 ARM behavior over 7 years with adjustments every year
            } else if (loan.equals("7/6 arm")) {
                loanTermYears = 30; // Initial fixed-rate period of 30 years
                annualInterestRate = 6.9; // Initial fixed interest rate
                Adjustable adjustable = new Adjustable(loanAmount, annualInterestRate);
                adjustable.simulateAdjustableRateOverTime(7, 0.5); // Simulate 7/6 ARM behavior over 7 years with adjustments every 6 months
            } else if (loan.equals("10/1 arm")) {
                loanTermYears = 30; // Initial fixed-rate period of 30 years
                annualInterestRate = 7.2; // Initial fixed interest rate
                Adjustable adjustable = new Adjustable(loanAmount, annualInterestRate);
                adjustable.simulateAdjustableRateOverTime(10, 1); // Simulate 10/1 ARM behavior over 10 years with adjustments every year
            }

            // Call calculateMonthlyPayment method
            double monthlyPayment = calculateMonthlyPayment.calculateMonthlyPayment(loanAmount, annualInterestRate, loanTermYears);
            System.out.println("Monthly Payment for a $" + loanAmount + " loan: $" + monthlyPayment);


            // Save customer information to CSV file
            saveCustomerToCSV(userID, loan, loanAmount, downPayment, monthlyPayment);

            // Ask if the user wants to set up automatic payment or pay manually
            System.out.println("How would you like to pay your monthly mortgage?");
            System.out.println("---------------------------");
            System.out.println("1) Automatic Payment");
            System.out.println("2) Manual Payment");
            System.out.println("---------------------------");

            System.out.print("Enter your choice (1 or 2): ");
            int paymentChoice = scanner.nextInt();
            scanner.nextLine(); // consume newline character

            if (paymentChoice == 1) {
                // Call scheduleAutomaticPayment method
                AutomaticPaymentProcessor.scheduleAutomaticPayment(monthlyPayment);

                // Prompt user to choose account for automatic payment
                System.out.println("Choose the account for automatic payment:");
                System.out.println("1. Savings Account");
                System.out.println("2. Checking Account");
                System.out.print("Enter your choice (1 or 2): ");
                int accountChoice = scanner.nextInt();
                scanner.nextLine(); // consume newline character

                if (accountChoice == 1) {
                    subtractFromSavingsMP(userID, monthlyPayment);
                } else if (accountChoice == 2) {
                    subtractFromCheckingMP(userID, monthlyPayment);
                } else {
                    System.out.println("Invalid choice. Automatic payment will be deducted from savings account.");
                    subtractFromSavingsMP(userID, monthlyPayment);
                }

                System.out.println("Automatic payment of $" + monthlyPayment + " has been scheduled and the first monthly payment has been deducted.");
            } else if (paymentChoice == 2) {
                // Manual Payment
                // Subtract monthly payment from chosen account
                System.out.println("Choose the account for manual payment:");
                System.out.println("1. Savings Account");
                System.out.println("2. Checking Account");
                System.out.print("Enter your choice (1 or 2): ");
                int accountChoice = scanner.nextInt();
                scanner.nextLine(); // consume newline character

                if (accountChoice == 1) {
                    subtractFromSavingsMP(userID, monthlyPayment);
                } else if (accountChoice == 2) {
                    subtractFromCheckingMP(userID, monthlyPayment);
                } else {
                    System.out.println("Invalid choice. Monthly payment will be deducted from savings account.");
                    subtractFromSavingsMP(userID, monthlyPayment);
                }

                System.out.println("Your monthly payment of $" + monthlyPayment + " has been deducted from your chosen account.");
            } else {
                System.out.println("Invalid choice. Assuming manual payment.");
                // Subtract monthly payment from chosen account
                System.out.println("Choose the account for manual payment:");
                System.out.println("1. Savings Account");
                System.out.println("2. Checking Account");
                System.out.print("Enter your choice (1 or 2): ");
                int accountChoice = scanner.nextInt();
                scanner.nextLine(); // consume newline character

                if (accountChoice == 1) {
                    subtractFromSavingsMP(userID, monthlyPayment);
                } else if (accountChoice == 2) {
                    subtractFromCheckingMP(userID, monthlyPayment);
                } else {
                    System.out.println("Invalid choice. Monthly payment will be deducted from savings account.");
                    subtractFromSavingsMP(userID, monthlyPayment);
                }

                System.out.println("Your monthly payment of $" + monthlyPayment + " has been deducted from your chosen account.");
            } 
        } else {
            System.out.println("You are not eligible for a mortgage loan at this time given your current credit score.");
        }

        scanner.close(); // close the scanner
    }

    private static void saveCustomerToCSV(String userID, String loanType, double loanAmount, double downPayment, double monthlyPayment) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("mortgage.csv", true))) {
            String line = userID + "," + loanType + "," + loanAmount + "," + downPayment + "," + monthlyPayment;
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getCreditScoreFromCSV(String Filename, String userID) {
        int cred = 0; // Initialize cred to a default value
        try (BufferedReader reader = new BufferedReader(new FileReader("ccard.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 6 && data[5].trim().equals(userID)) {
                    // Assuming credit score is at index 1
                    cred = Integer.parseInt(data[1].trim());
                    break; // Exit loop once credit score is found
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return cred;
    }

    //subtract down payment from checking account
    private static void subtractFromChecking(String userID, double downPayment) {
        try (BufferedReader reader = new BufferedReader(new FileReader("checkingsaccount.csv"))) {
            String line;
            StringBuilder content = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 2 && data[0].trim().equals(userID)) {
                    double currentBalance = Double.parseDouble(data[1].trim());
                    currentBalance -= downPayment;
                    data[1] = Double.toString(currentBalance);
                    line = String.join(",", data);
                }
                content.append(line).append("\n");
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("checkingsaccount.csv"))) {
                writer.write(content.toString());
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    // Method to subtract down payment from savings account
    private static void subtractFromSavings(String userID, double downPayment) {
        try (BufferedReader reader = new BufferedReader(new FileReader("savingsaccount.csv"))) {
            String line;
            StringBuilder content = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 2 && data[0].trim().equals(userID)) {
                    double currentBalance = Double.parseDouble(data[1].trim());
                    currentBalance -= downPayment;
                    data[1] = Double.toString(currentBalance);
                    line = String.join(",", data);
                }
                content.append(line).append("\n");
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("savingsaccount.csv"))) {
                writer.write(content.toString());
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    //subtract monthly payment from checking account
    private static void subtractFromCheckingMP(String userID, double monthlyPayment) {
        try (BufferedReader reader = new BufferedReader(new FileReader("checkingsaccount.csv"))) {
            String line;
            StringBuilder content = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 2 && data[0].trim().equals(userID)) {
                    double currentBalance = Double.parseDouble(data[1].trim());
                    currentBalance -= monthlyPayment;
                    data[1] = Double.toString(currentBalance);
                    line = String.join(",", data);
                }
                content.append(line).append("\n");
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("checkingsaccount.csv"))) {
                writer.write(content.toString());
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private static void subtractFromSavingsMP(String userID, double monthlyPayment) {
        try (BufferedReader reader = new BufferedReader(new FileReader("savingsaccount.csv"))) {
            String line;
            StringBuilder content = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 2 && data[0].trim().equals(userID)) {
                    double currentBalance = Double.parseDouble(data[1].trim());
                    currentBalance -= monthlyPayment;
                    data[1] = Double.toString(currentBalance);
                    line = String.join(",", data);
                }
                content.append(line).append("\n");
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("savingsaccount.csv"))) {
                writer.write(content.toString());
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }


    static class Fixed {
        public double calculateMonthlyPayment(double loanAmount, double annualInterestRate, int loanTermYears) {
            int months = loanTermYears * 12;
            double monthlyInterestRate = annualInterestRate / 12 / 100;
            double monthlyPayment = loanAmount * monthlyInterestRate / (1 - Math.pow(1 + monthlyInterestRate, -months));
            monthlyPayment = Math.round(monthlyPayment * 100.0) / 100.0;
            return monthlyPayment;
        }
    }

    static class AutomaticPaymentProcessor {
        public static void scheduleAutomaticPayment(double monthlyPayment) {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                public void run() {
                    processAutomaticPayment(monthlyPayment);
                }
            }, 0, 2592000000L); // 2.592e+9 milliseconds
        }

        private static void processAutomaticPayment(double monthlyPayment) {
            System.out.println("Processing automatic payment of $" + monthlyPayment);
        }
    }

    static class Adjustable {
        private double loanAmount;
        private double initialRate;

        public Adjustable(double loanAmount, double initialRate) {
            this.loanAmount = loanAmount;
            this.initialRate = initialRate;
        }

        public void simulateAdjustableRateOverTime(double years, double adjustmentInterval) {
            Random rand = new Random();
            double currentRate = initialRate;
            for (double i = 0; i < years; i += adjustmentInterval) {
                // generating a random interest rate change between 0.01 and 0.03 (inclusive); based off of known data for interest fluctuation
                int randomNumber = rand.nextInt(3) + 1;
                double randomDecimal = randomNumber / 100.0;
                // randomly subtracting or adding the rate change
                int option = rand.nextInt(2) + 1;
                if (option == 1) {
                    currentRate += randomDecimal;
                } else {
                    currentRate -= randomDecimal;
                }
            }
        }
    }
}
