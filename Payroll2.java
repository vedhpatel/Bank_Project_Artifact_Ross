package Pay;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.io.FileReader;  


public class Payroll2 {
	static String [] values;
	static String sal;
	static String o;
	static double p;
	static String date;
	static String Date;
	static String Pay;
	static String Hours;
	static String number;
    static String [] valuesPass;
    static String id;
    static String ID;
	
		
	
	    public static void main(String args[]) throws IOException  
	    { 
	    	
	       LocalDate myObj = LocalDate.now(); // Create a date object 
	       Date = myObj.toString(); 
	       date = myObj.toString(); 
	       date = date.substring(8); 

	       if (date.equals("19") || date.equals("28")) 
	       { 
	    	   Pass();
	    	   Payroll.InitialInput();
	       } 

	       else  
	       { 
	           System.out.print("Please try entering the Payroll Software during your pay day "); 
	       } 
	    } 
	    
	  
	    
	    public static void Catch(Double salary, String hours)
        {
	    	
	      
	      String Path = "C:\\Users\\krustamova964\\Desktop\\Pay\\src\\Pay\\checkingsaccount.csv";
	      String pat = "C:\\Users\\krustamova964\\Desktop\\Pay\\src\\Pay\\Payroll.csv";	
	    	
	      
	         // CHECKING ACCOUNT CODES
	    	 readCSV(Path);
	    	
	    	 
	    	//Converts data read from csv to string to int
	    	 o = values[values.length - 1];
	    	 number = values[values.length - 2];
	    	 
	    	 try { 
	    	 p = Double.valueOf(o); 
	    	 
	    	 }
	    	 catch (NumberFormatException e)
	    	 {
	    		 System.out.print(" ");
	    	 }
	    	 
	    	 
	    	 //Adds salary to previous balance
	    	 salary = salary + p;
	    	
	    	 
	    	 //Transforms int salary into a String
	    	 sal = String.valueOf(salary);
	    	 System.out.print(sal + " is the new balance "); 
	    	 writeRow(Path);
	 
	    	 
	    	 //Transform into String
	    	 Hours = hours;
	    	 Pay = Double.toString(salary);

	    	 writeRowCSV(pat); 	 
        }
	    
	    
	    
	    public static void Pass() throws IOException
        {
    		 String Pa = "C:\\Users\\krustamova964\\Desktop\\ATM\\src\\passwords.csv";
    	
    		 // Reads passwords csv 
    		 readPass(Pa);
    	 	id = valuesPass[valuesPass.length -2];
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
	    
	  	
	    
	    public static void writeRow(String fileName)
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
	    
	    
	    
	    public static void readCSV(String fileName)
        {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName)))
            {
                String line;
              //  System.out.println("\nReading data from " + fileName + ":");
                while ((line = reader.readLine()) != null)
                {
                	values = line.split(",");  
                }
                
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }   
	    
	    
	    
	    public static void writeRowCSV(String fileName)
        {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) //open the file with a second paramet thats true
            {
            	
                // Writing data
                //technically each element in the array below should be data that the user inputs and is gotten through the Scanner
                String[] data = {id + "," + Date + "," + Hours + "," + Pay}; //have each piece of data in the row in an array
                String line = String.join(", ", data); //put each object in the array into one line (mind the space after the comma)
                writer.write(line); //write line
                writer.newLine();
 
                //System.out.println("Data has been written to " + fileName);
            } catch (IOException e)
            {
                e.printStackTrace();
            }    
       }
}