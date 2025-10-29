import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
//stuff for experation date
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class safetyDeposit {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
      
        System.out.println("Enter your username:");
        String username = scanner.nextLine();

        try {
            // Read CSV file and search for user's entries
            List<String[]> entries = readBoxRecords();
            boolean boxFound = false;

            // Check if the user has existing safety deposit boxes
            List<String[]> userBoxes = new ArrayList<>();
            for (String[] entry : entries) {
                if (entry[0].equalsIgnoreCase(username)) {
                    userBoxes.add(entry);
                }
            }

            // Print existing boxes if user has
            if (!userBoxes.isEmpty()) {
                System.out.println("Here are your existing Safety Deposit Boxes:");
                System.out.println("--------------------------------------------");
                for (String[] box : userBoxes) {
                    System.out.println("Safety Deposit Box ID: " + box[4]);
                    System.out.println("Box Type: " + box[1]);
                    System.out.println("Date of Purchase: " + box[2]);
                    System.out.println("Expiration Date: " + box[3]);
                    System.out.println("--------------------------------------------");
                }
                boxFound = true;
            }


          
            // If no box, prompt user
            if (!boxFound) {
                System.out.println("No safety deposit box found for the provided username.");
            }

            // Ask if the user wants to purchase another box (if already existing)
            String choice;
            do {
                System.out.println("Would you like to buy a new safety deposit box? (yes/no)");
                choice = scanner.nextLine();
            } while (!choice.equalsIgnoreCase("yes") && !choice.equalsIgnoreCase("no"));

            if (choice.equalsIgnoreCase("yes")) {
                buyNewBox(username);
            } else {
                System.out.println("Come back anytime.");
            }
        } catch (IOException e) {
            System.out.println("Error reading box records: " + e.getMessage());
        }
    }
  

    private static List<String[]> readBoxRecords() throws IOException {
        List<String[]> entries = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("safetyDeposit.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                entries.add(parts);
            }
        }
        return entries;
    }

    private static void writeNewUser(String username, String boxSize, LocalDate currentDate, LocalDate expirationDate, String boxID) {
        // Write data to CSV file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("safetyDeposit.csv", true))) {
            // Format dates
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            String purchaseDateStr = currentDate.format(formatter);
            String expirationDateStr = expirationDate.format(formatter);

            // Write username, box size, taken out date, expiration date, and box ID to file
            String line = username + "," + boxSize + "," + purchaseDateStr + "," + expirationDateStr + "," + boxID;
            writer.write(line);
            writer.newLine();

            // Update the accounts.csv file to mark the user's account with a safety deposit box
            updateAccount(username);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

  
  //updating to master csv

    private static void updateAccount(String username) {
        // Read the accounts.csv file and update the entry for the user's account
        try {
            List<String> lines = Files.readAllLines(Paths.get("accounts.csv"));
            for (int i = 1; i < lines.size(); i++) { // Start from 1 to skip header line
                String[] parts = lines.get(i).split(",");
                if (parts[0].equals(username)) {
                    parts[23] = "true"; // Index 23 corresponds to the "Safe Deposit Box" field
                    lines.set(i, String.join(",", parts));
                    break;
                }
            }
            // Write the updated lines back to the accounts.csv file
            Files.write(Paths.get("accounts.csv"), lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //subtract annual fee from checking account
    private static void subtractFromChecking(String username, double annualFee) {
        try (BufferedReader reader = new BufferedReader(new FileReader("checkingsaccount.csv"))) {
            String line;
            StringBuilder content = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 2 && data[0].trim().equals(username)) {
                    double currentBalance = Double.parseDouble(data[1].trim());
                    currentBalance -= annualFee;
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
  

    private static void buyNewBox(String username) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What box size would you like to buy?");
        System.out.println("----------------------------");
        System.out.println("Small box (5 x 5 x 21.5) : $50/year");
        System.out.println("Medium box (3 x 10 x 21.5) : $60/year");
        System.out.println("Large box (5 x 10 x 21.5) : $80/year");
        String boxSize;
        double annualFee;
        do {
            boxSize = scanner.nextLine();
            if (boxSize.equalsIgnoreCase("small box")) {
                annualFee = 50.0;
                subtractFromChecking(username, annualFee);
            } else if (boxSize.equalsIgnoreCase("medium box")) {
                annualFee = 60.0;
                subtractFromChecking(username, annualFee);
            } else if (boxSize.equalsIgnoreCase("large box")) {
                annualFee = 80.0;
                subtractFromChecking(username, annualFee);
            } else {
                System.out.println("Invalid choice. Please choose again.");
                continue;
            }
            break;
        } while (true);

        // Get current date and calc. expiration date
        LocalDate currentDate = LocalDate.now();
        LocalDate expirationDate = currentDate.plusYears(1);

        // Generate box ID
        String boxID = generateBoxID();

        // Proceed with creating a new entry for the user's safety deposit box
        writeNewUser(username, boxSize, currentDate, expirationDate, boxID);

        // Inform the user about the purchase
        System.out.println("You have successfully purchased a new " + boxSize + " safety deposit box and your annual fee of $" + annualFee + "0 has been deducted from your checking account.");
        System.out.println("Safety Deposit Box ID: " + boxID);
        System.out.println("Date of Purchase: " + currentDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        System.out.println("Expiration Date: " + expirationDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
    }
  

    private static String generateBoxID() {
        // Generate a random 6-digit alphanumeric string
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder boxID = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int index = (int) (Math.random() * characters.length());
            boxID.append(characters.charAt(index));
        }
        return boxID.toString();
    }
}
