import java.util.Scanner;
import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class checkingsAccount {

private static final DecimalFormat df = new DecimalFormat("0.00");

private static String sa_path = "savingsaccount.csv";
private static String ca_path = "checkingsaccount.csv";
private static String a_path  = "accounts.csv";


    public static boolean verifyAcc(String boo) {
        if (boo.equalsIgnoreCase("false")) return false;
        return true;
    }

     public static void write2CSV(cAccount Cperson, String path, int Choice) throws IOException{
        
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
                if (parts.length >= 2 && parts[0].equals(Cperson.getAccNumber())) 
                {
                   parts[1] = String.valueOf(Cperson.getBalance());
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
                line = Cperson.getAccNumber() + "," + String.valueOf(Cperson.getBalance());
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
                if (parts.length >= 2 && parts[0].equals(Cperson.getAccNumber())) 
                {
                   parts[1] = String.valueOf(Cperson.getSavingsB());
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

    public static void createAccount(cAccount Cperson) {        
        Scanner accountCreation = new Scanner(System.in);
        System.out.println("Do you want to create a checkings account?\n0) no\n1) yes");
        int create = accountCreation.nextInt();
        if (create == 0){
            System.out.println("Thank you.");
            System.exit(0);
        }
        else{
            System.out.print("You need to deposit at least $75 in order to create a checkings Account\n" +
                            "Deposit Amount Here ---> ");
          

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
                    if (parts[0].trim().equals(Cperson.getAccNumber())) {
                        parts[18] = "true"; // Update verification status to true
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
            Cperson.setBalance(initialdep);
        } 
    }
    public static void readCSV(cAccount Cperson, String file) {

         String accountNumber = Cperson.getAccNumber(); 
        if (file.equals(sa_path)){

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
                            String sabalance = parts[1].trim(); 
                            double sbalance = Double.valueOf(sabalance);
                            sbalance = sbalance + Cperson.getTransfer();
                            Cperson.setSavingsB(sbalance);
                            found = true;
                        }
                    } 
                }

                if (!found) {
                    System.out.println("Account number not found for Checkings Account. Do you have one?: " + accountNumber);
                    double Balance = Cperson.getTransfer() + Cperson.getBalance();
                    Cperson.setBalance(Balance);
                }


                scanner.close();
            } catch (FileNotFoundException e) {

                System.out.println("File not found: " + e.getMessage());
            }
    } else if (file.equals(ca_path)) {
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
                        Cperson.setBalance(sbalance);

                    }
                } 
            }



            scanner.close();
        } catch (FileNotFoundException e) {

            System.out.println("File not found: " + e.getMessage());
        }
    }

    }
   
    
    public static void main(String[] args) throws IOException {
    	Scanner question = new Scanner(System.in);
    	System.out.println("What is your social security number? The bank needs to see if you have an account with us. (Write it in XXX-XX-XXXX form) ");
    	String ssnum = question.nextLine();
    	cAccount Cperson = new cAccount();
        boolean exists = false;
        try {
             Scanner sc = new Scanner(new File(a_path));
            sc.useDelimiter(",");
            while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] parts = line.split(",");
            String ssn = parts[3].trim();
            if (ssn.equals(ssnum)) {
                Cperson.setAccNumber(parts[0]);
                exists = verifyAcc(parts[18]);
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
            createAccount(Cperson);
        }
           readCSV(Cperson, ca_path);
           System.out.println(Cperson.getBalance());
           action(Cperson);
    	}

    public static void action(cAccount Cperson) throws IOException {

        System.out.println("Welcome to your checkings account. Please select an action.\n" +
        "1) Deposit\n" + 
        "2) Withdrawal\n" +
        "3) Transfer \n" +
        "4) View Balance\n" +
        "5) Exit\n" +
        "(Select 1, 2, 3, 4, or 5 to do respective action)");

        Scanner scan = new Scanner(System.in);
        int action  = scan.nextInt();
        double cbalance  =  Cperson.getBalance();

        if (action == 1){
            System.out.println("How much would you like to deposit?\n" +
            "This is your current balance: " + df.format(Cperson.getBalance()));
            Scanner scan2 = new Scanner(System.in);
            double deposit  = scan2.nextDouble();
            Cperson.setDeposit(deposit);
            cbalance  = Cperson.changeBalance(deposit, 0.00);
            Cperson.setBalance(cbalance);
            System.out.println("New Balance: " + df.format(Cperson.getBalance()));
            System.out.println("\nWould you like to see the menu again? \n 1) Yes\n 2) No");
            Scanner rerun = new Scanner(System.in);
            double runagain  = rerun.nextDouble();
            if (runagain ==1){    
                action(Cperson);
            } 
            else{
            	write2CSV(Cperson, ca_path, 0);
                System.exit(0);
            }        
           }

        if (action == 2){
            System.out.println("How much would you like to withdrawal?\n" +
            "This is your current balance: " + df.format(Cperson.getBalance()));
            Scanner scan2 = new Scanner(System.in);
            double withdrawal  = scan2.nextDouble();
            if (withdrawal > cbalance){
                System.out.println("You do NOT have that much money!\n\n\n");
                action(Cperson);
            }
            Cperson.setWithdrawal(withdrawal);
            cbalance  = Cperson.changeBalance(0.00, withdrawal);
            Cperson.setBalance(cbalance);
            System.out.println("New Balance: " + df.format(Cperson.getBalance()));
            System.out.println("\nWould you like to see the menu again? \n 1) Yes\n 2) No");
            Scanner rerun = new Scanner(System.in);
            double runagain  = rerun.nextDouble();
            if (runagain ==1){    
                action(Cperson);
            } 
            else{
            	write2CSV(Cperson, ca_path, 0);
                System.exit(0);
            }       
           }

        if (action == 3){
        	System.out.println("Please enter the amount you would like to Transfer to your savings account?");
            Scanner transferScanner = new Scanner(System.in);
            double transfer = transferScanner.nextDouble(); 
            if (transfer > cbalance){
                System.out.println("You do NOT have that much money!\n\n\n");
                action(Cperson);
            }
            Cperson.setTransfer(transfer);
            Cperson.transfrom(transfer);

            write2CSV(Cperson, ca_path, 0);
            readCSV(Cperson, sa_path);
            write2CSV(Cperson, sa_path, 1);
            

            System.out.println("\nWould you like to see the menu again? \n 1) Yes\n 2) No");
            Scanner rerun = new Scanner(System.in);
            double runagain  = rerun.nextDouble();
            if (runagain ==1){    
                action(Cperson);
            } 
            else{
                write2CSV(Cperson, ca_path, 0);
                System.exit(0);
            }
        }

        if (action == 4){
            System.out.println(df.format(Cperson.getBalance()));
            System.out.println("\nWould you like to see the menu again? \n 1) Yes\n 0) No");
            Scanner rerun = new Scanner(System.in);
            double runagain  = rerun.nextDouble();
            if (runagain ==1){    
                action(Cperson);
            } 
            else{
                write2CSV(Cperson, ca_path, 0);
                System.exit(0);
            }        
          }
        if (action == 5){
            write2CSV(Cperson, ca_path, 0);
            System.out.println("Thank you!");
            System.exit(0);
        }
    }
}
