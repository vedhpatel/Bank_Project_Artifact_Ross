import java.util.Scanner;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.util.Random; 
import java.lang.Math;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;  
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.util.GregorianCalendar;
@SuppressWarnings("all")
public class DebitCard
{
    
	public static void main(String[] args) throws IOException {
	    Scanner res = new Scanner(System.in);
	    Deposit dep = new Deposit();
	    Withdraw wid = new Withdraw();
	    ADebitCardInfoGenerator gen = new ADebitCardInfoGenerator();
	    int infinite = 0;
	    int k = 0; 
	    
	    String file = "dcard.csv";
        
    // try
    //         {
    //             //3585869483921986
    //             //4968493029185647
    //             modifyCSV("5555555555111115", 500.0, "409", "2/22/07", 0, 0); //RESEARCH HOW THIS CAN BE AUTOMATED VIA USER INPUT RATHER THAN HARD CODED
    //             modifyCSV("5543534535511111", 1000.0, "510", "1/31/07", 0, 0); //RESEARCH HOW THIS CAN BE AUTOMATED VIA USER INPUT RATHER THAN HARD CODED
    //         } catch(IOException e) 
    //         {
    //             e.printStackTrace();
    //         }
    //         writeRow("dcard.csv");
    //         //readCSV("dcard.csv"); 
	    
	    
	    //collab with checking account for balance
        while (infinite == 0)
    {
       
        System.out.println("Welcome to the Debit Card section!"); 
		System.out.println("Do you own a Debit Card?");
		
		String response = res.nextLine();
		
		if (response.equals("yes"))
		{
		    infinite++;
		    while (infinite == 1)
		    {
		    
		    
		    System.out.println("Please Enter in a Valid Debit Card Number:");
		    //do the csv reader thing

		    String smallkat = res.nextLine();

             if (searchCsvLine(0,smallkat) == 1 )
             {


		    infinite++;
		    while (infinite == 2)
		    {
		    System.out.println("Would you like to Deposit Money or Withdraw Money (Enter Deposit or Withdraw)");
		    String DW = res.nextLine();
		    if (DW.equals("Deposit")||DW.equals("deposit"))
		    {
		        dep.deposit(smallkat);
		        break;
		    }
		    else if (DW.equals("Withdraw")||DW.equals("withdraw"))
		    {
		        wid.withdraw(smallkat);
		        break;
		    }
		    else if (DW != "Deposit" || DW != "Withdraw"||DW!="deposit"|| DW != "withdraw")
		    {
		        System.out.println("Please Enter a Valid Reponse");
		    }
		    
                
             }  


		    }
            else {
                System.out.println("No such account found");
                     continue;
            }
		    
		    }
		}
		else if (response.equals("no")||response.equals("No"))
		{
		    gen.gen();
		     
		    
		}
		else if (response != "yes"||response !="no"||response != "Yes"||response !="No")
		{
		    System.out.println("Please Type in a Valid Answer");
		}
    }
		
	}
	public double getBalance()
    {
        Deposit dep = new Deposit();
	    Withdraw wid = new Withdraw();
	    double ball = 0;
	    
        double balance = ball + dep.getBalance() - wid.getBalance();
        return balance;
    }
    


    
    
      public static int searchCsvLine(int searchColumnIndex, String searchString) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("dcard.csv"));
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
    
    
    
    
    
    // public static void writeCSV(String fileName)
	// {
	//     try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName)))
	//     {
	//         writer.write("Numbers, Balance, CVC, Expiration");
	//         writer.newLine();
	//         System.out.println("Data has been written to " + fileName);
	//     }
	//     catch (IOException e)
	//     {
    //         e.printStackTrace();
    //     }
	// }
	// public static void readCSV(String fileName)
    //     {
    //         try (BufferedReader reader = new BufferedReader(new FileReader(fileName)))
    //         {
    //             String line;
    //             System.out.println("\nReading data from " + fileName + ":");
    //             while ((line = reader.readLine()) != null)
    //             {
    //                 System.out.println(line);
    //             }
    //         } catch (IOException e)
    //         {
    //             e.printStackTrace();
    //         }
    //     }
    //     public static void modifyCSV(String Numbers, Double Balance, String CVC, String Expiration, int row, int col) throws IOException
    //     {
    //         DebitCard obj = new DebitCard();
    //         String str = new Double(obj.getBalance()).toString();
    //         File inputFile = new File("dcard.csv");
    //         List<String> fileContent = new ArrayList<>();
    //         String card_number = "12345";
    //         ADebitCardInfoGenerator card = new ADebitCardInfoGenerator();
            
    //         try (BufferedReader reader = new BufferedReader(new FileReader(inputFile)))
    //         {
    //             String balance;
    //             String tempLine;
    //             int tempRow = 0;
                
    //             while ((tempLine = reader.readLine()) != null)
    //             {
    //                 String[] newTempLine = tempLine.split(",\\s*");
    //                 if (card_number.equals(newTempLine[0]))
    //                 {
    //                     System.out.println("Card Number: " + card.getNumber());
    //                     balance = newTempLine[0];
    //                     System.out.println(balance);
    //                 }
    //                 fileContent.add(tempLine);
    //                 tempRow++;
    //             }
                
    //         }
    //         System.out.println();
    //     }

    
    // public static void writeRow(String fileName)
    //     {
    //         try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true)))
    //         {
    //             //3585869483921986
    //             DebitCard obj = new DebitCard();
    //             ADebitCardInfoGenerator card = new ADebitCardInfoGenerator();
    //             DebitCardExpirationDateGenerator exp = new DebitCardExpirationDateGenerator();
    //             String[] data = {"3585869483921986", "770", "1000", "2/22/30",};
    //             //String[] data = {"0", "1", "2", "3", "4"};
                
    //             String DaScore = Double.toString(obj.getBalance());
    //             String fire = exp.getExpiration();
    //             //System.out.println(card.getNumber());
    //             //System.out.println(DaScore);
    //             //System.out.println(card.getCVC());
    //             //System.out.println(fire);
    //             data[0] = card.getNumber();
    //             data[1] = DaScore;
    //             data[2] = card.getCVC();
    //             data[3] = fire;
    //             String line = String.join(", ", data);
    //             writer.write(line);
    //             writer.newLine();
                
    //             //System.out.println("Data has been written to " + fileName);
    //         }
    //         catch (IOException e)
    //         {
    //             e.printStackTrace();
    //         }
    //     }

      

    
    public static void writecsv(ArrayList<String[]> csvdata, String filename) throws IOException {
        CsvWriter writer = new CsvWriter(filename);

        for (String[] record : csvdata) {

            writer.writeRecord(record);
            writer.flush();

        }
        writer.close();
        writer = null;
    }
    
    public static void subsavings(String Numbers, Double Balance, String filename) throws IOException {
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
                
                if (val1.equals(Numbers)) {
                    val2 = (Double.parseDouble(val2) - Balance) + "";
                    
                }
                csvdata.add(new String[] { val1, val2, val3, val4 });
            }

            reader.close();
            reader = null;

            writecsv(csvdata, filename);
            return;

        } catch (IOException e) {

            e.printStackTrace();
        }

    }
    
    
    
    
    
    
}