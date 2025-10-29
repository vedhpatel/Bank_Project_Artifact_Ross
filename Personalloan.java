import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.lang.Math;


public class Personalloan {
    public static void main (String args[])
    {
         Scanner keyboard = new Scanner(System.in);
         
         System.out.println ("Would you like to apply for a personal loan or access current/previous loan: 1 for apply and 2 for access ");
         int option = keyboard.nextInt();


         loantermss();
         


        //  if (option == "Apply")
        //  {
        //     loanterms();
        //  }
         
        //  else if (option == "current")
        //  {
        //      user();
        //  }
        //  else if (option == "previous")
        //  {
        //      user();
        //  }
        //  else
        //  {
        //      Bankmenu();
        //  }

    }
   

    public static void loantermss()
    {
        Scanner ball = new Scanner(System.in);
        GettersPersonalLoan personalLoan = new GettersPersonalLoan(); 
           
  
        System.out.println ("Enter your credit score");
        double creditt = ball.nextDouble();
        // studentloan.setcredit (credit);
    

  
  
        if (creditt >= 650)
        {
          System.out.println ("You qualify for a personal loan! How long of a loan would you like: 1, 3, 5?");
          double qualifyy = ball.nextDouble();
          personalLoan.setqualifyy(qualifyy);
          System.out.println(personalLoan.getqualifyy());



          if (qualifyy == 1)
          {
           System.out.println ("How much money would you like to take a loan for? Over 1 year you will pay 7.80% interest.");
           double amount = ball.nextDouble();
           personalLoan.setamount(amount);
           System.out.println(personalLoan.getamount());

        //    studentloan.setmoneyy (moneyy);
           
  
           double max = amount * .0780;
           personalLoan.setmax(max);
           System.out.println(personalLoan.getmax());

        //    studentloan.setmoney (total);
  
           System.out.println ("You're total loan amount is: "+max);
  
           double month = max/60;
           personalLoan.setmonth(month);
           System.out.println(personalLoan.getmonth());

        //    studentloan.setmonthly (monthly);
  
           System.out.println ("Monthly Payments: "+month);
  
          }
          else if (qualifyy == 3)
          {
           System.out.println ("How much money would you like to take a loan for? Over 3 years you will pay 9.57% interest.");
           double amountt = ball.nextDouble();
           personalLoan.setamountt(amountt);
           System.out.println(personalLoan.getamountt());

        //    studentloan.setmoneyyy (moneyyy);
  
           double maxx = amountt * .0957;
           personalLoan.setmaxx(maxx);
           System.out.println(personalLoan.getmaxx());
        //    studentloan.settotall (totall);
  
           System.out.println ("You're total loan amount is: "+maxx);
  
           double monthl = maxx/120;
           personalLoan.setmonthl(monthl);
           System.out.println(personalLoan.getmonthl());
        //    studentloan.setmonthlyy (monthlyy);
  
           System.out.println ("Monthly Payments: "+monthl);
  
          }
          else if (qualifyy == 5)
          {
           System.out.println ("How much money would you like to take a loan for? Over 5 years you will pay 15% interest.");
           double amounttt = ball.nextDouble();
           personalLoan.setamounttt(amounttt);
           System.out.println(personalLoan.getamounttt());
        //    studentloan.setmoneyyyy (moneyyyy);
  
           double maxxx = amounttt *.15;
           personalLoan.setmaxxx(maxxx);
           System.out.println(personalLoan.getmaxxx());
        //    studentloan.settotalll (totalll);
  
           System.out.println ("You're total loan amount is: "+maxxx);
  
           double monthll = maxxx/180;
           personalLoan.setmonthll(monthll);
           System.out.println(personalLoan.getmonthll());
        //    studentloan.setmonthlyyy (monthlyyy);
  
           System.out.println ("Monthly Payments: "+monthll);
  
          }






           

          
  
       
  
        }
        else
        {
           System.out.println ("You do not qualify for a loan.");
        }
      }

     

   }

