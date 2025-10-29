import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class BankAccountMenu {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException, ParseException {
        System.out.println("Welcome to the bank account menu!");

        boolean exitMenu = false;
        while (!exitMenu) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Login");
            System.out.println("2. Signup");
            System.out.println("3. Account Recovery");
            System.out.println("4. Exit");

            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    System.out.println();
                    BankAccountLogin.main(args);
                    break;
                case 2:
                    System.out.println();
                    BankAccountSignup.main(args);
                    break;
                case 3:
                    System.out.println();
                    BankAccountRecovery.main(args);
                    break;
                case 4:
                    System.out.println("Exiting bank account menu. Goodbye!");
                    exitMenu = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
}