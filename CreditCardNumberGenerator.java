import java.util.ArrayList;
import java.util.Random; 
import java.util.Scanner;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Math;
import java.time.LocalDate;
@SuppressWarnings("all")

public class CreditCardNumberGenerator

{
    private static String Number;
    private static String CVC;
	public void cardnumber() {
	 Scanner account = new Scanner(System.in);
	 CreditScore sco = new CreditScore();
	 CreditCardExpirationDateGenerator exp = new CreditCardExpirationDateGenerator();
     BankAccountSignup MyObj = new BankAccountSignup();
	 // Create a Scanner object
    System.out.println("Are you an existing user? Type either yes or no:");
    String response = account.nextLine();
    //no to being an existing user
    if (response.equals("no")) 
    {
        System.out.println("Would you like a credit card? Type either yes or no" );
        String responseCC = account.nextLine();
        if (responseCC.equals("no"))
        {
            System.out.println("Have a Good Day!");
            System. exit(0);
        }
        if (responseCC.equals("yes")) {    
        System.out.println("Do you know your credit score?"); 
        String Dylan = account.nextLine(); 
        if (Dylan.equals("no")) {
            sco.creditscore("");
        }
        if (Dylan.equals("yes")) {
        System.out.println("What is your credit score?");
        int responseCCscore = account.nextInt(); 
        String respond = Integer.toString(responseCCscore);
        
        
        

            
            
            
            if (responseCCscore >= 660 && responseCCscore <= 850) {
                System.out.println("Okay, you qualify for a Credit Card."); 
                System.out.println("Press 3 for American Express, 4 for Visa, 5 for Master Card, and 6 for Discover. "); 
                int responseCardTypes = account.nextInt();
                
                
                
                
            
                if (responseCardTypes == 3){
                    //include the credit card info generator for American Express
                String cvcAE = "";
                for(int i = 0; i < 3; i++) {
                int number = (int)(Math.random() * 9);
                String numberAEstring = Integer.toString(number);
                cvcAE += number + "";
                //include the number generator for a new card 
                
                }
                String cardNumberAE = ""; 
                String Three = "3"; 
                for (int j = 0; j < 15; j++) {
                int cardNumberAETwo = (int)(Math.random() * 9); 
                String cardNumberAEThree = Integer.toString(cardNumberAETwo); 
                cardNumberAE = Three += cardNumberAEThree + ""; 
                
                }
                
                 System.out.println("Your card number for American Express is: 3" + cardNumberAE); 
                 System.out.println("Your cvc code is: " + cvcAE);
                 System.out.println("Generated Expiration Date:  " + exp.Expiration());
                 Number = cardNumberAE;
                 CVC = cvcAE;
   
      
                }
                
                
                
                if (responseCardTypes == 4) {
                    //include the credit card info generator for Visa. 
                String cvcVS = "";
                for(int i = 0; i < 3; i++) {
                int number = (int)(Math.random() * 9);
                String numberVSstring = Integer.toString(number);
                cvcVS += number + "";
                //include the number generator for a new card. 
            }
            String cardNumberVS = ""; 
            String Four = "4";
                for (int j = 0; j < 15; j++) {
                int cardNumberVSTwo = (int)(Math.random() * 9); 
                String cardNumberVSThree = Integer.toString(cardNumberVSTwo); 
                cardNumberVS = Four += cardNumberVSThree + ""; 
                
                
                }
                //include the EXP DATE generator for a new card. 
            System.out.println("Your card number for Visa is: " + cardNumberVS); 
            System.out.println("Your cvc code for visa is: " + cvcVS); 
            System.out.println("Generated Expiration Date:  " + exp.Expiration());
            Number = cardNumberVS;
            CVC = cvcVS;
                }
                
            
            
            
                if (responseCardTypes == 5) {
                    
                    //include the credit card info generator for Master Card. 
                String cvcMS = "";
                for(int i = 0; i < 3; i++) {
                int number = (int)(Math.random() * 9);
                String numberMSstring = Integer.toString(number);
                cvcMS += number + "";
                
                }
                    //include the number generator for Master Card.  
                String cardNumberMS = ""; 
                String Five = "5"; 
                for (int j = 0; j < 15; j++) {
                int cardNumberMSTwo = (int)(Math.random() * 9); 
                String cardNumberMSThree = Integer.toString(cardNumberMSTwo); 
                cardNumberMS = Five += cardNumberMSThree + ""; 
                
                }
                    //include the EXP DATE generator for a new card. 
                
                System.out.println("Your card number for Master Card is: " + cardNumberMS); 
                System.out.println("Your cvc code for Master Card is: " + cvcMS); 
                System.out.println("Generated Expiration Date:  " + exp.Expiration());
                Number = cardNumberMS;
                CVC = cvcMS;
                }
                
                
                
                
                
                if (responseCardTypes == 6) {
                    //include the credit card info for Discover. 
                String cvcDV = "";
                for(int i = 0; i < 3; i++) {
                int number = (int)(Math.random() * 9);
                String numberDVstring = Integer.toString(number);
                cvcDV += number + "";  
                
                }
                //Include the number generator for Discover. 
                String cardNumberDV = ""; 
                
                
                for (int j = 0; j < 15; j++) {
                int cardNumberDVTwo = (int)(Math.random() * 9); 
                String cardNumberDVThree = Integer.toString(cardNumberDVTwo); 
                cardNumberDV += cardNumberDVThree + "";
                //****changes were made here and below, change them later 
                
                }
                String Six = "6"; 
                String cardNumberDVFinal = Six += cardNumberDV; 
                //Include the EXP DATE for Discover. 
     System.out.println("Your card number for Discover is: " + cardNumberDVFinal);
     System.out.println("Your cvc code for Discover is " + cvcDV); 
     System.out.println("Generated Expiration Date:  " + exp.Expiration());
     Number = cardNumberDVFinal;
     CVC = cvcDV;
     //**changes end here
                }
             if (responseCCscore < 660 && responseCCscore > 0) {
                System.out.println("Sorry, but your credit score is too low. Please try again again later."); 
            }
            else if (responseCCscore < 0) {
                System.out.println("Sorry, but you cannot have a negative score. Please re-enter your credit score now."); 
            }
            
            }
            if (responseCC.equals("no")) {
                System.out.println("Okay, exiting now. Goodbye!"); 
            }
   
    
        }
        try {
            adduser(Number, "0", "0", exp.Expiration(), CVC, "ccard.csv");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        
    //yes to being an existing user
    
	}
    
	if(response.equals("yes"))
    {
        
        System.out.println("Please enter your account number now to access your cards:");
        String responseExistingAccount = account.nextLine();
    }
}
    
//public double get1()
//{
    //return a;
//}
}
static BankAccountSignup.BankAccount MyObj = new BankAccountSignup.BankAccount();
private static String UserID = MyObj.getAccountId();

    public static void adduser(String CardNumber, String CreditScore, String CreditLimit, String ExpirationDate, String CVC, String filename) throws IOException {
        Scanner keyboard = new Scanner(System.in);

        try {
            CsvReader reader = new CsvReader(filename);

            int index = 0;
            ArrayList csvdata = new ArrayList();

            while (reader.readRecord()) {
                index++;

                String val1 = reader.get(0);
                String val2 = reader.get(1);
                String val3 = reader.get(2);
                String val4 = reader.get(3);
                String val5 = reader.get(4);
                String val6 = reader.get(5);

            }

            reader.close();
            reader = null;

            csvdata.add(new String[] { CardNumber, "0", "0", ExpirationDate, CVC, UserID });
            writecsv2ElectricBoogaloo(csvdata, filename);
            return;

        } catch (IOException e) {

            e.printStackTrace();
        }

    }
    public static void writecsv(ArrayList<String[]> csvdata, String filename) throws IOException {
        CsvWriter writer = new CsvWriter(filename);

        for (String[] record : csvdata) {

            writer.writeRecord(record);
            writer.flush();

        }
        writer.close();
        writer = null;
    }

    public static void writecsv2ElectricBoogaloo(ArrayList<String[]> csvdata, String filename) throws IOException {
        FileWriter outputfile = new FileWriter(filename, true);
        CsvWriter writer = new CsvWriter(outputfile, ',');

        for (String[] record : csvdata) {

            writer.writeRecord(record);
            writer.flush();

        }
        writer.close();
        writer = null;
    }

    public String GetID() {
        return UserID;
    }

    public String GetCard() {
        return Number;
    }

    public String SetID(String newID) 
    {
        return this.UserID = newID;
    }
public static int searchCsvLine(int searchColumnIndex, String searchString) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("ccard.csv"));
        String line;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            if (values[searchColumnIndex].equals(searchString)) {
                return 1;
            }

        }
        br.close();

        return 0;

    }
}