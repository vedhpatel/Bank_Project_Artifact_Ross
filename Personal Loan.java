import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.lang.Math;


public class PersonalLoan {
    public static void main (String args[])
    {
         Scanner keyboard = new Scanner(System.in);
         
         System.out.println ("Would you like to apply for a student loan or access current/previous loan: ");
         String option = keyboard.nextLine();


         loanterms();


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

    public static void loanterms()
    {
        Scanner ball = new Scanner(System.in);
        Getters studentloan = new Getters(); 
           
  
        System.out.println ("Enter your credit score");
        double credit = ball.nextDouble();
        // studentloan.setcredit (credit);
    

  
  
        if (credit >= 650)
        {
          System.out.println ("You qualify for a student loan! How long of a loan would you like: 5, 10, 15?");
          double qualify = ball.nextDouble();
          studentloan.setqualify(qualify);
          System.out.println(studentloan.getqualify());



          if (qualify == 5)
          {
           System.out.println ("How much money would you like to take a loan for? Over 5 years you will pay 3.94% interest.");
           double moneyy = ball.nextDouble();
           studentloan.setmoneyyy(moneyy);
           System.out.println(studentloan.getmoneyy());

        //    studentloan.setmoneyy (moneyy);
           
  
           double total = moneyy * .0394;
           studentloan.settotal(total);
           System.out.println(studentloan.gettotal());

        //    studentloan.setmoney (total);
  
           System.out.println ("You're total loan amount is: "+total);
  
           double monthly = total/60;
           studentloan.setmonthly(monthly);
           System.out.println(studentloan.getmonthly());

        //    studentloan.setmonthly (monthly);
  
           System.out.println ("Monthly Payments: "+monthly);
  
          }
          else if (qualify == 10)
          {
           System.out.println ("How much money would you like to take a loan for? Over 10 years you will pay 4.5% interest.");
           double moneyyy = ball.nextDouble();
           studentloan.setmoneyy(moneyyy);
           System.out.println(studentloan.getmoneyy());

        //    studentloan.setmoneyyy (moneyyy);
  
           double totall = moneyyy * .045;
           studentloan.settotall(totall);
           System.out.println(studentloan.gettotall());
        //    studentloan.settotall (totall);
  
           System.out.println ("You're total loan amount is: "+totall);
  
           double monthlyy = totall/120;
           studentloan.setmonthlyy(monthlyy);
           System.out.println(studentloan.getmonthlyy());
        //    studentloan.setmonthlyy (monthlyy);
  
           System.out.println ("Monthly Payments: "+monthlyy);
  
          }
          else if (qualify == 15)
          {
           System.out.println ("How much money would you like to take a loan for? Over 15 years you will pay 5% interest.");
           double moneyyyy = ball.nextDouble();
           studentloan.setmoneyyyy(moneyyyy);
           System.out.println(studentloan.getmoneyyyy());
        //    studentloan.setmoneyyyy (moneyyyy);
  
           double totalll = moneyyyy *.05;
           studentloan.settotalll(totalll);
           System.out.println(studentloan.getotalll());
        //    studentloan.settotalll (totalll);
  
           System.out.println ("You're total loan amount is: "+totalll);
  
           double monthlyyy = totalll/180;
           studentloan.setmonthlyyy(monthlyyy);
           System.out.println(studentloan.getmonthlyyy());
        //    studentloan.setmonthlyyy (monthlyyy);
  
           System.out.println ("Monthly Payments: "+monthlyyy);
  
          }

           

          
  
       
  
        }
        else
        {
           System.out.println ("You do not qualify for a loan.");
        }
      }
