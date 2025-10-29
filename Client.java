package ATM;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Client {
	
	static String sal;
	static double p;
	static String o;
	static String number;
    static String [] valuesS;
    static String [] valuesC;
    static String [] valuesPass;
    static String intial;
    static String intialc;
    static double intialbalance;
    static double intialbalances;
    static int userChoice;
    static String Date;
    static double Eposit;
    static int num;
    static String currency;
    static String pass;
    static String id;
    static String ID;
    static String Type;
    
    
    //Begin
	public static void main(String[] args) throws IOException { 
		
		Pass();
	}
	
	//Retrieving password and ID
  	public static void Pass() throws IOException
    {
		 String Pa = "C:\\Users\\krustamova964\\Desktop\\ATM\\src\\passwords.csv";
	
		 // Reads passwords csv 
		 readPass(Pa);
	 	
	 	 pass = valuesPass[valuesPass.length -1];
	 	id = valuesPass[valuesPass.length -2];
	 	ID = id.substring(4);
	 	Sign();
	 	
	 	
    }
	
  	//Chose type of account
	public static void Sign() throws IOException {
		
		 LocalDate ny = LocalDate.now(); // Create a date object 
	       Date = ny.toString(); 
	       
	       Scanner myObj = new Scanner(System.in);
		
	       
		//User chooses which account to use
		
	    System.out.print(" Choose 1 - Savings account, choose 2 - Checkings Account");
	    userChoice = myObj.nextInt();
	    
	 
	    if (userChoice == 1) {
	    	FindSavings();
	    	
	    	ATM2.SignIn(userChoice, pass, ID, intialbalances, intialbalance); 
	    }
	    
	    else if (userChoice == 2) {
	    	FindChecking();
		    ATM2.SignIn(userChoice, pass, ID, intialbalances, intialbalance); 
		    }
	    
	    else 
	    {
	    	 System.out.print("Error, try again!");
	    	 Sign();
	    }
	 } 
	
	    
	
	
	
	//Writes csv for savings account 
    public static void CatchS(Double Deposit,int number)
    {
	 String Path = "C:\\Users\\krustamova964\\Desktop\\ATM\\src\\savingsaccount.csv";
	 
	 num = number;
	 Eposit = Deposit;
	 double deposit = Deposit+intialbalances;
	 	
  	 //Transforms int salary into a String
  	 sal = String.valueOf(deposit);
  	
  	 System.out.print(sal + " is the new balance "); 
  	 writeRowS(Path);
 	 
  	 CSV();
 	 }
 	 
 	 

 	//Writes  csv for checking account 
     public static void CatchC(Double Deposit, int number)
     {
 	 String Path = "C:\\Users\\krustamova964\\Desktop\\Pay\\src\\Pay\\checkingsaccount.csv";
 	 
 	 num = number;
 	 Eposit = Deposit;
 	 double deposit = Deposit+intialbalance;

 	 //Transforms int salary into a String
 	 sal = String.valueOf(deposit);
 
 	 System.out.print(sal + " is the new balance "); 
 	 writeRowC(Path);
 	 
 	 CSV();
    }
     


     //Writes ATM CSV
    private static void CSV() {
    	String Path = "C:\\Users\\krustamova964\\Desktop\\ATM\\src\\ATM.csv";
		
    	writeCSV(Path);
    	
	}

    
    //WRITES ATM CSV
    public static void writeCSV(String fileName)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) //open the file with a second paramet thats true
        {
        	
        	if (num >1) 
        	{
        		if (num ==1)
        		{
        			currency = "SAR";
        		}
        		if (num ==2)
        		{
        			currency = "EUR";
        		}
        		if (num ==3)
        		{
        			
        			currency = "GBP";
        		}
        		if (num ==4)
        		{
        			currency = "CAD";
        		}
        		if (num ==5)
        		{
        			currency = "INDR";
        		}
        		
        	}
        	
        	else 
        	{
        		currency = "USD";
        	}
        	
        	 if (userChoice == 1) {
        		 Type = "Savings account";
     	    }
     	    
     	    else {
     	    	Type = "Checking account";
     		    }
        	
            // Writing data
            //technically each element in the array below should be data that the user inputs and is gotten through the Scanner
            String[] data = {Date + "," + Eposit + "," + currency + "," + id + "," + Type}; //have each piece of data in the row in an array
            String line = String.join(", ", data); //put each object in the array into one line (mind the space after the comma)
            writer.write(line); //write line
            writer.newLine();

            //System.out.println("Data has been written to " + fileName);
        } catch (IOException e)
        {
            e.printStackTrace();
        }    
   }



	//Updates CHECKINGS account csv 
    public static void writeRowC(String fileName)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) //open the file with a second paramet thats true
        {
        	
            // Writing data
            //technically each element in the array below should be data that the user inputs and is gotten through the Scanner
            String[] data = {id + "," + sal}; //have each piece of data in the row in an array
            String line = String.join(", ", data); //put each object in the array into one line (mind the space after the comma)
            writer.write(line); //write line
            writer.newLine();

            //System.out.println("Data has been written to " + fileName);
        } catch (IOException e)
        {
            e.printStackTrace();
        }    
   }
    
    
    //Updates SAVINGS account csv 
    public static void writeRowS(String fileName)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) //open the file with a second paramet thats true
        {
        	
            // Writing data
            //technically each element in the array below should be data that the user inputs and is gotten through the Scanner
            String[] data = {id + "," + sal}; //have each piece of data in the row in an array
            String line = String.join(", ", data); //put each object in the array into one line (mind the space after the comma)
            writer.write(line); //write line
            writer.newLine();

            //System.out.println("Data has been written to " + fileName);
        } catch (IOException e)
        {
            e.printStackTrace();
        }    
   }
    
    
    //Read Savings account csv
    public static void readCSVS(String fileName)
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName)))
        {
            String line;
          //  System.out.println("\nReading data from " + fileName + ":");
            while ((line = reader.readLine()) != null)
            {
            	valuesS = line.split(",");  
            }
            
        } catch (IOException e)
        {
            e.printStackTrace();
        }
      }   
    
    
    //Read CHECKING account csv
    public static void readCSVC(String fileName)
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName)))
        {
            String line;
          //  System.out.println("\nReading data from " + fileName + ":");
            while ((line = reader.readLine()) != null)
            {
            	valuesC = line.split(",");  
            }
            
        } catch (IOException e)
        {
            e.printStackTrace();
        }
      }   
    
    
  //Assignees values from Checking account
  	 public static void FindChecking()
  	    {
  		 String Path = "C:\\Users\\krustamova964\\Desktop\\Pay\\src\\Pay\\checkingsaccount.csv";
  		 
  		// Reads CHECKING ACCOUNT 
  		 	readCSVC(Path);
  		 	
  		 	intial = valuesC[valuesC.length - 1];
  		 	
  		 	 try { 
  		 		intialbalance = Double.valueOf(intial); 
  		 	 	 
  		 	 	 }
  		 	 	 catch (NumberFormatException e)
  		 	 	 {
  		 	 		 System.out.print("   ");
  		 	 	 }
  	    }
  	 
  	 
  	 //Assignees values from Savings account
  	 public static void FindSavings()
  	    {
  		 String Pat = "C:\\Users\\krustamova964\\Desktop\\ATM\\src\\savingsaccount.csv";
  		 
  		// Reads SAVINGS ACCOUNT 
  		 	readCSVS(Pat);
  		 	
  		 	intialc = valuesS[valuesS.length -1];
  		  try { 
		 		intialbalances = Double.valueOf(intialc); 
		 	 	 
		 	 	 }
		 	 	 catch (NumberFormatException e)
		 	 	 {
		 	 		 System.out.print("Invalid integer input");
		 	 	 }
  	    }
  	 
  	 
 
  	//Reads Password
  	public static void readPass(String fileName)
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName)))
        {
            String line;
          //  System.out.println("\nReading data from " + fileName + ":");
            while ((line = reader.readLine()) != null)
            {
            	valuesPass = line.split(",");  
            }
            
        } catch (IOException e)
        {
            e.printStackTrace();
        }
      }   
  	
	  }
    
    
	