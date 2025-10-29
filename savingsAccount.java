import java.util.Scanner;
import java.io.*;
import java.io.File;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;


public class savingsAccount {

private static final DecimalFormat df = new DecimalFormat("0.00");

private static String sa_path = "savingsaccount.csv";
private static String ca_path = "checkingsaccount.csv";
private static String a_path  = "accounts.csv";

    public static boolean verifyAcc(String boo) {
        if (boo.equalsIgnoreCase("false")) return false;
        return true;
    }
    
    
       public static void write2CSV(sAccount person, String path, int Choice) throws IOException{
        
    if (Choice == 0) {
        try {
             BufferedReader file = new BufferedReader(new FileReader(path));
            StringBuffer inputBuffer = new StringBuffer();
            String line;

            boolean found=false;
            String[] parts=null;
             while ((line = file.readLine()) != null) 
             {
                parts = line.split(",");
                if (parts.length >= 2 && parts[0].equals(person.getAccNumber())) 
                {
                   parts[1] = String.valueOf(person.getBalance());
                   line = parts[0] + "," + parts[1];
                   inputBuffer.append(line);
                   inputBuffer.append('\n');
                   found=true;
                }
                else
                {
                   inputBuffer.append(line);
                   inputBuffer.append('\n');
                }
            
             }
             
             if(found == false)
             {
                line = person.getAccNumber() + "," + String.valueOf(person.getBalance());
                inputBuffer.append(line);
                inputBuffer.append('\n');
              }
             file.close();

        FileOutputStream fileOut = new FileOutputStream(path);
        fileOut.write(inputBuffer.toString().getBytes());
        fileOut.close();

    } catch (Exception e) {
        System.out.println("Problem reading file."+e);
    }
    } else if (Choice == 1) {
        try {
             BufferedReader file = new BufferedReader(new FileReader(path));
            StringBuffer inputBuffer = new StringBuffer();
            String line;

            boolean found=false;
            String[] parts=null;
             while ((line = file.readLine()) != null) 
             {
                parts = line.split(",");
                if (parts.length >= 2 && parts[0].equals(person.getAccNumber())) 
                {
                   parts[1] = String.valueOf(person.getCheckingsB());
                   line = parts[0] + "," + parts[1];
                   inputBuffer.append(line);
                   inputBuffer.append('\n');
                   found=true;
                }
                else
                {
                   inputBuffer.append(line);
                   inputBuffer.append('\n');
                }
            
             }
             
    
             file.close();

        FileOutputStream fileOut = new FileOutputStream(path);
        fileOut.write(inputBuffer.toString().getBytes());
        fileOut.close();

    } catch (Exception e) {
        System.out.println("Problem reading file."+e);
    }
    }
       }
    
    public static void readCSV(sAccount person, String file) {

        String accountNumber = person.getAccNumber(); 
        if (file.equals(ca_path)){

            try {
                Scanner scanner = new Scanner(new File(file));

                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                } else {
                    System.out.println("Empty file or missing header.");
                    return;
                }

                boolean found = false;
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(",");
                    if (parts.length == 2) {
                        String userNumber = parts[0].trim();
                        if (userNumber.equals(accountNumber)) {
                            String chbalance = parts[1].trim(); 
                            double cbalance = Double.valueOf(chbalance);
                            cbalance = cbalance + person.getTransfer();
                            person.setCheckingsB(cbalance);
                            found = true;
                        }
                    } 
                }

                if (!found) {
                    System.out.println("Account number not found for Checkings Account. Do you have one?: " + accountNumber);
                    double Balance = person.getTransfer() + person.getBalance();
                    person.setBalance(Balance);
                }


                scanner.close();
            } catch (FileNotFoundException e) {

                System.out.println("File not found: " + e.getMessage());
            }
    } else if (file.equals(sa_path)) {
        try {
            Scanner scanner = new Scanner(new File(file));

            if (scanner.hasNextLine()) {
                scanner.nextLine();
            } else {
                System.out.println("Empty file or missing header.");
                return;
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String userNumber = parts[0].trim();
                    if (userNumber.equals(accountNumber)) {
                        String balance = parts[1].trim(); 
                        double sbalance = Double.valueOf(balance);
                        person.setBalance(sbalance);

                    }
                } 
            }



            scanner.close();
        } catch (FileNotFoundException e) {

            System.out.println("File not found: " + e.getMessage());
        }
    }

    }


    public static void createAccount(sAccount person) {        
        Scanner accountCreation = new Scanner(System.in);
        System.out.println("Do you want to create a savings account?\n0) no\n1) yes");
        int create = accountCreation.nextInt();
        if (create == 0){
            System.out.println("Thank you.");
            System.exit(0);
        }
        else{
            System.out.print("You need to deposit at least $75 in order to create a Savings Account\n" +
                            "Deposit Amount Here ---> ");
            // edit csv file element 13 to be true

            Scanner dep = new Scanner(System.in);
            int initialdep = dep.nextInt();
            while (initialdep < 75) {
                System.out.println("Please enter at least $75");
                initialdep = dep.nextInt();
            // add our initial to csv
            }
            try (Scanner scanner = new Scanner(new File(a_path))) {
                List<String> lines = new ArrayList<>();
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(",");
                    // Assuming SSN is at index 3 and account number is at index 0
                    if (parts[0].trim().equals(person.getAccNumber())) {
                        parts[13] = "true"; // Update verification status to true
                    }
                    line = String.join(",", parts);
                    lines.add(line);
                }

                // Rewrite the CSV file with updated content
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(a_path))) {
                    for (String line : lines) {
                        writer.write(line);
                        writer.newLine();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            person.setBalance(initialdep);
        } 
    } 
    public static void main(String[] args) throws IOException {
        Scanner question = new Scanner(System.in);
    	System.out.println("What is your social security number? The bank needs to see if you have an account with us. (Write it in XXX-XX-XXXX form) ");
    	String ssnum = question.nextLine();
    	sAccount person = new sAccount();
        boolean exists = false;
        try {
             Scanner sc = new Scanner(new File(a_path));
            sc.useDelimiter(",");
            while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] parts = line.split(",");
            String ssn = parts[3].trim();
            if (ssn.equals(ssnum)) {
                person.setAccNumber(parts[0]);
                exists = verifyAcc(parts[13]);
                break;
            }
            }
            sc.close(); 
        }
        catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            e.printStackTrace();
        }
        if (!exists) {
            createAccount(person);
        }
           readCSV(person, sa_path);
           System.out.println(person.getBalance());
           action(person);
    	}

        
    public static void action(sAccount person) throws IOException{

        System.out.println("Welcome to your savings account. Please select an action.\n" +
        "1) Deposit\n" + 
        "2) Withdrawal\n" +
        "3) Transfer \n" +
        "4) View Balance\n" +
        "5) Exit\n" +
        "(Select 1, 2, 3, 4, or 5 to do respective action)");

        Scanner scan = new Scanner(System.in);
        int action  = scan.nextInt();
        double sbalance  =  person.getBalance();

        if (action == 1){
            System.out.println("How much would you like to deposit?\n" +
            "This is your current balance: " + df.format(person.getBalance()));
            Scanner scan2 = new Scanner(System.in);
            double deposit  = scan2.nextDouble();
            person.setDeposit(deposit);
            sbalance  = person.changeBalance(deposit, 0.00);
            person.setBalance(sbalance);
            System.out.println("New Balance: " + df.format(person.getBalance()));
            System.out.println("\nWould you like to see the menu again? \n 1) Yes\n 2) No");
            Scanner rerun = new Scanner(System.in);
            double runagain  = rerun.nextDouble();
            if (runagain ==1){    
                action(person);
            } 
            else{
            	write2CSV(person, sa_path, 0);
                System.exit(0);
            }        
           }

        if (action == 2){
            System.out.println("How much would you like to withdrawal?\n" +
            "This is your current balance: " + df.format(person.getBalance()));
            Scanner scan2 = new Scanner(System.in);
            double withdrawal  = scan2.nextDouble();
            if (withdrawal > sbalance){
                System.out.println("You do NOT have that much money!\n\n\n");
                action(person);
            }
            person.setWithdrawal(withdrawal);
            sbalance  = person.changeBalance(0.00, withdrawal);
            person.setBalance(sbalance);
            System.out.println("New Balance: " + df.format(person.getBalance()));
            System.out.println("\nWould you like to see the menu again? \n 1) Yes\n 2) No");
            Scanner rerun = new Scanner(System.in);
            double runagain  = rerun.nextDouble();
            if (runagain ==1){    
                action(person);
            } 
            else{
            	write2CSV(person, sa_path, 0);
                System.exit(0);
            }       
           }

        if (action == 3){
            System.out.println("Please enter the amount you would like to Transfer to your checkings account?");
            Scanner transferScanner = new Scanner(System.in);
            double transfer = transferScanner.nextDouble(); 
            if (transfer > sbalance){
                System.out.println("You do NOT have that much money!\n\n\n");
                action(person);
            }
            person.setTransfer(transfer);
            person.transfrom(transfer);
           
            write2CSV(person, sa_path, 0);
            readCSV(person, ca_path);
            write2CSV(person, ca_path, 1);
            
            

            System.out.println("\nWould you like to see the menu again? \n 1) Yes\n 2) No");
            Scanner rerun = new Scanner(System.in);
            double runagain  = rerun.nextDouble();
            if (runagain ==1){    
                action(person);
            } 
            else{
                write2CSV(person, sa_path, 0);
                System.exit(0);
            }
        }

        if (action == 4){
            System.out.println(df.format(person.getBalance()));
            System.out.println("\nWould you like to see the menu again? \n 1) Yes\n 0) No");
            Scanner rerun = new Scanner(System.in);
            double runagain  = rerun.nextDouble();
            if (runagain ==1){    
                action(person);
            } 
            else{
                write2CSV(person, sa_path, 0);
                System.exit(0);
            }        
          }
        if (action == 5){
            write2CSV(person, sa_path, 0);
            System.out.println("Thank you!");
            System.exit(0);
        }
    }
}

