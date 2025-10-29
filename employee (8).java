import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class employee
{
    public static void main(String args[])
    {
        boolean loggedIn = false;
        employeeLogin(loggedIn);
        if (loggedIn = true)
        {
        
        int count = 0;
        while(count == 0)
        {
            String iD = employeeWork();
            if (!iD.equals("false"))
            {

                
                CustomerInfo displayMeth = new CustomerInfo();
                
                System.out.println("Basic User Info");
                 System.out.println(" ");
                displayMeth.displayAny(iD, displayMeth.basicInfo);
                 System.out.println("Money Market Account User Info");
                 System.out.println(" ");
                displayMeth.displayAny(iD, displayMeth.mmaInfo);
                 System.out.println("Home Equity Account User Info");
                 System.out.println(" ");
                displayMeth.displayAny(iD, displayMeth.homeEquityInfo);
                System.out.println("Foreign Account User Info");
                 System.out.println(" ");
                displayMeth.displayAny(iD, displayMeth.foreignInfo);
                 System.out.println("Savings Account User Info");
                 System.out.println(" ");
                displayMeth.displayAny(iD, displayMeth.savingsInfo);
                  System.out.println("Credit Account User Info");
                 System.out.println(" ");
                displayMeth.displayAny(iD, displayMeth.creditInfo);
                  System.out.println("Transaction User Info");
                 System.out.println(" ");
                displayMeth.displayAny(iD, displayMeth.transInfo);
                  System.out.println("Mortgage Account User Info");
                 System.out.println(" ");
                displayMeth.displayAny(iD, displayMeth.mortInfo);
                  System.out.println("Annuity Investment Account User Info");
                 System.out.println(" ");
                displayMeth.displayAny(iD, diplayMeth.annInfo);
                System.out.println("Bonds Account User Info");
                 System.out.println(" ");
                displayMeth.displayAny(iD, displayMeth.bondInfo);
                   System.out.println("Checkings Account User Info");
                 System.out.println(" ");
                displayMeth.displayAny(iD, displayMeth.checkInfo);
                System.out.println("Debit Card User Info");
                System.out.println("");
                displayMeth.displayAny(iD, displayMeth.debitInfo);
                System.out.println("Payroll User Info");
                System.out.println("");
                displayMeth.displayAny(iD, displayMeth.payInfo);
            }
            }
        }
    }
    
    private static boolean employeeLogin(boolean loggedIn)
    {
        String password = "bankproject2024";
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Welcome to employee login.");
        System.out.println("Input the password: ");
        String passwordInput = keyboard.nextLine();
        while (!passwordInput.equals(password))
        {
            System.out.println("The password you entered is incorrect. Please enter the correct password.");
            passwordInput = keyboard.nextLine();
        }
        System.out.println("You have successfully logged into the employee account.");
        loggedIn = true;
        return loggedIn;
    }
    
    private static String employeeWork()
    {
        ArrayList<String> userIDs = readMainCSV("accounts.csv");

        int counter = 0;
        while(counter == 0)
        {
            String iD = userID();
            if (iD.equals("1"))
            {
            	BankAccountSignup.BankAccount MyID = new BankAccountSignup.BankAccount();
            	String CurrentID = MyID.getAccountId();
                BankMenu.showMainMenu(CurrentID);
            }
            
            for (int i = 0;i < userIDs.size();i++)
            {
                if (userIDs.get(i).equals(iD))
                {
           
                    return iD;
                }
            }
            System.out.println("The ID you entered does not exists. Please enter a valid ID.");
        }
        return "false";
    }
    
    private static ArrayList<String> readMainCSV(String fileName)
    {
        ArrayList<String> userIDs = new ArrayList<>();
        String line = "";
        String splitBy = ",";
            
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName)))
        {
            System.out.println("\nReading data from " + fileName + ":");
            while ((line = reader.readLine()) != null)
            {
                String[] user = line.split(splitBy);
                userIDs.add(user[0]);
            }
            
        }
                
        catch (IOException e)
        {
            e.printStackTrace();
        }
                
            return userIDs;
    }
    
    private static String userID()
    {
        System.out.println("Enter the ID for the account you would like to view or enter 1 to return to the main menu.");
        Scanner keyboard = new Scanner(System.in);
        String IDinput = keyboard.nextLine();
        return IDinput;
    }
    

}