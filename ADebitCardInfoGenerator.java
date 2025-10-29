import java.util.ArrayList;
import java.util.Scanner;
import java.text.DecimalFormat;
import java.io.*;
import java.lang.Math;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Objects;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;


@SuppressWarnings("all")

public class ADebitCardInfoGenerator {
    private static String Number;
    private static String CVC;
    DebitCardExpirationDateGenerator exp = new DebitCardExpirationDateGenerator();
    DebitCard obj = new DebitCard();

    public void gen() throws IOException {
        Scanner Debitscanner = new Scanner(System.in);

        System.out.println("We will begin with your card number and cvc code.");
        System.out.println("Please press 1 now to generate your information.");

        int infoNow = Debitscanner.nextInt();
        for (int i = 1; i != 0; i++) {

            if (infoNow == 1) {
                System.out.println("Press 3 for American Express, 4 for Visa, 5 for Mastercard, and 6 for Discover.");
                int DebitCardResponseTypes = Debitscanner.nextInt();

                if (DebitCardResponseTypes == 3) {
                    String DebitcvcAE = "";
                    for (int z = 0; z < 3; z++) {
                        int Debitnumber = (int) (Math.random() * 9);
                        String DebitnumberAEstring = Integer.toString(Debitnumber);
                        DebitcvcAE += Debitnumber + "";
                    }
                    String DebitcardNumberAE = "";
                    String DebitThree = "3";
                    for (int j = 0; j < 15; j++) {
                        int DebitcardNumberAETwo = (int) (Math.random() * 9);
                        String DebitcardNumberAEThree = Integer.toString(DebitcardNumberAETwo);
                        DebitcardNumberAE = DebitThree += DebitcardNumberAEThree + "";
                        Number = DebitcardNumberAE;
                        CVC = DebitcvcAE;
                    }
                    System.out.println("Your card number will be: " + DebitcardNumberAE);
                    System.out.println("Your cvc code will be: " + DebitcvcAE);
                    System.out.println("Generated Expiration Date:  " + exp.Expiration());
                    break;
                }
                if (DebitCardResponseTypes == 4) {
                    String DebitcvcVS = "";
                    for (int z = 0; z < 3; z++) {
                        int Debitnumber = (int) (Math.random() * 9);
                        String DebitnumberVSstring = Integer.toString(Debitnumber);
                        DebitcvcVS += Debitnumber + "";
                        // include the number generator for a new card.
                    }
                    String DebitcardNumberVS = "";
                    String DebitFour = "4";
                    for (int j = 0; j < 15; j++) {
                        int DebitcardNumberVSTwo = (int) (Math.random() * 9);
                        String DebitcardNumberVSThree = Integer.toString(DebitcardNumberVSTwo);
                        DebitcardNumberVS = DebitFour += DebitcardNumberVSThree += "";
                        Number = DebitcardNumberVS;
                        CVC = DebitcvcVS;
                    }
                    // include the EXP DATE generator for a new card.
                    System.out.println("Your card number will be: " + DebitcardNumberVS);
                    System.out.println("Your cvc code will be: " + DebitcvcVS);
                    System.out.println("Generated Expiration Date:" + exp.Expiration());

                    break;
                }
                if (DebitCardResponseTypes == 5) {
                    String DebitcvcMS = "";
                    for (int z = 0; z < 3; z++) {
                        int Debitnumber = (int) (Math.random() * 9);
                        String numberMSstring = Integer.toString(Debitnumber);
                        DebitcvcMS += Debitnumber + "";

                    }
                    // include the number generator for Master Card.
                    String DebitcardNumberMS = "";
                    String DebitFive = "5";
                    for (int j = 0; j < 15; j++) {
                        int DebitcardNumberMSTwo = (int) (Math.random() * 9);
                        String DebitcardNumberMSThree = Integer.toString(DebitcardNumberMSTwo);
                        DebitcardNumberMS = DebitFive += DebitcardNumberMSThree += "";
                        Number = DebitcardNumberMS;
                        CVC = DebitcvcMS;
                    }
                    System.out.println("Your card number will be: " + DebitcardNumberMS);
                    System.out.println("Your cvc code will be: " + DebitcvcMS);
                    System.out.println("Generated Expiration Date: " + exp.Expiration());
                    break;
                }
                if (DebitCardResponseTypes == 6) {
                    String DebitcvcDV = "";
                    for (int z = 0; z < 3; z++) {
                        int Debitnumber = (int) (Math.random() * 9);
                        String DebitnumberDVstring = Integer.toString(Debitnumber);
                        DebitcvcDV += Debitnumber + "";

                    }

                    String DebitcardNumberDV = "";

                    for (int j = 0; j < 15; j++) {
                        int DebitcardNumberDVTwo = (int) (Math.random() * 9);
                        String DebitcardNumberDVThree = Integer.toString(DebitcardNumberDVTwo);
                        DebitcardNumberDV += DebitcardNumberDVThree + "";

                    }
                    String DebitSix = "6";
                    String DebitcardNumberDVFinal = DebitSix += DebitcardNumberDV;
                    Number = DebitcardNumberDVFinal;
                    CVC = DebitcvcDV;
                    // Include the EXP DATE for Discover.
                    System.out.println("Your card number will be: " + DebitcardNumberDVFinal);
                    System.out.println("Your cvc code will be: " + DebitcvcDV);
                    System.out.println("Generated Expiration Date: " + exp.Expiration());
                    break;
                } else {

                    System.out.println("Re-enter your information now.");
                    infoNow = Debitscanner.nextInt();
                }
            }
        }
        adduser(Number, CVC, exp.Expiration(), "dcard.csv");
    }

    public String getNumber() {
        return Number;
    }

    public String getCVC() {
        return CVC;
    }

    public static void adduser(String Numbers, String CVC, String Expiration, String filename) throws IOException {
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

            }

            reader.close();
            reader = null;

            csvdata.add(new String[] { Numbers, "0", CVC, Expiration });
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

}