import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class MainMenu {
    private static final Scanner scanner = new Scanner(System.in);
    public static String loggedInAccountId;

    public static void setLoggedInAccountId(String accountId) {
        loggedInAccountId = accountId;
    }

    public static void showMainMenu(String accountId) throws IOException, ParseException {
        setLoggedInAccountId(accountId);
        System.out.println("\nWelcome to the main menu!");

        boolean exitMenu = false;
        while (!exitMenu) {
            System.out.println("\nSelect an option:");
            System.out.println("1. ATM");
            System.out.println("2. Credit Card");
            System.out.println("3. Customer Info");
            System.out.println("4. Debit Card");
            System.out.println("5. Foreign Currency");
            System.out.println("6. Payroll");
            System.out.println("7. Safety Deposit");
            System.out.println("8. Annuities Investments");
            System.out.println("9. Certificate of Deposit");
            System.out.println("10. Money Transfer");
            System.out.println("11. Treasury Bonds");
            System.out.println("12. Your Accounts");
            System.out.println("13. Your Loans");
            System.out.println("14. Exit");

            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    System.out.println("You selected ATM.");
                    System.out.println("Launching...");
                    ATM2.SignIn(choice, accountId, accountId, null, null);
                    break;
                case 2:
                    System.out.println("You selected Credit Card.");
                    System.out.println("Launching...");
                    CMain.main(new String[]{});
                    break;
                case 3:
                    System.out.println("You selected Customer Info.");
                    System.out.println("Launching...");
                    CustomerInfo.main(new String[]{});
                    break;
                case 4:
                    System.out.println("You selected Debit Card.");
                    System.out.println("Launching...");
                    DebitCard.main(new String[]{});
                    break;
                case 5:
                    System.out.println("You selected Foreign Currency.");
                    System.out.println("Launching...");
                    Tester.main(new String[]{});
                    break;
                case 6:
                    System.out.println("You selected Payroll.");
                    System.out.println("Launching...");
                    Payroll2.main(new String[]{});
                    break;
                case 7:
                    System.out.println("You selected Safe Deposit Box.");
                    System.out.println("Launching...");
                    safetyDeposit.main(new String[]{});
                    break;
                case 8:
                    System.out.println("You selected Annuities Investments.");
                    System.out.println("Launching...");
                    AnnuityInvestment.main(new String[]{});
                    break;
                case 9:
                    System.out.println("You selected Certificate of Deposit.");
                    System.out.println("Launching...");
                    CertificateOfDeposit.CertificateOfDepositSystem.main(new String[]{});
                    break;
                case 10:
                    System.out.println("You selected Money Transfer.");
                    System.out.println("Launching...");
                    MoneyTransfer.transferMoney(choice);
                    break;
                case 11:
                    System.out.println("You selected Treasury Bonds.");
                    System.out.println("Launching...");
                    welcome.menu(accountId);
                    break;
                case 12:
                    showAccountsMenu();
                    break;
                case 13:
                    showLoansMenu();
                    break;
                case 14:
                    System.out.println("Exiting main menu. Goodbye!");
                    exitMenu = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 14.");
            }
        }
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
    
                // Check if there is input available to read
                if (scanner.hasNextLine()) {
                    return Integer.parseInt(scanner.nextLine());
                } else {
                    System.out.println("No input found. Please try again.");
                    scanner.nextLine(); // Consume the newline character
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }    

    private static void showAccountsMenu() {
        System.out.println("\nSelect an account type:");
        System.out.println("1. Savings account");
        System.out.println("2. Money Market account");
        System.out.println("3. Checking account");
        System.out.println("4. Custodial account");
        System.out.println("5. Roth Individual Retirement account");


        int choice = getIntInput("Enter your choice: ");

        switch (choice) {
            case 1:
                System.out.println("You selected Savings account.");
                System.out.println("Launching...");
                Savings.bankProject.savingsAccount.main(new String[]{});
                break;
            case 2:
                System.out.println("You selected Money Market account.");
                System.out.println("Launching...");
                MoneyMarket.mmaDisplay.main(new String[]{});
                break;
            case 3:
                System.out.println("You selected Checking account.");
                System.out.println("Launching...");
                CheckingAccount.checkingsAccount.main(new String[]{});
                break;
            case 4:
                System.out.println("You selected Custodial account.");
                System.out.println("Launching...");
                CustodialAccount.main(new String[]{});
                break;
            case 5:
                System.out.println("You selected Roth Individual Retirement account.");
                System.out.println("Launching...");
                ROTHIRA.RothIRA.main(new String[]{});
                break;
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 5.");
        }
    }

    private static void showLoansMenu() {
        System.out.println("\nSelect a loan type:");
        System.out.println("1. Mortgage loan");
        System.out.println("2. Automobile loan");
        System.out.println("3. Home Equity loan");
        System.out.println("4. Commercial loan");
        System.out.println("5. Student loan");
        System.out.println("6. Personal loan");


        int choice = getIntInput("Enter your choice: ");

        switch (choice) {
            case 1:
                System.out.println("You selected Mortgage loan.");
                System.out.println("Launching...");
                Mortage.Mortgage.main(new String[]{});
                break;
            case 2:
                System.out.println("You selected Automobile loan.");
                System.out.println("Launching...");
                autoloan.main(new String[]{});
                break;
            case 3:
                System.out.println("You selected Home Equity loan.");
                System.out.println("Launching...");
                HomeEquity.RealHomeEquityLoanClient.main(new String[]{});
                break;
            case 4:
                System.out.println("You selected Commercial loan.");
                System.out.println("Launching...");
                CommercialLoan.commercialloan.main(new String[]{});
                break;
            case 5:
                System.out.println("You selected Student loan.");
                System.out.println("Launching...");
                StudentLoan.App.main(new String[]{});
                break;
            case 6:
                System.out.println("You selected Personal loan.");
                System.out.println("Launching...");
                PersonalLoan.PersonalLoan.main(new String[]{});
                break;
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 6.");
        }
    }
}