import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

public class DebitCardExpirationDateGenerator {
    private static String Expiration;
    public String Expiration() {
        String DebitexpirationDate = DebitgenerateExpirationDate();
        // System.out.println("Generated Expiration Date: " + DebitexpirationDate);
        Expiration = DebitexpirationDate;

        return Expiration;
    }

    public static String DebitgenerateExpirationDate() {
        Calendar Debitcalendar = new GregorianCalendar();

        // Set the current date
        int DebitcurrentYear = Debitcalendar.get(Calendar.YEAR);
        int DebitcurrentMonth = Debitcalendar.get(Calendar.MONTH) + 1; // Month is zero-based, so we add 1

        // Add 2-5 years to the current year for expiration year
        Random Debitrandom = new Random();
        int DebityearsToAdd = Debitrandom.nextInt(4) + 2;
        int DebitexpirationYear = DebitcurrentYear + DebityearsToAdd;

        // Generate a random month between 1 and 12 for expiration month
        int DebitexpirationMonth = Debitrandom.nextInt(12) + 1;

        // Adjust the expiration date if the expiration month is before the current month in the current year
        if (DebitexpirationYear == DebitcurrentYear && DebitexpirationMonth < DebitcurrentMonth) {
            DebitexpirationMonth = DebitcurrentMonth;
        }

        // Format the expiration date
        String DebitexpirationDate = String.format("%02d/%d", DebitexpirationMonth, DebitexpirationYear);

        return DebitexpirationDate;
    }
    public String getExpiration()
    {
        return Expiration;
    }
}