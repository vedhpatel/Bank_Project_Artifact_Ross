import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.Reader;
import java.text.DecimalFormat;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;


public class commercialloan
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
        String path = "commercial.csv";
        try 
        {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
            while (true) 
            {
                if (reader.readLine() == null)
                {
                    writer.write(String.valueOf(person.getAccNumber()) + ", ");
                    writer.write(String.valueOf(df.format(person.getLoanAmount())) + ", ");
                    writer.write(String.valueOf(df.format(person.getMonthlyPayment())) + ", ");
                    writer.write(String.valueOf(df.format(person.getInterest())));
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
    
    public static void read2CSV() 
     {
         info person = new info();
        String filePath = "commercial.csv";

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
                if (counter == 20) 
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
            
            System.out.println("You already have a commercial loan.");
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
    
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private static final DecimalFormat df2 = new DecimalFormat("0");
    
    
    
    public static void loanApp()
    {
        info person = new info();
        info2 person2 = new info2();
        Scanner kc = new Scanner(System.in);
       
        System.out.println("Please confirm your Credit Card Number below: ");
        String searchValue = kc.nextLine();
        
        System.out.println("Please confirm your Account Number below: ");
        String accnum = kc.nextLine();
        person.setAccNumber(accnum);
        
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
        ArrayList <Double> pay = new ArrayList <Double>();
        
        Scanner kb = new Scanner(System.in);
        double noi = 0;
        double tds = 0;
        double percent = 0;
        double appraisal = 0;
        double dscr = 0;
        double down = 0;
        int loanlength = 0;
        
        System.out.println(" *** WELCOME TO THE COMMERICAL LOAN APPLICATION PAGE *** ");
        
        System.out.println("To apply for a commercial loan, you will need the following: the net operation income (NOI), annual total debt service, \n the percentage of the building you will be owning," + 
        "and a third party appraisal. If you have this information, type 'yes' or 'no'.");
        String choice = kb.nextLine();
        
        if (choice.equalsIgnoreCase("yes"))
        {
            
           System.out.println(" Please enter the net operation income (NOI), do not use $ sign: ");
           noi = kb.nextDouble();
           
           System.out.println("What is the annual total debt service of you business, not including this loan (do not use $ sign): ");
           tds = kb.nextDouble();
           
           System.out.println("What percentage of the bulding will you be owning? (do not use $ sign): ");
           percent = kb.nextDouble();
           
           System.out.println("What was the third party appraisal? (do not use $ sign): ");
           appraisal = kb.nextDouble();
           person.setLoanAmount(appraisal);
           
           System.out.println("How long will the loan be for? Choose between 5 to 20 years.");
           loanlength = kb.nextInt();
           person.setLoanLength(loanlength);
           for (int i = 1; i != 0; i++)
           {
               if (loanlength >= 5 && loanlength <= 20)
               {
                   break;
               }
               else
               {
                   System.out.println("Please enter a valid loan length. From 5 to 20 years. Full years only.");
                   loanlength = kb.nextInt();
                   person.setLoanLength(loanlength);
               }
           }
           
           pay.add(appraisal);
           
           dscr = noi/tds;
           down = appraisal * 0.25;
           
           double partofrate = appraisal * 0.065;
           double interest = 6.50;
           person.setInterest(interest);
           
           double monthlypayment = 0.0;
           monthlypayment = ((appraisal / loanlength) * ((interest / 100) + 1)) / 12;
           person.setMonthlyPayment(monthlypayment);
           
           write2CSV(person);
           if (dscr > 1.25 && percent >= 50 && cscore >= 650)
           {
               
               System.out.println("\n Your loan will be worth the appraisal price, which is $" + df.format(appraisal) + ".");
               System.out.println("Your interest rate will be " + df.format(person.getInterest()) + "%.");
               System.out.println(" ");
           }
           else
           {
               System.out.println("Your information did not meet the requirements for a commercial loan.");  
           }
           
        }
        else
        {
            System.out.println("You must have this information to apply for a commercial loan.");
            System.out.println("If this information is not given, you will be DECLINED a commercial loan.");
        }
    }
    
    public static void payment()
    {
        Scanner kb = new Scanner(System.in);
        payment pay = new payment();
        int account = 0;
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
            String csvFile2 = "commercial.csv";
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
                            System.out.println("Payment Due: $" + parts2[2]);
                            System.out.println("Total Payment Remaining: $" + parts2[1]);
                            pay.setPaymentDue(parts2[2]);
                            pay.setTotalDue(parts2[1]);
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
                System.out.println("Checking Account Balance: $" + cbal2);
                System.out.println("Total Amount Left to Pay: $" + total2);
                
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
                
                String csvFile4 = "commercial.csv";
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
                            if (parts4.length > 0)
                            {
                                parts4[1] = total2;
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
            String csvFile2 = "commercial.csv";
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
                            System.out.println("Payment Due: $" + parts2[2]);
                            System.out.println("Total Payment Remaining: $" + parts2[1]);
                            pay.setPaymentDue(parts2[2]);
                            pay.setTotalDue(parts2[1]);
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
                
                String csvFile4 = "commercial.csv";
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