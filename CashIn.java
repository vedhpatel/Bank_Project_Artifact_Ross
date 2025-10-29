import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.IntSummaryStatistics;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

@SuppressWarnings("all")

public class CashIn {

    public double cashin(String tempID, int accounttype) throws IOException {
        int exit = 0;

        while (exit != 1) {

            getbond(tempID, "bonds.csv", accounttype);

            return 0;

        }
        return 0;

    }

    public double getbond(String ID, String filename, int accounttype) throws IOException {

        try {

            CsvReader reader = new CsvReader(filename);

            int index = 0;
            ArrayList<String> csvdata = new ArrayList<String>();
            while (reader.readRecord()) {
                index++;

                String val1 = reader.get(0);
                String val2 = reader.get(1);
                String val3 = reader.get(2);
                String val4 = reader.get(3);
                String val5 = reader.get(4);
                String val6 = reader.get(5);
                String val7 = reader.get(6);

                if (val1.equals(ID)) {
                    for (int i = 1; i <= 4; i++) {
                        if (i == 1) {
                            System.out.println("Term Length: " + reader.get(i));
                        } else if (i == 2) {
                            System.out.println("Bond Interest Rate: " + reader.get(i));
                        } else if (i == 3) {
                            System.out.println("Date Purchased: " + reader.get(i));
                        } else if (i == 4) {
                            System.out.println("Original Cost of the Bond: " + reader.get(i));
                        }

                    }
                    SimpleDateFormat Year = new SimpleDateFormat("yyyy");
                    SimpleDateFormat Month = new SimpleDateFormat("MM");

                    String cmonth = Month.format(new Date());
                    String cyear = Year.format(new Date());
                    int months = (

                    ((Integer.parseInt(cyear))
                            - (Integer.parseInt(val7))) * 12
                            + ((Integer.parseInt(cmonth))
                                    - (Integer.parseInt(val6)))

                    );
                    Double valinterest = (Double.valueOf(val5) * (Math.pow((1 + Double.valueOf(val3)), months)))
                            - Double.valueOf(val5);
                    System.out.println("The total interest is " + valinterest);

                    System.out.println("Would you like to cash in this bond?(yes,no)");
                    Scanner keyboard = new Scanner(System.in);
                    String confirm = keyboard.next();

                    if (confirm.equals("yes") || confirm.equals("Yes")) {

                        // TODO fix bond deletion
                        deletebond(ID, filename, index);


                        if(accounttype == 0) {
                        addsavings(ID, valinterest, "savingsaccount.csv");
                        }
                        else if (accounttype == 1) {
                            addsavings(ID, valinterest, "checkingsaccount");
                        }

                        System.out.println("Cashed In");
                        return (valinterest);

                    }
                    if (confirm.equals("no") || confirm.equals("No")) {
                        continue;
                    }

                }
            }

            reader.close();
            reader = null;

        } catch (IOException e) {

            e.printStackTrace();
        }
        return 0;

    }

    public void printbonds(String ID, String filename) throws IOException {
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

                    for (int i = 1; i <= 4; i++) {
                        if (i == 1) {
                            System.out.println("Term Length: " + reader.get(i));
                        } else if (i == 2) {
                            System.out.println("Bond Interest Rate: " + reader.get(i));
                        } else if (i == 3) {
                            System.out.println("Date Purchased: " + reader.get(i));
                        } else if (i == 4) {
                            System.out.println("Original Cost of the Bond: " + reader.get(i));
                        }

                    }
                    System.out.println("");
                    continue;
                }

            }

            reader.close();
            reader = null;

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
                    val2 = (Double.parseDouble(val2) + money) + "";

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

    public void deletebond(String ID, String filename, int delete_index) throws IOException {
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
                String val7 = reader.get(6);

                if (index == delete_index) {
                    csvdata.add(new String[] { null });
                } else {
                    csvdata.add(new String[] { val1, val2, val3, val4, val5, val6 });
                }

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

    public static int searchCsvLine(int searchColumnIndex, String searchString) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("savingsaccount.csv"));
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

    // https://stackoverflow.com/questions/55144549/how-to-get-max-and-min-values-from-csv-file-in-java#:~:text=Simply%20read%20the%20file's%20lines,Clean%20and%20simple.
    private int minAndMax() {
        try (Stream<String> stream = Files.lines(Paths.get("bonds.csv"))) {
            IntSummaryStatistics statistics = stream
                    .map(s -> s.split(",")[1])
                    .mapToInt(Integer::valueOf)
                    .summaryStatistics();
            System.out.println("Lowest:: " + statistics.getMin());
            int max = (statistics.getMax());
            return (max);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;

    }

}