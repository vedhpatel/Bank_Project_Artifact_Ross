

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ExchangeRate {

    public Double fetchExchangeRates(String Rate) {
        String urlString = "https://api.exchangerate-api.com/v4/latest/USD"; // Ensure URL is correct

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                System.out.println("Request failed with HTTP error code : " + responseCode);
                return 0.0;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Example of extracting rates using simple string manipulation
            String ratesPart = response.substring(response.indexOf("\"rates\":") + 8,
                    response.indexOf("}", response.indexOf("\"rates\":")) + 1);

            int currencyIndex = ratesPart.indexOf(Rate);
            String rate = ratesPart.substring(currencyIndex, ratesPart.indexOf(",", currencyIndex));
            return (Double.valueOf((rate.substring(5))));
        } catch (Exception e) {
            System.err.println("Error occurred while fetching exchange rates: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

}
