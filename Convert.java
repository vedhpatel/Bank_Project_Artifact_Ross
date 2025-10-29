import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

@SuppressWarnings("all")

public class Convert {

    // temp Rates, API will overwrite these values

    private double SAR = 3.75;
    private double EUR = 0.92;
    private double GBP = 0.79;
    private double CAD = 1.34;
    private double INDR = 82.96;
    // As of 2-1-24

    private double fees = 0;
    private static int Choice = 0;
    String account = "";

    public double getRates(String Selection) {
        updaterates();
        double rate = 1;
        // check if inputted choice is a valid number

        Choice = Integer.parseInt(Selection);

        if (Choice <= 5 && Choice >= 1) {
            if (Choice == 1) {
                rate = SAR;
            } else if (Choice == 2) {
                rate = EUR;
            } else if (Choice == 3) {
                rate = GBP;
            } else if (Choice == 4) {
                rate = CAD;
            } else if (Choice == 5) {
                rate = INDR;
            }
        } else {
            System.out.println("Selection is invalid, rate defaulted to 1");
        }
        return rate;

    }

    public double ConvertCurrency(double Covnerted_USD, String account) throws IOException {
        String confirmconv = "no";
        Convert obj = new Convert();
        double rates;
        Scanner keyboard = new Scanner(System.in);

        System.out.println(
                "Select a Currency \n 1)Saudi Riyal \n 2)Euro \n 3)Pound Sterling \n 4)Canadian Dollar \n 5)Indian Rupee ");
        rates = obj.getRates(keyboard.next());
        System.out.println("The rate is " + rates + " for every USD." + " Total converted value is "
                + Covnerted_USD * rates + "\nThe fees when converting will total to " + Covnerted_USD * 0.03
                + "\n Continue? (yes/no)");

        confirmconv = keyboard.next();

        if (confirmconv.equals("yes") || confirmconv.equals("Yes")) {
            setfees((Covnerted_USD * 0.03) + Covnerted_USD);
            if ((Covnerted_USD * 0.03) + Covnerted_USD >= 1) {

                if (searchCsvLine(0, account) == (true)) {
                    settrue(account, "accounts.csv");
                    addfaccount(account, Choice, (Covnerted_USD * rates), "foreignaccounts.csv");
                    subsavings(account, (Covnerted_USD + Covnerted_USD * 0.03), "savingsaccount.csv");
                }

                return (Covnerted_USD * rates);

            } else {
                System.out.println("Value is to little, cancelling operation.");
                return 0;
            }

        } else if (confirmconv.equals("no") || confirmconv.equals("No")) {
            System.out.println("Conversion Cancelled");
            return 0;
        } else {
            System.out.println("Invalid response, cancelling conversion");
            return 0;
        }
    }

    public double ForeignPurchase(double fmoney, int currency, String account) throws IOException {
        // 1=SAR, 2=EUR, 3=GBP, 4=CAD, and 5= INDR

        String confirmconv = "no";

        String typefee = "";
        Convert obj = new Convert();
        double rates;
        Scanner keyboard = new Scanner(System.in);

        rates = obj.getRates(String.valueOf(currency));

        System.out
                .println("Would you like to use currency conversion, or dynamic currency conversion? (Type CC or DCC)");
        typefee = keyboard.next();
        if (typefee.equals("DCC") || typefee.equals("dcc")) {
            System.out.println("Total converted value is " + fmoney / rates
                    + "\n The fees when converting will total to " + (fmoney / rates) * 0.08
                    + " USD\n Continue? (yes/no)");

            confirmconv = keyboard.next();
            if (confirmconv.equals("yes") || confirmconv.equals("Yes")) {
                setfees(((fmoney / rates) * 0.08) + (fmoney / rates));

                if (searchCsvLine(0, account) == (true)) {
                    if (searchCsvLine(0, account) == (true)) {
                        settrue(account, "accounts.csv");
                        subsavings(account, ((fmoney / rates) + ((fmoney / rates) * 0.08)), "savingsaccount.csv");
                    }
                }

                return ((fmoney / rates) * 0.08) + (fmoney / rates);
            } else if (confirmconv.equals("no") || confirmconv.equals("No")) {
                System.out.println("Conversion Cancelled");
                return 0;
            } else {
                System.out.println("Invalid response, cancelling conversion");
                return 0;
            }
        }

        else if (typefee.equals("CC") || typefee.equals("cc")) {
            System.out.println("The rate is " + rates + " for every USD." + "\nThe fees when converting will total to "
                    + (fmoney / rates) * 0.03
                    + " USD\n Continue? (yes/no)");

            confirmconv = keyboard.next();

            if (confirmconv.equals("yes") || confirmconv.equals("Yes")) {
                setfees(((fmoney / rates) * 0.03));

                if (searchCsvLine(0, account) == (true)) {
                    if (searchCsvLine(0, account) == (true)) {

                        settrue(account, "accounts.csv");
                        subsavings(account, ((fmoney / rates) * 0.03) + (fmoney / rates), "savingsaccount.csv");
                    }
                }

                return (fees);
            } else if (confirmconv.equals("no") || confirmconv.equals("No")) {
                System.out.println("Conversion Cancelled");
                return 0.00;
            } else {
                System.out.println("Invalid response, cancelling conversion");
                return 0.00;
            }
        } else {
            System.out.println("Invalid response, cancelling conversion");
            return 0.00;
        }

    }

    public double converttoUSD(double fmoney, int currency, String account) throws IOException {
        // To use the function, input the amount of money, followed by the number and ID
        // corresponding to the currency that it is in.
        // 1=SAR, 2=EUR, 3=GBP, 4=CAD, and 5= INDR

        String confirmconv = "no";
        Convert obj = new Convert();
        double rates;
        Scanner keyboard = new Scanner(System.in);

        rates = obj.getRates(String.valueOf(currency));

        System.out.println("The rate is " + rates + " for every USD." + " Total converted value is "
                + fmoney / rates + "\nThe fees when converting will total to " + (fmoney / rates) * 0.03
                + "\n Continue? (yes/no)");

        confirmconv = keyboard.next();

        if (confirmconv.equals("yes") || confirmconv.equals("Yes")) {
            setfees(((fmoney / rates) * 0.03) + (fmoney / rates));
            if (((fmoney / rates) * 0.03) + (fmoney / rates) >= 1) {

                if (searchCsvLine(0, account) == (true)) {
                    settrue(account, "accounts.csv");
                    subfaccount(account, Choice, fmoney, "foreignaccounts.csv");
                    addsavings(account, ((fmoney / rates) - (fmoney / rates) * 0.03), "savingsaccount.csv");
                }

                return (fmoney / rates);
            } else {
                System.out.println("Value is to little, cancelling operation.");
                return 0;
            }

        } else if (confirmconv.equals("no") || confirmconv.equals("No")) {
            System.out.println("Conversion Cancelled");
            return 0;
        } else {
            System.out.println("Invalid response, cancelling conversion");
            return 0;
        }
    }

    // https://stackoverflow.com/questions/6016348/search-particular-column-value-from-csv-file-using-java

    public void sendmoney(double amount, int choice, String account) throws IOException {
        Scanner keyboard = new Scanner(System.in);
        String method = "";

        System.out.println(
                "Where would you like to send your money? \n 1)Cash\n 2)Wire to Foreign Card\n 3)Place in temp account.");
        method = keyboard.next();
        if (method.equals("1") || method.equals("2")) {
            System.out.println("Transfer compelte.");
        } else if (method.equals("3")) {

            if (searchCsvLine(0, account) == (true)) {

                settrue(account, "accounts.csv");

            }

        }

    }

    public boolean searchCsvLine(int searchColumnIndex, String searchString)
            throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("accounts.csv"));
        String line;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            if (values[searchColumnIndex].equals(searchString)) {
                System.out.println("ID found");
                return true;

            } else {
                System.out.println("No such ID found");
                return false;

            }
        }
        br.close();
        return false;
    }

    public void settrue(String ID, String filename) {
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
                String val7 = reader.get(6);
                String val8 = reader.get(7);
                String val9 = reader.get(8);
                String val10 = reader.get(9);
                String val11 = reader.get(10);
                String val12 = reader.get(11);
                String val13 = reader.get(12);
                String val14 = reader.get(13);
                String val15 = reader.get(14);
                String val16 = reader.get(15);
                String val17 = reader.get(16);
                String val18 = reader.get(17);

                if (val1.equals(ID)) {
                    val11 = "true";
                }
                csvdata.add(new String[] { val1, val2, val3, val4, val5, val6, val7, val8, val9, val10, val11, val12,
                        val13, val14, val15, val16, val17, val18 });

            }

            reader.close();
            reader = null;

            writecsv(csvdata, filename);

        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    public void addfaccount(String ID, int currency, Double money, String filename) throws IOException {
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

                if (val1.equals(ID)) {
                    if (money <= 0) {
                        System.out.println("You can't deposit negative funds.");
                    } else {

                        if (currency == 1) {
                            val2 = (Double.parseDouble(val2) + money) + "";
                        } else if (currency == 2) {
                            val3 = (Double.parseDouble(val3) + money) + "";
                        } else if (currency == 3) {
                            val4 = (Double.parseDouble(val4) + money) + "";
                        } else if (currency == 4) {
                            val5 = (Double.parseDouble(val5) + money) + "";
                        } else if (currency == 5) {
                            val6 = (Double.parseDouble(val6) + money) + "";
                        }

                    }

                }
                csvdata.add(new String[] { val1, val2, val3, val4, val5, val6 });
            }

            reader.close();
            reader = null;

            writecsv(csvdata, filename);

        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    public void subfaccount(String ID, int currency, Double money, String filename) throws IOException {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Use checking or savings account? (0:Checking, 1:Savings, 2:Cancel action)");
        int choice = 3;
        while (choice == 3) {
            choice = keyboard.nextInt();
            if (choice == 0) {
                filename = "checkingsaccount.csv";
            } else if (choice == 1) {
                filename = filename;
            }

            else if (choice == 2) {
                ID = "You Cancelled the action.";
            } else {
                System.out.println("Invalid choice.");
            }
        }
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

                if (val1.equals(ID)) {

                    if (currency == 1) {

                        if (money >= Double.parseDouble(val2)) {
                            System.out.println("Insufficient funds in user balance.");
                        } else if (money <= 0) {
                            System.out.println("You can't withdraw negative funds.");
                        } else {
                            val2 = (Double.parseDouble(val2) - money) + "";
                        }

                    } else if (currency == 2) {

                        if (money >= Double.parseDouble(val3)) {
                            System.out.println("Insufficient funds in user balance.");
                        } else if (money <= 0) {
                            System.out.println("You can't withdraw negative funds.");
                        } else {
                            val3 = (Double.parseDouble(val3) - money) + "";
                        }

                    } else if (currency == 3) {

                        if (money >= Double.parseDouble(val4)) {
                            System.out.println("Insufficient funds in user balance.");
                        } else if (money <= 0) {
                            System.out.println("You can't withdraw negative funds.");
                        } else {
                            val4 = (Double.parseDouble(val4) - money) + "";
                        }

                    } else if (currency == 4) {

                        if (money >= Double.parseDouble(val5)) {
                            System.out.println("Insufficient funds in user balance.");
                        } else if (money <= 0) {
                            System.out.println("You can't withdraw negative funds.");
                        } else {
                            val5 = (Double.parseDouble(val5) - money) + "";
                        }

                    } else if (currency == 5) {

                        if (money >= Double.parseDouble(val2)) {
                            System.out.println("Insufficient funds in user balance.");
                        } else if (money <= 0) {
                            System.out.println("You can't withdraw negative funds.");
                        } else {
                            val6 = (Double.parseDouble(val6) - money) + "";
                        }

                    }

                }
                csvdata.add(new String[] { val1, val2, val3, val4, val5, val6 });
            }

            reader.close();
            reader = null;

            writecsv(csvdata, filename);

        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    public void subsavings(String ID, Double money, String filename) throws IOException {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Use checking or savings account? (0:Checking, 1:Savings, 2:Cancel action)");
        int choice = 3;
        while (choice == 3) {
            choice = keyboard.nextInt();
            if (choice == 0) {
                filename = "checkingsaccount.csv";
            } else if (choice == 1) {
                filename = filename;
            }

            else if (choice == 2) {
                ID = "You Cancelled the action.";
            } else {
                System.out.println("Invalid choice.");
            }
        }

        try {
            CsvReader reader = new CsvReader(filename);

            int index = 0;
            ArrayList csvdata = new ArrayList();
            while (reader.readRecord()) {
                index++;

                String val1 = reader.get(0);
                String val2 = reader.get(1);

                if (val1.equals(ID)) {

                    if (money >= Double.parseDouble(val2)) {
                        System.out.println("Insufficient funds in user balance.");
                    } else if (money <= 0) {
                        System.out.println("You can't withdraw negative funds.");
                    } else {
                        val2 = (Double.parseDouble(val2) - money) + "";
                    }

                }

                csvdata.add(new String[] { val1, val2 });
            }

            reader.close();
            reader = null;

            writecsv(csvdata, filename);

        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    public void addsavings(String ID, Double money, String filename) throws IOException {
        Scanner keyboard = new Scanner(System.in);

        try {
            CsvReader reader = new CsvReader(filename);

            int index = 0;
            ArrayList csvdata = new ArrayList();
            while (reader.readRecord()) {
                index++;

                String val1 = reader.get(0);
                String val2 = reader.get(1);

                if (val1.equals(ID)) {

                    if (money <= 0) {
                        System.out.println("You can't deposit negative funds.");
                    } else {
                        val2 = (Double.parseDouble(val2) + money) + "";
                    }

                }
                csvdata.add(new String[] { val1, val2 });
            }

            reader.close();
            reader = null;

            writecsv(csvdata, filename);

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

    public double getfees() {
        updaterates();
        return fees;
    }

    public double SAR() {
        updaterates();
        return SAR;
    }

    public double EUR() {
        updaterates();
        return EUR;
    }

    public double GBP() {
        updaterates();
        return GBP;
    }

    public double CAD() {
        updaterates();
        return CAD;
    }

    public double INDR() {
        updaterates();
        return INDR;
    }

    public void setfees(double cost) {
        this.fees = cost;
    }

    public void updaterates() {
        ExchangeRate conv = new ExchangeRate();
        SAR = conv.fetchExchangeRates("SAR");
        EUR = conv.fetchExchangeRates("EUR");
        GBP = conv.fetchExchangeRates("GBP");
        CAD = conv.fetchExchangeRates("CAD");
        INDR = conv.fetchExchangeRates("INR");
    }

}
