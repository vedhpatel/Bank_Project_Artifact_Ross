import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
@SuppressWarnings("all")


public class CreditCardExpirationDateGenerator {
    private static String Expiration;
        public String Expiration() {
        String expirationDate = generateExpirationDate();
        //System.out.println("Generated Expiration Date: " + expirationDate);
        Expiration = expirationDate;

        return Expiration;
    }
    

    public static String generateExpirationDate() {
        
        Calendar calendar = new GregorianCalendar();

        // Set the current date
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH) + 1; // Month is zero-based, so we add 1

        // Add 2-5 years to the current year for expiration year
        Random random = new Random();
        int yearsToAdd = random.nextInt(4) + 2;
        int expirationYear = currentYear + yearsToAdd;

        // Generate a random month between 1 and 12 for expiration month
        int expirationMonth = random.nextInt(12) + 1;

        // Adjust the expiration date if the expiration month is before the current month in the current year
        if (expirationYear == currentYear && expirationMonth < currentMonth) {
            expirationMonth = currentMonth;
        }

        // Format the expiration date
        String expirationDate = String.format("%02d/%d", expirationMonth, expirationYear);

        return expirationDate;
    }
    public String getExpiration()
    {
        return Expiration;
    }
}