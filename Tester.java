import java.io.IOException;
import java.util.Scanner;
@SuppressWarnings("all")

public class Tester {
  public static void main(String[] args) throws IOException {

    Convert obj = new Convert();
    //obj.ConvertCurrency(1000); 
     System.out.println( obj.ForeignPurchase(3.75, 2, "ACCTXXXXXXXXXX") );
    //  try {
    // obj.sendmoney(0.0, 0);
    // } catch (IOException e) {

    //    e.printStackTrace();
    //  }
    // ExchangeRate conv = new ExchangeRate();
    // System.out.println(conv.fetchExchangeRates("EGP"));
  }
}
