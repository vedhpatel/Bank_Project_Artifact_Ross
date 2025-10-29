import java.io.FileReader;
import java.io.Reader;
import java.util.Scanner;
import java.text.DecimalFormat;
import java.io.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class autoloan
{
    
    public static boolean verifyLoan(String boo)
    {
        if (boo.equalsIgnoreCase("false"))
        {
            return false;
        }
        else
        {
            return true;
        }
        
    }
    
    public static boolean verifyLoan1(String boo1)
    {
        if (boo1.equalsIgnoreCase("false"))
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    public static boolean verifyLoan2(String boo2)
    {
        if (boo2.equalsIgnoreCase("false"))
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    
    public static void write2CSV(info person)
    {
        String path = "auto.csv";
        try 
        {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
            while (true) 
            {
                if (reader.readLine() == null)
                {
                    writer.write(String.valueOf(person.getAccNumber()) + ", ");
                    writer.write(String.valueOf(df.format(person.getMonthlyPayment())) + ", ");
                    writer.write(String.valueOf(person.getInterest()) + ", ");
                    writer.write(String.valueOf(df.format(person.getLoanAmount())));
                    writer.newLine();
                    break;
                }
            }
            reader.close();
            writer.close();
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
     
     public static void read1CSV() 
     {
        info person = new info();
        String filePath = "checkingsaccount.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) 
        {
            String line;
            while ((line = reader.readLine()) != null) 
            {
                
                String[] values = line.split(",");
                
                System.out.print(values[1]);
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
     }
     
     public static void read2CSV() 
     {
         info person = new info();
        String filePath = "auto.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) 
        {
            String line;
            while ((line = reader.readLine()) != null) 
            {
                
                String[] values = line.split(",");
                
                System.out.print(values[0]);
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
     }

    
    public static void main (String[] args)
    {
        boolean exists = false;
        try 
        {
            info person = new info();
            Scanner sc = new Scanner(new File("accounts.csv"));
            sc.useDelimiter(",");
            
            int counter = 0;
            while (sc.hasNext()) 
            {
                if (counter == 12) 
                {
                    exists = verifyLoan(sc.next());
                    break;
                }
                sc.next();
                counter++;
            }
            sc.close(); 
        } 
        catch (FileNotFoundException e) 
        {
            System.err.println("File not found: " + e.getMessage());
            e.printStackTrace();
        }

        if (!exists)
        {
            boolean exists1 = false;
            try
            {
                Scanner kb = new Scanner(new File("accounts.csv"));
                kb.useDelimiter(",");
                
                int counter1 = 0;
                while (kb.hasNext())
                {
                    if (counter1 == 18)
                    {
                        exists1 = verifyLoan1(kb.next());
                        break;
                    }
                    kb.next();
                    counter1++;
                }
                kb.close();
            }
            catch (FileNotFoundException e)
            {
                System.err.println("File not found: " + e.getMessage());
                e.printStackTrace();
            }
            
            if (!exists1)
            {
                System.out.println("You must create a checking account to apply for an auto loan.");
            }
            else
            {
                boolean exists2 = false;
            try
            {
                Scanner kb = new Scanner(new File("accounts.csv"));
                kb.useDelimiter(",");
                
                int counter2 = 0;
                while (kb.hasNext())
                {
                    if (counter2 == 17)
                    {
                        exists2 = verifyLoan2(kb.next());
                        break;
                    }
                    kb.next();
                    counter2++;
                }
                kb.close();
            }
            catch (FileNotFoundException e)
            {
                System.err.println("File not found: " + e.getMessage());
                e.printStackTrace();
            }
            
            if (!exists2)
            {
                System.out.println("You must establish a credit card so a credit score may be available.");
            }
            else
            {
                loanApp();
            }
            }
            
            
        }
        else
        {
            Scanner kb = new Scanner(System.in);
            
            System.out.println("You already have an automobile loan.");
            System.out.println("Would you like to apply for another loan? Type '0' for yes and '1' for no. If you would like to make a payment, type '2'.");
            int b = 0;
            b = kb.nextInt();
        
            if (b == 0)
            {
                loanApp();
            }
            
            if (b == 1)
            {
                System.out.println("Thank you!");
            }
            
            if (b == 2)
            {
                payment();
            }
    }
    }
    
    private static final DecimalFormat df2 = new DecimalFormat("0");
    private static final DecimalFormat df = new DecimalFormat("0.00");
    
    public static void loanApp()
    {
        Scanner kb = new Scanner(System.in);
       
        //Make customer choose which loan they need
       
        System.out.println(" *** WELCOME TO THE AUTOMOBILE LOAN APPLICATION PAGE *** ");
       
        System.out.println("Would you like to apply for an automobile loan for a" +
        "\nDealer Purchase, Refinance, or Lease Buyout?");
       
        System.out.println("\nType 1 for a new car Dealer Purchase, 2 for a" + "\nused car Dealer Purchase, 3 for Refinance, and 4 for Lease Buyout.");
        int choice = 0;
        choice = kb.nextInt();        

         if (choice == 1)
        {
            if (loanaccepted() == true)
            {
                System.out.println("");
                System.out.println(newcarLoan());
                payment();
            }
            else
            {
                System.out.println("Please return to the main menu."); //change later
            }
        }
       
         if (choice == 2)
        {
            if (loanaccepted() == true)
            {
                System.out.println("");
                System.out.println(usedcarLoan());
                payment();
            }
            else
            {
                System.out.println("Please return to the main menu."); //change later
            }
        }
       
         if (choice == 3)
        {
            if (loanaccepted() == true)
            {
                System.out.println("");
                System.out.println(refinance());
                payment();
            }
            else
            {
                System.out.println("Please return to the main menu."); //change later
            }
        }
       
         if (choice == 4)
        {
            if (loanaccepted() == true)
            {
                System.out.println("");
                System.out.println(leasebuyout());
                payment();
            }
            else
            {
                System.out.println("Please return to the main menu."); // change later
            }
        }
        
    }
   
    public static boolean loanaccepted()
    {
        info person = new info();
        info2 person2 = new info2();
        Scanner kb = new Scanner(System.in);
       
        System.out.println("Please confirm your Credit Card Number below: ");
        String searchValue = kb.nextLine();
        
        String csvFile = "credit.csv";
        String line;
        
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile)))
        {
            while ((line = br.readLine()) != null)
            {
                String [] parts = line.split(",");
                if (parts.length > 0 && parts[0].equals(searchValue))
                {
                    if (parts.length > 1)
                    {
                        System.out.println("Credit Score:" + parts[1]);
                        person2.setCreditScore(parts[1]);
                    }
                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } 
        
        double dpayment = 0;
        double dratio = 0;
        boolean loan = false;
        int cscore = Integer.parseInt(person2.getCreditScore().trim());
       
        System.out.println("\nEnter your available down payment (do not use $ symbol)");
        dpayment = kb.nextDouble();
        for (int i = 1; i != 0; i++)
        {
            if (dpayment >= 0)
            {
                break;
            }
            else 
            {
                System.out.println("Enter a valid down payment.");
                dpayment = kb.nextDouble();
            }
        }
       
        System.out.println("\nEnter your debt to income ratio (Ranged from 0-100 do not use % symbol)");
        dratio = kb.nextDouble();
        for (int i = 1; i != 0; i++)
        {
            if (dratio >= 0 && dratio <= 100)
            {
                break;
            }
            else 
            {
                System.out.println("Please enter a valid debt-to-income ratio.");
                dratio = kb.nextDouble();
            }
        }
       
        for (int i = 1 ; i != 0 ; i++)
        {
           
            if (dratio <= 40 && cscore >= 620)
            {
                loan = true;
            }
            else
            {
                loan = false;
            }
        }
        
        if (loan == true)
        {
            System.out.println("\nYou have been APPROVED for an automobile loan. Please fill out the following information.");
            System.out.println(" ");
        }
        else
        {
            System.out.println("\nSorry, you do not qualify for an automobile loan at this time.");
        }
       
        return loan;
    }
   
    public static String usedcarLoan()
    {
        info person = new info();
        Scanner kb = new Scanner(System.in);
       
       
        System.out.println(" *** USED CAR LOAN APPLICATION *** ");
       
        System.out.println("No car information is needed for a used car loan.");
       
        System.out.println(" ");
        
        System.out.println("Please confirm your Account Number below: ");
        String accnum = kb.nextLine();
        person.setAccNumber(accnum);
       
        System.out.println("Input Car Loan Information:");
        System.out.println("\nWhat is the loan amount? (minimum of $7500, do not use $ symbol)");
        double loanamount = 0.0;
        loanamount = kb.nextDouble();
        person.setLoanAmount(loanamount);
       
        for (int i = 1; i != 0; i++)
        {
           if (loanamount >= 7500)
           {
               break;
           }
           else
           {
                System.out.println("Please enter a valid loan amount. Minimum of $7500. (no dollar sign)");
                loanamount = kb.nextDouble();
                person.setLoanAmount(loanamount);
           }
        }
       
       
        System.out.println("What will the length of the loan be? Choose between 48, 60, or 72 months.");
        int length = 0;
        length = kb.nextInt();
        person.setLoanLength(length);
       
        for (int i = 1; i != 0; i++)
        {
       
            if (length == 48 || length == 60 || length == 72)
                {
                    break;
                }
                else
                {
                    System.out.println("Please enter a valid loan length, 48 months, 60 months, or 72 months.");
                    length = kb.nextInt();
                    person.setLoanLength(length);
                }
        }
       
        double interest = 6.79;
        person.setInterest(interest);
        
        double monthlypayment = 0.0;
        monthlypayment = (loanamount / length) * ((interest / 100) + 1);
        person.setMonthlyPayment(monthlypayment);
       
        write2CSV(person);
        return "\nYour loan is " + "$" + df.format(person.getLoanAmount()) + " and the length of your loan is " + df2.format(person.getLoanLength())
        + " months." + "\nThe fixed APR (annual percentage rate) is " + df.format(person.getInterest()) + "%." + 
        "\nYour monthly payment will be $" + df.format(person.getMonthlyPayment()) + ".";
       
    }
   
    public static String newcarLoan()
    {
        info person = new info();
        Scanner kb = new Scanner(System.in);
       
        System.out.println("\n *** NEW CAR LOAN APPLICATION *** ");
        
        System.out.println("Please confirm your Account Number below: ");
        String accnum = kb.nextLine();
        person.setAccNumber(accnum);
       
        System.out.println("What year was the car made?");
        String answer = kb.nextLine();
       
        System.out.println("\nWhat is the make of the car?");
        String answer1 = kb.nextLine();
       
        System.out.println("\nWhat is the model of the car?");
        String answer2 = kb.nextLine();
       
        System.out.println("\nWhat is the trim of the car?");
        String answer3 = kb.nextLine();
       
        System.out.println("\nWhat is the mileage of the car?");
        String answer4 = kb.nextLine();
        
        System.out.println(" ");
        System.out.println("Input Car Loan Information:");
        System.out.println("What is the loan amount? (minimum of $7500, do not use $ symbol)");
        Double loanamount = 0.0;
        loanamount = kb.nextDouble();
        person.setLoanAmount(loanamount);
       
        for (int i = 1; i != 0; i++)
        {
           if (loanamount >= 7500)
           {
               break;
           }
           else
           {
                System.out.println("Please enter a valid loan amount. Minimum of $7500.");
                loanamount = kb.nextDouble();
                person.setLoanAmount(loanamount);
           }
        }
       
       
        System.out.println("What will the length of the loan be? Choose between 48, 60, or 72 months.");
        int length = 0;
        length = kb.nextInt();
        person.setLoanLength(length);
       
        for (int i = 1; i != 0; i++)
        {
       
            if (length == 48 || length == 60 || length == 72)
                {
                    break;
                }
                else
                {
                    System.out.println("Please enter a valid loan length, 48 months, 60 months, or 72 months.");
                    length = kb.nextInt();
                    person.setLoanLength(length);
                }
        }
       
        double interest = 6.39;
        person.setInterest(interest);
        
        double monthlypayment = 0.0;
        monthlypayment = (loanamount / length) * ((interest / 100) + 1);
        person.setMonthlyPayment(monthlypayment);
        
        write2CSV(person);
        return "\nYour loan is " + "$" + df.format(person.getLoanAmount()) + " and the length of your loan is " + df2.format(person.getLoanLength()) +
                " months." + "\nThe fixed APR (annual percentage rate) is " + df.format(person.getInterest()) + "%." + 
                "\nYour monthly payment will be " + df.format(person.getMonthlyPayment());
       
       
    }
   
    public static String refinance()
    {
        info person = new info();
        Scanner kb = new Scanner(System.in);
       
        System.out.println(" *** WELCOME TO THE REFINANCE APPLICATION PAGE *** ");
        System.out.println(" ");
        System.out.println("Please confirm your Account Number below: ");
        String accnum = kb.nextLine();
        person.setAccNumber(accnum);
       
        System.out.println("Please enter the VIN (Vehicle Indentification Number) of the vehicle.");
        String vinnumber = kb.nextLine();
       
        System.out.println(" ");
        System.out.println("Input Car Loan Information:");
        System.out.println("What is the loan amount? (minimum of $7500, do not use $ symbol)");
        Double loanamount = 0.0;
        loanamount = kb.nextDouble();
        person.setLoanAmount(loanamount);
       
        for (int i = 1; i != 0; i++)
        {
           if (loanamount >= 7500)
           {
               break;
           }
           else
           {
                System.out.println("Please enter a valid loan amount. Minimum of $7500.");
                loanamount = kb.nextDouble();
                person.setLoanAmount(loanamount);
           }
        }
       
        System.out.println("What will the length of the loan be? Choose between 48, 60, or 72 months.");
        int length = 0;
        length = kb.nextInt();
        person.setLoanLength(length);
       
        for (int i = 1; i != 0; i++)
        {
       
            if (length == 48 || length == 60 || length == 72)
                {
                   
                    break;
                }
                else
                {
                    System.out.println("Please enter a valid loan length, 48 months, 60 months, or 72 months.");
                    length = kb.nextInt();
                    person.setLoanLength(length);
                }
        }
       
        double interest = 7.59;
        person.setInterest(interest);
        
        double monthlypayment = 0.0;
        monthlypayment = (loanamount / length) * ((interest / 100) + 1);
        person.setMonthlyPayment(monthlypayment);
        
        write2CSV(person);
        return "\nYour refinanced loan is " + "$" + df.format(person.getLoanAmount()) + " and the length of your loan is " + df2.format(person.getLoanLength()) +
                " months." + "\nThe APR of your loan is " + df.format(person.getInterest()) + "% and the monthly payment is " + df.format(person.getMonthlyPayment());
    }
   
    public static String leasebuyout()
    {
        info person = new info();
        Scanner kb = new Scanner(System.in);
        
        double value = 0;
        double loan = 0;
        int length = 0;
        
        System.out.println("\n*** WELCOME TO THE LEASE BUYOUT PAGE ***");
        System.out.println("Please confirm your Account Number below: ");
        String accnum = kb.nextLine();
        person.setAccNumber(accnum);
        System.out.println("\nPlease enter the residual value of your vehicle (no dollar sign). A sales tax will automatically be applied later.");
        value = kb.nextDouble();
        System.out.print(" ");
        String a = kb.nextLine();
        System.out.println("\nEnter the VIN (Vehicle Identification Number) for your vehicle.");
        String vinnumber = kb.nextLine();
        System.out.println(" ");
        System.out.println("\nLoan Information:");
        System.out.println("\nWhat is your preferred loan amount? (no dollar sign)");
        loan = kb.nextDouble();
        person.setLoanAmount(loan);
        for (int i = 1; i != 0; i++)
        {
            if (loan >= 7500)
            {
                break;
            }
            else
            {
                System.out.println("Please enter a valid loan amount. Minimum of $7500. (no dollar sign)");
                loan = kb.nextDouble();
                person.setLoanAmount(loan);
            }
        }
        System.out.println("What is the preferred loan length? 48, 60, or 72 months.");
        length = kb.nextInt();
        person.setLoanLength(length);
        
        for (int i = 1; i != 0; i++)
        {
            if (length == 48 || length == 60 || length == 72)
            {
                break;
            }
            else 
            {
                System.out.println("Please enter a valid loan length. Either 48, 60, or 72 months.");
                length = kb.nextInt();
                person.setLoanLength(length);
            }
        }
    
        double interest = 7.59;
        person.setInterest(interest);
        
        double monthlypayment = 0.0;
        monthlypayment = (loan / length) * ((interest / 100) + 1);
        person.setMonthlyPayment(monthlypayment);
        
        write2CSV(person);
        return "\nYour lease buyout loan is for $" + df.format(person.getLoanAmount()) + " and the loan length is " + df2.format(person.getLoanLength()) + " months." +
               "\nThe APR of your loan is " + df.format(person.getInterest()) + "% and the monthly payment is " + df.format(person.getMonthlyPayment());   
    }
    
    public static void payment()
    {
        Scanner kb = new Scanner(System.in);
        payment pay = new payment();
        int account = 0;
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("*** Welcome to the Payment Site ***");
        System.out.println("\nPlease confirm your account number below.");
        String searchValue = kb.next();
        System.out.println("\nType '1' if you would like to pay with a checking account.");
        System.out.println("Type '2' if you would like to pay with a savings account.");
        account = kb.nextInt();
        if (account == 1)
        {
            String csvFile = "checkingsaccount.csv";
            String csvFile2 = "auto.csv";
            String line;
            
            try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 0 && parts[0].equals(searchValue)) {
                    if (parts.length > 1) {
                        System.out.println("Checking Account Balance: $" + parts[1]);
                        pay.setCheckingBalance(parts[1]);
                    } else {
                        System.out.println("Checking Account Balance: No balance found.");
                    }
                }
            }
        } catch (IOException e) 
        {
            e.printStackTrace();
        }
            try (BufferedReader br2 = new BufferedReader(new FileReader(csvFile2)))
            {
                while ((line = br2.readLine()) != null)
                {
                    String[] parts2 = line.split(",");
                    if (parts2.length > 0 && parts2[0].equals(searchValue))
                    {
                        if (parts2.length > 1)
                        {
                            System.out.println("Payment Due: $" + parts2[1]);
                            System.out.println("Total Payment Remaining: $" + parts2[3]);
                            pay.setPaymentDue(parts2[1]);
                            pay.setTotalDue(parts2[3]);
                        }
                        else
                        {
                            System.out.println("Payment Due: none");
                        }
                    }
                }
            } catch (IOException e2)
            {
                e2.printStackTrace();
            }
            
            int pay2 = 0;
            System.out.println("Would you like to pay this amount now? Enter '1' if you would, and '2' if you would not.");
            pay2 = kb.nextInt();
            if (pay2 == 1)
            {
                double cbal = Double.parseDouble(pay.getCheckingBalance().trim());
                double monpay = Double.parseDouble(pay.getPaymentDue().trim());
                double total = Double.parseDouble(pay.getTotalDue().trim());
                if (cbal < monpay)
                {
                    System.out.println("Balance not sufficient to make payment.");
                    System.out.println("Return with the correct amount at the time of the next payment.");
                }
                else
                {
                cbal = cbal - monpay;
                total = total - monpay;
                String cbal2 = String.valueOf(cbal);
                String total2 = String.valueOf(total);
                System.out.println("\nPayment Complete:");
                System.out.println("Checking Account Balance: $" + cbal);
                System.out.println("Total Amount Left to Pay: $" + total);
                
                String csvFile3 = "checkingsaccount.csv";
                try (BufferedReader br3 = new BufferedReader(new FileReader(csvFile3)))
                {
                    StringBuilder content = new StringBuilder();
                    String line3;
                    boolean found = false;
                    
                    while ((line3 = br3.readLine()) != null)
                    {
                        String[] parts3 = line3.split(",");
                        if (parts3.length > 0 && parts3[0].equals(searchValue))
                        {
                            line3 = searchValue + "," + cbal2;
                            found = true;
                        }
                        content.append(line3).append("\n");
                    }
                    
                    try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile3)))
                    {
                        bw.write(content.toString());
                    } catch (IOException e3)
                    {
                        e3.printStackTrace();
                    }
                } catch (IOException e3)
                {
                    e3.printStackTrace();
                }
                
                String csvFile4 = "auto.csv";
                try (BufferedReader br4 = new BufferedReader(new FileReader(csvFile4)))
                {
                    StringBuilder content2 = new StringBuilder();
                    String line4;
                    boolean found2 = false;
                    
                    while ((line4 = br4.readLine()) != null)
                    {
                        String[] parts4 = line4.split(",");
                        if (parts4.length > 0 && parts4[0].equals(searchValue))
                        {
                            if (parts4.length > 2)
                            {
                                parts4[3] = total2;
                                line4 = String.join(",", parts4);
                                found2 = true; 
                            }
                        }
                        content2.append(line4).append("\n");
                    }
                    
                    try (BufferedWriter bw2 = new BufferedWriter(new FileWriter(csvFile4)))
                    {
                        bw2.write(content2.toString());
                    } catch (IOException e4)
                    {
                        e4.printStackTrace();
                    }
                } catch (IOException e4)
                {
                    e4.printStackTrace();
                }
                }
            }
        }
        else if (account == 2)
        {
            String csvFile = "savingsaccount.csv";
            String csvFile2 = "auto.csv";
            String line;
            
            try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 0 && parts[0].equals(searchValue)) {
                    if (parts.length > 1) {
                        System.out.println("Savings Account Balance: $" + parts[1]);
                        pay.setCheckingBalance(parts[1]);
                    } else {
                        System.out.println("Savings Account Balance: No balance found.");
                    }
                }
            }
        } catch (IOException e) 
        {
            e.printStackTrace();
        }
            try (BufferedReader br2 = new BufferedReader(new FileReader(csvFile2)))
            {
                while ((line = br2.readLine()) != null)
                {
                    String[] parts2 = line.split(",");
                    if (parts2.length > 0 && parts2[0].equals(searchValue))
                    {
                        if (parts2.length > 1)
                        {
                            System.out.println("Payment Due: $" + parts2[1]);
                            System.out.println("Total Payment Remaining: $" + parts2[3]);
                            pay.setPaymentDue(parts2[1]);
                            pay.setTotalDue(parts2[3]);
                        }
                        else
                        {
                            System.out.println("Payment Due: none");
                        }
                    }
                }
            } catch (IOException e2)
            {
                e2.printStackTrace();
            }
            
            int pay2 = 0;
            System.out.println("Would you like to pay this amount now? Enter '1' if you would, and '2' if you would not.");
            pay2 = kb.nextInt();
            if (pay2 == 1)
            {
                double cbal = Double.parseDouble(pay.getCheckingBalance().trim());
                double monpay = Double.parseDouble(pay.getPaymentDue().trim());
                double total = Double.parseDouble(pay.getTotalDue().trim());
                cbal = cbal - monpay;
                total = total - monpay;
                if (cbal < monpay)
                {
                    System.out.println("Balance not sufficient to make payment.");
                    System.out.println("Return with the correct amount at the time of the next payment.");
                }
                else
                {
                String cbal2 = String.valueOf(cbal);
                String total2 = String.valueOf(total);
                System.out.println("\nPayment Complete:");
                System.out.println("Savings Account Balance: $" + cbal);
                System.out.println("Total Amount Left to Pay: $" + total);
                
                String csvFile3 = "savingsaccount.csv";
                try (BufferedReader br3 = new BufferedReader(new FileReader(csvFile3)))
                {
                    StringBuilder content = new StringBuilder();
                    String line3;
                    boolean found = false;
                    
                    while ((line3 = br3.readLine()) != null)
                    {
                        String[] parts3 = line3.split(",");
                        if (parts3.length > 0 && parts3[0].equals(searchValue))
                        {
                            line3 = searchValue + "," + cbal2;
                            found = true;
                        }
                        content.append(line3).append("\n");
                    }
                    
                    try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile3)))
                    {
                        bw.write(content.toString());
                    } catch (IOException e3)
                    {
                        e3.printStackTrace();
                    }
                } catch (IOException e3)
                {
                    e3.printStackTrace();
                }
                
                String csvFile4 = "auto.csv";
                try (BufferedReader br4 = new BufferedReader(new FileReader(csvFile4)))
                {
                    StringBuilder content2 = new StringBuilder();
                    String line4;
                    boolean found2 = false;
                    
                    while ((line4 = br4.readLine()) != null)
                    {
                        String[] parts4 = line4.split(",");
                        if (parts4.length > 0 && parts4[0].equals(searchValue))
                        {
                            if (parts4.length > 2)
                            {
                                parts4[3] = total2;
                                line4 = String.join(",", parts4);
                                found2 = true; 
                            }
                        }
                        content2.append(line4).append("\n");
                    }
                    
                    try (BufferedWriter bw2 = new BufferedWriter(new FileWriter(csvFile4)))
                    {
                        bw2.write(content2.toString());
                    } catch (IOException e4)
                    {
                        e4.printStackTrace();
                    }
                } catch (IOException e4)
                {
                    e4.printStackTrace();
                }
            }
            }
        }
    }
}