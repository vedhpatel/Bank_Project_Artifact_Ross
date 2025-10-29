import java.util.ArrayList;
import java.util.Scanner;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.io.IOException;
import java.lang.Math;
@SuppressWarnings("all")

public class CreditLimit
  {
	public static String flimit;
	private double limit = 500.0;
	private double spent;
	private double owed = limit - spent;
	CMain obj = new CMain();

	public CreditLimit (double credlim, double credsp)
	{
	  double limit = credlim;
	  double spent = credsp;
	}

	public void creditlimit (String account)
	{
	    Scanner scan = new Scanner(System.in);
	    System.out.println("Would you like to Increase your Credit Limit?");
	    
	    String response = scan.nextLine();
	    
	    if (response.equals("yes"))
	    {
	        System.out.println("How much would you like to increase your Credit Limit by?");
	        System.out.println("Maximum is 25%, Minimum is 10%");
	        
	        double percent = scan.nextInt();
	        
	        if (percent > 25)
	        {
	            System.out.println("Unfortunately we cannot increase it that much");
	            
	        }
	        if (percent < 10)
	        {
	            System.out.println("Unfortunately we cannot increase it that little");
	        }
	        
	        
	        
	        System.out.println("What is your Credit Score?");
	        
	        int score = scan.nextInt();
	        
	        if (score >=670 && score <= 850)
	        {
	            if ( percent >= 10 && percent <= 25);
	        {
	            double newcred = limit + (percent/100 * limit);
	            limit = newcred;
	            System.out.println("Your new Credit Limit is: " + newcred);
	            flimit = Double.toString(newcred);
				try {
					addsavings(account, flimit, "ccard.csv");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

	        }
	            
	        }
	        if (score < 700)
	        {
	            System.out.println("Unfortunately that is too low of a Credit Score");
	        }
	        if (score > 850)
	        {
	            System.out.println("That number is invalid as it is above the maximum Credit Score");
	        }
	        
	        
	        
	        //if (j > 2)
	        //{
	            //System.out.println("You can only update this twice per month");
	        //}
	        
	        
	    }
	  //if (response.equals("no"))
	  //{
	      //return back to the main menu 
	  //}
	  
	  
	  
	  //if (limit < spent)
		//{
		  //System.out.println ("Insufficient Funds, Amount Over the Limit");
            //fee is $35, drop credit score as well
		//}
		//else
		//{
		  //System.out.println("Transaction completed");
		//}
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
	public static void addsavings(String CardNumber,String CreditLimit, String filename) throws IOException {
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

                if (val1.equals(CardNumber)) {
                    //val2 = (Double.parseDouble(val2) + Balance) + "";
					val3 = (Double.parseDouble(val3) + flimit);
                }
                csvdata.add(new String[] { val1, val2, val3, val4, val5, val6 });
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


    
    
    
    
    
    
    
    
    
    
    
    
    
    
