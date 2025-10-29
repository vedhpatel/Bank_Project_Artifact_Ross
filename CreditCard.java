import java.util.Scanner;
import java.lang.Math;
@SuppressWarnings("all")


public class CreditCard
{
  private double balance;
  private double creditscore;

  public CreditCard (double initialbal, double initialcred)
  {
	double balance = initialbal;
	double creditscore = initialcred;
  }

  public void purchase (double amount)
  {
      Scanner scan = new Scanner(System.in);
      System.out.println("Would you like to Payback Money due for the Credit Card?");
      
      String response = scan.nextLine();
      
      if (response.equals("yes"))
      {
          System.out.println("How much did you spend on the Credit Card?");
          
          double spent = scan.nextInt();
          double remaining = balance-spent;
          if (spent > balance || spent < 0)
          {
              System.out.println("Please Enter a Valid Number");
          }
          
          System.out.println("Your Balance is currently: " + spent);
          System.out.println("What percent would you like to pay back? (Minimum of 5%)");
          
          double answer = scan.nextInt();
          
          if (answer < 5)
          {
              System.out.println("I'm sorry but that is too low of a percent to pay back");
              //return to menu
          }
          
          double paid = spent*(answer/100);
          double ke = spent - paid;
          balance = ke;
          
          System.out.println("You have paid: " + paid);
          System.out.println("Your new balance is " + ke);
          
          
          
      }
      if (response.equals("no"))
      {
          //return to menu
         
      }
      
      
	
  }

  public void payment (double amount)
  {
	balance += amount;
	System.out.println ("Payment has been recieved, your new balance is: " +
						balance);
  }
  
  
  

  public double getBalance ()
  {
	return balance;
  }
  public double getcreditscore ()
  {
	return creditscore;
  }

  public void setBalance (double money)
  {
	this.balance = money;
  }
  public void setcscore (double cscore)
  {
	this.creditscore = cscore;
  }
}
