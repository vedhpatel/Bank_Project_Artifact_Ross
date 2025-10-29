import java.util.ArrayList;
import java.util.Scanner;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.io.IOException;
import java.lang.Math;
@SuppressWarnings("all")



public class CreditScore
{
	public static String fscore;
	public static String betterfscore;
  public static double C_Score = 0.0; 
  public void creditscore(String number)
  {
	double CreditScore = 0; 
	double LengthHistoryMath = 0;
	double CredScoreHistory = 0; 
	double CreditMixMath = 0; 
	double newCreditMath = 0; 
	double newCreditMathLoan = 0;
	double IncomeRatioMath = 0; 
	
	
	Scanner myObj = new Scanner (System.in);	// Create a Scanner object
	  System.out.println ("Welcome to the Credit Score Calculator. Please enter '1' now.");
	  
	  String CredCalcYes = myObj.nextLine ();
    
	if (CredCalcYes.equals ("1"))
	  {
		System.out.println ("Are you a new user? Please type yes or no.");
				System.out.println ("New users are those who have had all accounts, loans, etc. opened in the last 6 months.");
		System.out.println ("If you have not previously owned a card, type yes anyway. "); 
		String CredCalcYesTwo = myObj.nextLine ();
		
		if (CredCalcYesTwo.equals ("yes"))
			{
			System.out.println("Okay, you will be provided with a minimum credit score of 600. ");
			CreditScore = 600;
			 
		String selection = myObj.nextLine();
			}
			if (CredCalcYesTwo.equals ("no"))
			{
			System.out.println("-------------------------------------------------------------------------");
			System.out.println("How long have you had a credit card for?"); 
			System.out.println("Enter an integer value of months. (Round down in all cases.)"); 
			int CredCalcYesFour = myObj.nextInt(); 
			
			
			if (CredCalcYesFour <= 6) {
		      System.out.println("Okay, you are a new user. You'll be provided with the minimum score of 600."); 
		       CreditScore = 600; 
		       
		       String selection = myObj.nextLine();
		      //MAKE THE NECESSARY VARIABLE NAME CHANGES
		    }
			
			        else if (CredCalcYesFour >= 24) {
			    System.out.println("You've had a credit card for " + CredCalcYesFour + "months."); 
			    boolean GoodLength = true; 
			    boolean BadLength = false; 
			    LengthHistoryMath = 0.15*850; 
			    
			} 
			else if (CredCalcYesFour > 6 && CredCalcYesFour < 24) {
			    
			    LengthHistoryMath = 0.95*0.15*850; 
			    System.out.println("-------------------------------------------------------------------------"); 
			}
			}
			
			
			
			System.out.println("How many total payments, loans, bills, etc. have you made?"); 
			    int totPayment = myObj.nextInt();
		    System.out.println("How many of these payments were made on time?"); 
			    int OnTimePayment = myObj.nextInt(); 
			     
			 double totalPayment = totPayment; 
			 double OnTimePayments = OnTimePayment;
			 double PaymentHistory = OnTimePayments / totalPayment; 
			 double PaymentHistoryPerc = PaymentHistory*100;  
			 System.out.println("Your payment history as a percentage is " + PaymentHistoryPerc); 
			 
			 
			 
			 
			       if (PaymentHistoryPerc == 100.0) {
			            System.out.println("Nice, you have the best payment history. You'll most likely be given a higher score."); 
			            
			      CredScoreHistory = (0.35)*(850); 
			       }
			     
			      else if (PaymentHistoryPerc < 100 && PaymentHistoryPerc >= 90) {
			            System.out.println("Nice, you have an excellent payment history. You'll most likely be given a higher score."); 
			           
			            boolean Excellent = true; 
			     CredScoreHistory = (PaymentHistoryPerc/100)*0.35*850; 
			        }
			        else if (PaymentHistoryPerc <= 89.0 && PaymentHistoryPerc >= 80.0) {
			            System.out.println("Nice, you have a good payment history."); 
			            boolean GoodHistory = true; 
			       CredScoreHistory = (PaymentHistoryPerc/100)*0.35*850;
			        }
			        else if (PaymentHistoryPerc <=79.0 && PaymentHistoryPerc >= 70.0) {
			            System.out.println("Okay, that's acceptable, but you might be given a lower credit score."); 
			            boolean OkayHistory = true; 
			       CredScoreHistory = (PaymentHistoryPerc/100)*0.35*850;
			        }
			        else if (PaymentHistoryPerc <= 69.0 && PaymentHistoryPerc >= 60.0 ) {
			            System.out.println("Okay, that's fine, but you will be given a lower credit score."); 
			            boolean BadHistory = true; 
			       CredScoreHistory = (PaymentHistoryPerc/100)*0.35*850;
			        }
			        else if (PaymentHistoryPerc <= 59.0) {
			            System.out.println("-------------------------------------------------------------------------");
			            System.out.println("Sorry, but you have been deemed as a risk by the system."); 
			            System.out.println("Unfortunately, we will be forced to provide you with a credit score of 520"); 
			             CreditScore = 520; 
			            System.out.println("Your credit score is: " + CreditScore);
			            boolean BadHistory = true; 
			            
			            myObj.close(); 
			         
			     
			        }
			        }
			     
			     
			    
			        
		
			
			
			
			System.out.println("Okay, now that's out of the way."); 
			 
			System.out.println("-------------------------------------------------------------------------"); 
			System.out.println("How many accounts of installment credit do you currently have active?"); 
						System.out.println("Please enter an integer value"); 
			int InstallPayments = myObj.nextInt(); 
			    System.out.println("How many credit cards/revolving credit accounts do you have open?"); 
			    System.out.println("Please enter an integer value."); 
			
			int RevolvePayments = myObj.nextInt();
			int CreditMix = InstallPayments + RevolvePayments; 
			//calculate the percentage of credit score based on the types of credit
			System.out.println("Okay, so you it looks like you currently have " + CreditMix + " accounts active"); 
			if (CreditMix == 1) {
			    System.out.println("-------------------------------------------------------------------------"); 
			    			    System.out.println("Note: it's helpful to have more than one type of credit account open."); 
			     CreditMixMath = (0.80*0.1)*850; 
			}
			else if (CreditMix == 2) {
			    System.out.println("-------------------------------------------------------------------------"); 
			    
			     CreditMixMath = (0.85*0.1)*850; 
			}
			else if (CreditMix == 3) {
			    System.out.println("-------------------------------------------------------------------------"); 
			    			     CreditMixMath = (0.95*0.1)*850;
			}
			else if (CreditMix == 4) {
			    System.out.println("-------------------------------------------------------------------------"); 
			    System.out.println("That's a solid number, don't increase it more than that.");
			     CreditMixMath = 0.1*850; 
			}
			else if (CreditMix == 5) {
			    System.out.println("-------------------------------------------------------------------------"); 
			    System.out.println("That's acceptable, but try not to go over this many types of credit.");
			     CreditMixMath = (0.98*0.1)*850; 
			}
			else if (CreditMix == 6) {
			    System.out.println("-------------------------------------------------------------------------");
			    System.out.println("Alright, but try not to go over any further."); 
			     CreditMixMath = (0.94*0.1)*850; 
			}
			else if (CreditMix == 7) {
			    System.out.println("-------------------------------------------------------------------------"); 
			    System.out.println("Okay, you might notice a slight dip in your credit score."); 
			     CreditMixMath = 0.9*0.1*850; 
			}
			else if (CreditMix == 8) {
			    System.out.println("-------------------------------------------------------------------------"); 
			    System.out.println("There will be a dip in your credit score."); 
			     CreditMixMath = 0.87*0.1*850; 
			}
			else if (CreditMix == 9) {
			    System.out.println("-------------------------------------------------------------------------"); 
			    System.out.println("There will be a dip in your credit score."); 
			     CreditMixMath = 0.79*0.1*850; 
			}
			else if (CreditMix >= 10) {
			    System.out.println("-------------------------------------------------------------------------"); 
			    System.out.println("Okay, you will be provided with a default value.");
			    System.out.println("Please note that this limits your potential credit score."); 
			     CreditMixMath = 0.1*300; 
			}
	  
	  System.out.println("-------------------------------------------------------------------------"); 
	  System.out.println("Now, let's take a look at your new credit."); 
	  System.out.println("How many times have you applied for credit card in the past six months?"); 
	  System.out.println("Please enter an integer value."); 
	  int CredCalcYesSeven = myObj.nextInt(); 
	  if (CredCalcYesSeven == 1 || CredCalcYesSeven == 2) {
	      System.out.println("Okay, you haven't tried to apply for a card that much. This won't hurt your score."); 
	       newCreditMath = 0.5*0.1*850; 
	  }
	  else if (CredCalcYesSeven == 3 || CredCalcYesSeven == 4 || CredCalcYesSeven == 5) {
	      System.out.println("Okay, this might hurt your score a little bit.");
	       newCreditMath = 0.85*0.5*0.1*850; 
	      
	  }
	  else if (CredCalcYesSeven >= 6) {
	      System.out.println("Unfortunately, this impacts your score greatly. Try not to apply too much for a card in such little time.");
	       newCreditMath = 0.75*0.5*0.1*850; 
	  }
	 //ERROR IN CODE IS SOMEWHERE BELOW HERE
	  System.out.println("Have you applied for a loan recently? Type either yes or no.");
	  String responseActiveLoanTwo = myObj.nextLine (); //this line of code is being skipped
	   responseActiveLoanTwo = myObj.nextLine ();
	  	  if (responseActiveLoanTwo.equals("no")) {
	       newCreditMathLoan = 0.5*0.1*850; 
	   double newCreditMathLoanTotal = newCreditMathLoan + newCreditMath;    
	  }
	  // yes response -> provide them with the other half of the 85 points but subtract 10 points if they've applied too much
	  if (responseActiveLoanTwo.equals("yes")) {
	      System.out.println("How many loans did you attempt to take out/took out during the last 12 months?"); 
	      int ActiveLoans = myObj.nextInt(); 
	      if (ActiveLoans == 1 || ActiveLoans == 2 || ActiveLoans == 3) {
	     System.out.println("Okay, this shouldn't hurt your credit score, however, if you fall behind in your payments this will incur a loss in credit score."); 
	      newCreditMathLoan = 0.5*0.1*850; 
	     boolean softInquiry = false; 
	     double newCreditMathLoanTotal = newCreditMathLoan + newCreditMath; 
	      }
	      else if (ActiveLoans > 3 && ActiveLoans <= 6) {
	          System.out.println("Okay, this might hurt your credit score slightly."); 
	           newCreditMathLoan = 0.45*0.1*850; 
	          boolean hardInquiry = true; 
	          double newCreditMathLoanTotal = newCreditMathLoan + newCreditMath; 
	   //include code that subtracts 2 points for every query into credit; hard query will be "counted" ????
	      }
	      else if (ActiveLoans > 6 && ActiveLoans <= 8) {
	          System.out.println("Okay, this will hurt your potential score."); 
	           newCreditMathLoan = 0.40*0.1*850; 
	          boolean hardInquiryTwo = true; 
	          double newCreditMathLoanTotal = newCreditMathLoan + newCreditMath; 
	       
	          // hard inquiry two will count as 2 hard inquiries, lowers the credit score by 30 points.
	      }
	      else if (ActiveLoans > 8) {
	          System.out.println("We will provide a default value; in other words, your score will be severely limited."); 
	           newCreditMathLoan = 0.1*300;
	          boolean hardInquiryTwo = true; 
	          double newCreditMathLoanTotal = newCreditMathLoan + newCreditMath; 
				
	      }
	      
	  }
	 System.out.println("-------------------------------------------------------------------------"); 
	 System.out.println("Okay, please enter a value of how much you make per month.");
	 double MonthlyIncome = myObj.nextDouble(); 
	 System.out.println("Now, enter your monthly debt; this will include loans, mortgages, bills, minimum card payments, etc.");
	 double MonthlyDebt = myObj.nextDouble();
	 double DebtIncomeRatio = MonthlyDebt/MonthlyIncome; 
	 double DebtIncomePercent = DebtIncomeRatio*100; 
	 System.out.println("Okay, your DTR is " + DebtIncomePercent + "%"); 
	 //determine if the user's dtr ratio is good or not, which will affect the credit score promptly
	 //user may obtain a maximum of 255 points from this section of credit score
	 if (DebtIncomePercent > 0 && DebtIncomePercent <= 5) {
	     System.out.println("Nice, that's an excellent DTI ratio, you'll likely have a higher score"); 
	      IncomeRatioMath = 0.3*850; 
	 }
	 else if (DebtIncomePercent > 5 && DebtIncomePercent <= 10) {
	     System.out.println("Nice, that's an excellent DTI ratio, you'll likely have a higher score."); 
	      IncomeRatioMath = 0.95*0.3*850; 
	 }
	 else if (DebtIncomePercent > 10 && DebtIncomePercent <= 15) {
	     
	     System.out.println("Nice, that's a good DTI ratio, you'll likely have a higher score.");
	      IncomeRatioMath = 0.90*0.3*850; 
	 }
	 else if (DebtIncomePercent > 15 && DebtIncomePercent <= 25) {
	     System.out.println("Alright, that ratio is acceptable."); 
	      IncomeRatioMath = 0.87*0.3*850; 
	 }
	 else if (DebtIncomePercent > 25 && DebtIncomePercent <= 35) {
	     System.out.println("Okay, that ratio is acceptable."); 
	      IncomeRatioMath = 0.85*0.3*850; 
	 }
	 else if (DebtIncomePercent > 35 && DebtIncomePercent <= 40) {
	     System.out.println("Tip: Try to better manage your credit score."); 
	     System.out.println("This will hurt your potential credit score."); 
	      IncomeRatioMath = 0.75*0.3*850; 
	 }
	 else if (DebtIncomePercent > 45 && DebtIncomePercent <= 50) {
	     System.out.println("Tip: Try to better manage your credit score; this will hurt your potential score.");
	      IncomeRatioMath = 0.70*0.3*850; 
	 }
	 else if (DebtIncomePercent > 50 && DebtIncomePercent <= 100) {
	     System.out.println("Sorry, but you've been marked as a security risk by the system.");
	     System.out.println("Until your DTI ratio improves, we will be forced to provide you with a lower number."); 
	      IncomeRatioMath = 0.60*0.3*850; 
	 }
	 CreditScore = LengthHistoryMath + CredScoreHistory + CreditMixMath + newCreditMath + newCreditMathLoan + IncomeRatioMath;
	 C_Score = CreditScore;
	  betterfscore = Double.toString(CreditScore);
	  System.out.println("Congratulations! Your final credit score is " + CreditScore); 

	  try {
		addsavings(number, fscore, "ccard.csv" );
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  }
	   
	  public double getcreditscore() 
	  { 
	  
	  return C_Score; 
	      
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
	public static void addsavings(String CardNumber,String CreditScore, String filename) throws IOException {
		CreditCardNumberGenerator ask = new CreditCardNumberGenerator();
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

                if (val1.equals(ask.GetCard())) {
                  
					 val3 = betterfscore;

					 //still not writing :|
                }
                csvdata.add(new String[] { val1, val2, val3, val4, val5, val6 });
            }

            reader.close();
            reader = null;

             writecsv(csvdata, filename);
            return;

        } catch (IOException e) {

            e.printStackTrace();
        }

    }
	
  }
  
  
  
