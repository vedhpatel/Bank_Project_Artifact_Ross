import java.util.ArrayList;
import java.util.Scanner;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.io.IOException;
import java.lang.Math;
import java.text.ParseException;

@SuppressWarnings("all")
public class Withdraw {
    public static double balance = 0.0;

    public void withdraw(String number) throws IOException
    {
        DebitCard obj = new DebitCard();
        Deposit dep = new Deposit();
        MainMenu main = new MainMenu();
        ADebitCardInfoGenerator card = new ADebitCardInfoGenerator();
        DebitCardExpirationDateGenerator exp = new DebitCardExpirationDateGenerator();
        double bal = 0.0;
        int fin = 0;
        
		while (fin == 0)
		   {
        Scanner omar = new Scanner(System.in);
        
        
        
        System.out.println("How much would you like to withdraw");
        //System.out.println(obj.getBalance());
            
            double money = omar.nextDouble();
            
          

            System.out.println("Are you sure you want to Withdraw $" + money + "?");
            
            String e = omar.nextLine();
            e = omar.nextLine();
            if (e.equals("yes")||e.equals("Yes"))
     {
         System.out.println("Thank you for Withdrawing your Money! Would you like to Deposit Money?");
         //String DaScore = Double.toString(obj.getBalance());
                //String fire = exp.getExpiration();
                //System.out.println(card.getNumber());
                //System.out.println(DaScore);
                //System.out.println(card.getCVC());
                //System.out.println(fire);
         bal = bal + money;
         balance = bal;
         subsavings(number,money, "dcard.csv" );
         //System.out.println(obj.getBalance());
         
     String yea = omar.nextLine();
     if (yea.equals("yes")||yea.equals("Yes"))
     {
         fin++;
         //System.out.println(obj.getBalance());
         dep.deposit(number);
         
         
     }
     else if (yea.equals("no")||yea.equals("No"))
     {
        try {
            main.showMainMenu("1234");
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (ParseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
         //fin++;
         
     }
     else if (yea != "yes" || yea != "no"||yea != "Yes" || yea != "No")
     {
         System.out.println("Please Enter a Valid Response");
         
         
     }
         
         fin++;
     }
     else if(e.equals("no")||e.equals("No"))
     {
        System.out.println(""); 
     }
     else if(e != "yes" || e != "no"||e != "Yes" || e != "No")
     {
         System.out.println("Please Enter A Valid Response");
     }
           else
    {
        System.out.println("Please enter in a value that you can withdraw.");
    }
         
            
            
    }    
    
        
	}   
    

    public double getBalance() {
        return balance;
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

    public void subsavings(String Numbers, Double Balance, String filename) throws IOException {
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
                    if (Balance >= Double.parseDouble(val2)) {
                        System.out.println("Previous transaction cancelled.Not enough funds in user balance.");
         
                    } else {
                        val2 = (Double.parseDouble(val2) - Balance) + "";

                    }

                }
                csvdata.add(new String[] { val1, val2, val3, val4 });
                String BB = val2;
            }

            reader.close();
            reader = null;

            writecsv(csvdata, filename);

        } catch (IOException e) {

            e.printStackTrace();
        }

    }
}