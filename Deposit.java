import java.util.Scanner;

import java.util.ArrayList;
import java.util.Scanner;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.io.IOException;
import java.lang.Math;
import java.text.ParseException;

@SuppressWarnings("all")

public class Deposit {
    DebitCard obj = new DebitCard();
    Withdraw wid = new Withdraw();
    MainMenu main = new MainMenu();
    public static double balance = 0.0;

    public void deposit(String account) throws IOException {
        int infin = 0;
        // double balance = 0.0;
        while (infin == 0) {
            System.out.println("How much would you like to Deposit?");
            // System.out.println(obj.getBalance());
            Scanner hi = new Scanner(System.in);

            double he = hi.nextDouble();
            if (he > 0)
            {
            System.out.println("Are you sure you would like to Deposit $" + he + "?");
            String help = hi.nextLine();
            help = hi.nextLine();
            if (help.equals("yes")||help.equals("Yes")) {
                System.out.println("Thank you for Depositing your Money! Would you like to Withdraw Money?");
        addsavings(account,he, "dcard.csv");
                // System.out.println(getBalance());

                String why = hi.nextLine();
                if (why.equals("yes")||why.equals("Yes")) {
                    infin++;
                    wid.withdraw(account);
                    break;

                } else if (why.equals("no")||why.equals("No")) {

                    try {
                        main.showMainMenu("1234");
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    //infin++;

                } else if (why != "yes" || why != "no"||why != "Yes" || why != "No") {
                    System.out.println("Please Enter a Valid Response");

                }
            } else if (help.equals("no")||help.equals("No")) {
                System.out.println("");
            } else if (help != "yes" || help != "no"||help != "Yes" || help != "No") {
                System.out.println("Please Enter A Valid Response");
            }
        }
        else
        {
            System.out.println("Please Enter in a valid statement");
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

    public static void addsavings(String Numbers, Double Balance, String filename) throws IOException {
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
                    val2 = (Double.parseDouble(val2) + Balance) + "";

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