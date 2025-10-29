// imports ====================================================================================================
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.text.ParseException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;  
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;  
import java.util.Date; 
import java.text.DateFormat;
import java.util.Calendar;
import java.lang.Math;
import java.text.DecimalFormat;

public class AnnuityInvestment 
{
	public static void main(String[] args) throws IOException, ParseException
	{	
		
// variables/Scanner/user ====================================================================================================
		// decimal formatter to ensure good looking displays
		DecimalFormat twoDecimalPlaces = new DecimalFormat("0.00");
		
		// current user
    	String UserID = MainMenu.loggedInAccountId;

		
		//scanner
		Scanner info = new Scanner(System.in);
		
		// string variables for does not have investment
		String currentlyLoggedInUser = UserID;
		String line;
		String splitBy = ",";
		String doesUserHaveInvestmentString = "";
		String currentusersName = "";
		String createInvestmentResponse = "";
		String useCheckOrSaveAccForInvestment = "";
		String useCheckOrSaveAccForInvestmentFullWord = "";
		String useCheckOrSaveAccForPayment = "";
		String useCheckOrSaveAccForPaymentFullWord = "";
		String firstPaymentOption = "";
		String firstPaymentOptionFullWord = "";
		String firstPaymentDate = "";
		String mainCSVTFChange = "";
		String checkingAccAmountString = "";
		String savingsAccAmountString = "";
		
		// string variables for already has investment
		String viewAnnuityInvestment = "";
		String userCSV = "";
		String firstPaymentDateCSV = "";
		String recentPaymentDateCSV = "";
		String nextPaymentDateCSV = "";
		String checkOrSaveAccForInvestmentCSV = "";
		String checkOrSaveAccForPaymentsCSV = "";
		String stringTermCSV = "";
		String stringAmountOfPaymentsMadeIncludingFirstCSV = "";
		String stringAmountOfPaymentsLeftCSV = "";
		String withdrawToCheckOrSaveResponse = "";
		
		// double variables for does not have investment
		double checkingAccAmount = 0.0; // use .get method from checking account team
		double savingsAccAmount = 0.0; // use .get method from savings team
		double checkOrSaveAccAmountForLimit = 0.0;
		double principle = 0.0;
		double interestRate = 0.0;
		double monthlyPaymentAmount = 0.0;
		double monthylPaymentInterestRateVariable = 0.0;
		
		// double variables for has investment
		double principleCSV = 0.0;
		double newBalanceCSV = 0.0;
		double monthlyPaymentAmountCSV = 0.0;
		double interestRateCSV = 0.0;
		
		// int variables for does not have investment
		int investmentTerm = 0;
		int mainCSVTFCol = 0;
		int mainCSVTFRow = 0;
		int userCSVRow = 0;
		int checkingsaccountCSVRow = 0;
		int savingsaccountCSVRow = 0;
		
		// int variables for has investment
		int termCSV = 0;
		int amountOfPaymentsMadeIncludingFirstCSV = 0;
		int amountOfPaymentsLeftCSV = 0;
		int termOverOptionsResponse = 0;
		
		// boolean variables for does not have investment
		Boolean checkingAccMade = false;
		Boolean savingsAccMade = false;
		Boolean doesUserHaveInvestment = false;
		Boolean createInvestmentInputValid = false;
		Boolean useCOSForInvestmentInputValid = false;
		Boolean useCOSForPaymentInputValid = false;
		Boolean investmentTermInputValid = false;
		Boolean firstPaymentOptionInputValid = false;
		
		// boolean variables for has investment
		Boolean viewAnnuityInvestmentInputValid = false;
		Boolean hasInvestmentCSV = false;
		Boolean termOverOptionResponseInputValid = false;
		Boolean withdrawToCheckOrSaveResponseInputValid = false;
		
// check account for investment ====================================================================================================
		try   
		{    
		BufferedReader readMainCSV = new BufferedReader(
				new FileReader("accounts.csv"));  
		while ((line = readMainCSV.readLine()) != null) 
			{  
				String[] currentUser = line.split(splitBy); 
				if(currentUser[0].equals(currentlyLoggedInUser)){  
					doesUserHaveInvestmentString = (currentUser[22]);
					currentusersName = ((currentUser[1]).replace(" ", ""));
				}
				
			}  
		readMainCSV.close();
		}   
		catch (IOException e)   
		{  
			e.printStackTrace();  
		}  
		
		// change make investment or not make investment variable
		if (doesUserHaveInvestmentString.equalsIgnoreCase("true") || doesUserHaveInvestmentString.equalsIgnoreCase(" true"))
		{
			doesUserHaveInvestment = true;
		} else if (doesUserHaveInvestmentString.equalsIgnoreCase("false") || doesUserHaveInvestmentString.equalsIgnoreCase(" false"))
		{
			doesUserHaveInvestment = false;
		} else
		{
			System.out.print("ERROR IN \"check account for investment\""
					+ "\r\n"
					+ "Incorrect Found Value: " + doesUserHaveInvestmentString
					+ "\r\n");
		}
		
		// get row of currently logged in user ====================================================================================================
		try (BufferedReader reader = new BufferedReader(new FileReader("accounts.csv")))
        {
            while ((line = reader.readLine()) != null)
            {
                String[] currentUser = line.split(splitBy);
                if(currentUser[0].equals(currentlyLoggedInUser)){  
					break;
				}
                mainCSVTFRow++;
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
		
		// get checking and savings account balance for investment input boundaries and later use ====================================================================================================
					// get checking acc balance
					try   
					{    
					BufferedReader readMainCSV = new BufferedReader(
							new FileReader("checkingsaccount.csv"));  
					while ((line = readMainCSV.readLine()) != null) 
						{  
							String[] currentUser = line.split(splitBy); 
							if(currentUser[0].equals(currentlyLoggedInUser)){  
								checkingAccAmount = Double.parseDouble(currentUser[1]);
								checkingAccMade = true;
							}
							
						}  
					readMainCSV.close();
					}   
					catch (IOException e)   
					{  
						e.printStackTrace();  
					} 
					
					// get row of checking acc
					try (BufferedReader reader = new BufferedReader(new FileReader("checkingsaccount.csv")))
			        {
			            while ((line = reader.readLine()) != null)
			            {
			                String[] currentUser = line.split(splitBy);
			                if(currentUser[0].equals(currentlyLoggedInUser)){  
								break;
							}
			                checkingsaccountCSVRow++;
			            }
			        } catch (IOException e)
			        {
			            e.printStackTrace();
			        }
					
					// get savings acc balance
					try   
					{    
					BufferedReader readMainCSV = new BufferedReader(
							new FileReader("savingsaccount.csv"));  
					while ((line = readMainCSV.readLine()) != null) 
						{  
							String[] currentUser = line.split(splitBy); 
							if(currentUser[0].equals(currentlyLoggedInUser)){  
								savingsAccAmount = Double.parseDouble(currentUser[1]);
								savingsAccMade = true;
							}
							
						}  
					readMainCSV.close();
					}   
					catch (IOException e)   
					{  
						e.printStackTrace();  
					} 
					
					// get row of savings acc
					try (BufferedReader reader = new BufferedReader(new FileReader("checkingsaccount.csv")))
			        {
			            while ((line = reader.readLine()) != null)
			            {
			                String[] currentUser = line.split(splitBy);
			                if(currentUser[0].equals(currentlyLoggedInUser)){  
								break;
							}
			                savingsaccountCSVRow++;
			            }
			        } catch (IOException e)
			        {
			            e.printStackTrace();
			        }
	if (checkingAccMade && savingsAccMade)	
	{
// run has or does not have investment program ====================================================================================================
		if (doesUserHaveInvestment)
		{
// has investment ====================================================================================================
			
			// ask if wish to view investment
			System.out.print("=================================================="
					+ "\r\n"
					+ "Welcome back " + currentusersName + "!"
					+ "\r\n"
					+ "Would you like to view and update your current annuity investment's info?"
					+ "\r\n"
					+ "(Respond with \"y\" or \"n\"): ");
			viewAnnuityInvestment = info.next();
			
			// check to see if input is valid
			if (viewAnnuityInvestment.equals("y") || viewAnnuityInvestment.equals("n"))
				{
				viewAnnuityInvestmentInputValid = true;
				} else
				{
					viewAnnuityInvestmentInputValid = false;
				}
			
			// if not valid, ask again till valid
			while (viewAnnuityInvestmentInputValid == false)
			{
				System.out.print("\r\n"
						+ "It would appear you have not entered a valid response, please try again: ");
				viewAnnuityInvestment = info.next();
				if (viewAnnuityInvestment.equals("y") || viewAnnuityInvestment.equals("n"))
				{
					viewAnnuityInvestmentInputValid = true;
				} else
				{
					viewAnnuityInvestmentInputValid = false;
				}
			}
			
			if (viewAnnuityInvestment.equals("y"))
			{
				System.out.print("\r\n"
						+ "Here is your current annuity investment: ");
				
				// get info from csv file and put into variables ====================================================================================================
				try   
				{    
				BufferedReader readMainCSV = new BufferedReader(
						new FileReader("Annuity Investment Users.csv"));  
				while ((line = readMainCSV.readLine()) != null) 
					{  
						String[] currentUser = line.split(splitBy); 
						if(currentUser[0].equals(currentlyLoggedInUser)){  
							for (int i = 0; i < 14; i++)
							{
								if (i == 0)
								{
									userCSV = currentUser[i];
								} else if (i == 1)
								{
									firstPaymentDateCSV = currentUser[i];
									firstPaymentDateCSV = firstPaymentDateCSV.substring(1);
								} else if (i == 2)
								{
									recentPaymentDateCSV = currentUser[i];
									recentPaymentDateCSV = recentPaymentDateCSV.substring(1);
								} else if (i == 3)
								{
									nextPaymentDateCSV = currentUser[i];
									nextPaymentDateCSV = nextPaymentDateCSV.substring(1);
								} else if (i == 4)
								{
									checkOrSaveAccForInvestmentCSV = currentUser[i];
									checkOrSaveAccForInvestmentCSV = checkOrSaveAccForInvestmentCSV.substring(1);
								} else if (i == 5)
								{
									checkOrSaveAccForPaymentsCSV = currentUser[i];
									checkOrSaveAccForPaymentsCSV = checkOrSaveAccForPaymentsCSV.substring(1);
								} else if (i == 6)
								{
									principleCSV = (Double.valueOf(currentUser[i]));
								} else if (i == 7)
								{
									newBalanceCSV = Double.valueOf(currentUser[i]);
								} else if (i == 8)
								{
									monthlyPaymentAmountCSV = Double.valueOf(currentUser[i]);
								} else if (i == 9)
								{
									interestRateCSV = Double.valueOf(currentUser[i]);
								} else if (i == 10)
								{
									stringTermCSV = (currentUser[i]).substring(1);
									termCSV = Integer.valueOf(stringTermCSV);
								} else if (i == 11)
								{
									stringAmountOfPaymentsMadeIncludingFirstCSV = (currentUser[i]).substring(1);
									amountOfPaymentsMadeIncludingFirstCSV = Integer.valueOf(stringAmountOfPaymentsMadeIncludingFirstCSV);
								} else if (i == 12)
								{
									stringAmountOfPaymentsLeftCSV = (currentUser[i]).substring(1);
									amountOfPaymentsLeftCSV = Integer.valueOf(stringAmountOfPaymentsLeftCSV);
								} else if (i == 13)
								{
									hasInvestmentCSV = Boolean.parseBoolean((currentUser[i].replace(" ", "")));
								}
							}
						}
						
					}  
				readMainCSV.close();
				}   
				catch (IOException e)   
				{  
					e.printStackTrace();  
				}
				
				// re create user's investment object using re evaluated variables and perform maintenance ====================================================================================================
				AnnuityInvestmentObject annuityInvestment = new AnnuityInvestmentObject();
				annuityInvestment.reCreateAnnuityInvestmentObject(userCSV, firstPaymentDateCSV, recentPaymentDateCSV, nextPaymentDateCSV, checkOrSaveAccForInvestmentCSV, checkOrSaveAccForPaymentsCSV, principleCSV, newBalanceCSV, monthlyPaymentAmountCSV, interestRateCSV, termCSV, amountOfPaymentsMadeIncludingFirstCSV, amountOfPaymentsLeftCSV, hasInvestmentCSV);
				
				if (checkOrSaveAccForPaymentsCSV.equals("c"))
				{
					checkingAccAmount = annuityInvestment.existingInvestmentVariableMaintenance(checkingAccAmount);
				} else if (checkOrSaveAccForPaymentsCSV.equals("s"))
				{
					savingsAccAmount = annuityInvestment.existingInvestmentVariableMaintenance(savingsAccAmount);
				}
				
				// display investment ====================================================================================================
				annuityInvestment.getDisplayAttributes();
				
				// get row of user ====================================================================================================
				try (BufferedReader reader = new BufferedReader(new FileReader("Annuity Investment Users.csv")))
		        {
		            while ((line = reader.readLine()) != null)
		            {
		                String[] currentUser = line.split(splitBy);
		                if(currentUser[0].equals(currentlyLoggedInUser)){  
							break;
						}
		                userCSVRow++;
		            }
		        } catch (IOException e)
		        {
		            e.printStackTrace();
		        }
				
				// if investment over ====================================================================================================
				
				// gives options for what to do
				/*annuityInvestment.setAmountOfPaymentsLeft(0);*/
				if (annuityInvestment.getAmountOfPaymentsLeft() == 0)
				{
					System.out.print("\r\n"
							+ "It appears your annuity investment has come to an end."
							+ "\r\n"
							+ "|"
							+ "\r\n"
							+ "  Here is your list of options moving forward: "
							+ "\r\n"
							+ "    - Withdraw the amount into either your savings or checking account"
							+ "\r\n"
							+ "    - Start fresh without an annuity investment"
							+ "\r\n"
							+ "|"
							+ "\r\n");
					System.out.print("Respond with \"1\" for the first option or \"2\" for the second option): ");
					termOverOptionsResponse = info.nextInt();
					
					//make sure response is valid
					if (termOverOptionsResponse == 1 || termOverOptionsResponse == 2)
					{
						termOverOptionResponseInputValid = true;
					} else
					{
						termOverOptionResponseInputValid = false;
					}
					
					while (termOverOptionResponseInputValid == false)
					{
						System.out.print("\r\n"
								+ "It would appear you have not entered a valid response, please try again: ");
						termOverOptionsResponse = info.nextInt();
						if (termOverOptionsResponse == 1 || termOverOptionsResponse == 2)
						{
							termOverOptionResponseInputValid = true;
						} else
						{
							termOverOptionResponseInputValid = false;
						}
					}
					
					// run programs depending on users input
					if (termOverOptionsResponse == 1)
					{
						System.out.print("\r\n"
								+ "-------------------------"
								+ "\r\n"
								+ "Would you like your \"New Balance\" amount to be sent to your checking or savings account?"
								+ "\r\n"
								+ "(respond with \"c\" or \"s\"): ");
						withdrawToCheckOrSaveResponse = info.next();
						
						// check to see if input is valid
						if (withdrawToCheckOrSaveResponse.equals("c") || withdrawToCheckOrSaveResponse.equals("s"))
							{
							withdrawToCheckOrSaveResponseInputValid = true;
							} else
							{
								withdrawToCheckOrSaveResponseInputValid = false;
							}
						
						// if not valid, ask again till valid
						while (withdrawToCheckOrSaveResponseInputValid == false)
						{
							System.out.print("\r\n"
									+ "It would appear you have not entered a valid response, please try again: ");
							withdrawToCheckOrSaveResponse = info.next();
							if (withdrawToCheckOrSaveResponse.equals("c") || withdrawToCheckOrSaveResponse.equals("s"))
							{
								withdrawToCheckOrSaveResponseInputValid = true;
							} else
							{
								withdrawToCheckOrSaveResponseInputValid = false;
							}
						}
						
						// withdraw and send money
						if (withdrawToCheckOrSaveResponse.equals("c"))
						{
							checkingAccAmount += annuityInvestment.getNewBalance();
							annuityInvestment.setNewBalance(0);
						} else if (withdrawToCheckOrSaveResponse.equals("s"))
						{
							savingsAccAmount += annuityInvestment.getNewBalance();
							annuityInvestment.setNewBalance(0);
						}
						
						System.out.print("\r\n"
								+ "You have successfully transfered your \"New Balance\" amount."
								+ "\r\n"
								+ "Your previous annuity investment's information will now be reset."
								+ "\r\n"
								+ "-------------------------"
								+ "\r\n");
						
					} else if (termOverOptionsResponse == 2)
					{
						System.out.print("\r\n"
								+ "-------------------------"
								+ "\r\n"
								+ "Your information has been succesfully reset."
								+ "\r\n"
								+ "-------------------------"
								+ "\r\n");
					}
					
				}
				
				// update csv files ====================================================================================================
				for (int i = 0; i < 14; i++)
				{
					if (i == 0)
					{
						modifyCSV("Annuity Investment Users.csv", annuityInvestment.getUser(), userCSVRow, i);
					} else if (i == 1)
					{
						modifyCSV("Annuity Investment Users.csv", annuityInvestment.getfirstPaymentDate(), userCSVRow, i);
					} else if (i == 2)
					{
						modifyCSV("Annuity Investment Users.csv", annuityInvestment.getRecentPaymentDate(), userCSVRow, i);
					} else if (i == 3)
					{
						modifyCSV("Annuity Investment Users.csv", annuityInvestment.getNextPaymentDate(), userCSVRow, i);
					} else if (i == 4)
					{
						modifyCSV("Annuity Investment Users.csv", annuityInvestment.getCheckOrSaveAccForInvestment(), userCSVRow, i);
					} else if (i == 5)
					{
						modifyCSV("Annuity Investment Users.csv", annuityInvestment.getCheckOrSaveAccForPayments(), userCSVRow, i);
					} else if (i == 6)
					{
						modifyCSV("Annuity Investment Users.csv", Double.toString(annuityInvestment.getPrinciple()), userCSVRow, i);
					} else if (i == 7)
					{
						modifyCSV("Annuity Investment Users.csv", Double.toString(annuityInvestment.getNewBalance()), userCSVRow, i);
					} else if (i == 8)
					{
						modifyCSV("Annuity Investment Users.csv", Double.toString(annuityInvestment.getMonthlyPaymentAmount()), userCSVRow, i);
					} else if (i == 9)
					{
						modifyCSV("Annuity Investment Users.csv", Double.toString(annuityInvestment.getInterestRate()), userCSVRow, i);
					} else if (i == 10)
					{
						modifyCSV("Annuity Investment Users.csv", Integer.toString(annuityInvestment.getTerm()), userCSVRow, i);
					} else if (i == 11)
					{
						modifyCSV("Annuity Investment Users.csv", Integer.toString(annuityInvestment.getAmountOfPaymentsMadeIncludingFirst()), userCSVRow, i);
					} else if (i == 12)
					{
						modifyCSV("Annuity Investment Users.csv", Integer.toString(annuityInvestment.getAmountOfPaymentsLeft()), userCSVRow, i);
					} else if (i == 13)
					{
						modifyCSV("Annuity Investment Users.csv", Boolean.toString(annuityInvestment.getHasInvestment()), userCSVRow, i);
					}	
				}
				
				// modify chekcing or savings acc csv
				if (withdrawToCheckOrSaveResponse.equals("c") )
				{
					checkingAccAmount = Math.round((checkingAccAmount) * 100.00) / 100.00;
					checkingAccAmountString = Double.toString(checkingAccAmount);
					modifyCSV("checkingsaccount.csv", checkingAccAmountString, checkingsaccountCSVRow, 1);
				} else if (withdrawToCheckOrSaveResponse.equals("s"))
				{
					savingsAccAmount = Math.round((savingsAccAmount) * 100.00) / 100.00;
					savingsAccAmountString = Double.toString(savingsAccAmount);
					modifyCSV("savingsaccount.csv", savingsAccAmountString, savingsaccountCSVRow, 1);
				}
				
				if (termOverOptionsResponse == 1 || termOverOptionsResponse == 2)
				{
				modifyCSV("accounts.csv", "false", mainCSVTFRow, 22);
				modifyCSV("Annuity Investment Users.csv", "INVESTMENT TERMINATED", userCSVRow, 0);
				}
				
				System.out.print("\r\n"
						+ "We hope to have you back soon!"
						+ "\r\n"
						+ "==================================================");
				
			// if does not want to view investment ====================================================================================================
			} else if (viewAnnuityInvestment.equals("n"))
			{
				System.out.print("\r\n"
						+ "We hope to have you back soon!"
						+ "\r\n"
						+ "==================================================");
			}
			
		} else
		{
// does not have investment ====================================================================================================
		// get first response to create loan
		System.out.print("==================================================" + "\r\n"
				+ "Hello " + currentusersName + ", it appears you do not have an annuity investment." + "\r\n"
				+ "Would you like to create one? (Respond with \"y\" or \"n\"): ");
		createInvestmentResponse = info.next();
		
		// check to see if input is valid
		if (createInvestmentResponse.equals("y") || createInvestmentResponse.equals("n"))
			{
				createInvestmentInputValid = true;
			} else
			{
				createInvestmentInputValid = false;
			}
		
		// if not valid, ask again till valid
		while (createInvestmentInputValid == false)
		{
			System.out.print("\r\n"
					+ "It would appear you have not entered a valid response, please try again: ");
			createInvestmentResponse = info.next();
			if (createInvestmentResponse.equals("y") || createInvestmentResponse.equals("n"))
			{
				createInvestmentInputValid = true;
			} else
			{
				createInvestmentInputValid = false;
			}
		}
		
		// create or not create loan 
		if (createInvestmentResponse.equals("y")) 
		{
			// create loan ====================================================================================================
			
			// get account used for investment ====================================================================================================
			System.out.print("\r\n"
					+ "-------------------------"
					+ "\r\n"
					+ "We are glad you wish to create an annuity investment with us."
					+ "\r\n"
					+ "\r\n"
					+ " - Firstly, would you like to make the initial investment using your checking or savings account?"
					+ "\r\n"
					+ "(Type \"c\" for checking account and \"s\" for savings account.): ");
			useCheckOrSaveAccForInvestment = info.next();
			
			// check to see if input is valid
			if (useCheckOrSaveAccForInvestment.equals("c") || useCheckOrSaveAccForInvestment.equals("s"))
				{
				useCOSForInvestmentInputValid = true;
				} else
				{
					useCOSForInvestmentInputValid = false;
				}
			
			// if not valid, ask again till valid
			while (useCOSForInvestmentInputValid == false)
			{
				System.out.print("\r\n"
						+ "It would appear you have not entered a valid response, please try again: ");
				useCheckOrSaveAccForInvestment = info.next();
				if (useCheckOrSaveAccForInvestment.equals("c") || useCheckOrSaveAccForInvestment.equals("s"))
				{
					useCOSForInvestmentInputValid = true;
				} else
				{
					useCOSForInvestmentInputValid = false;
				}
			}
			
			if (useCheckOrSaveAccForInvestment.equals("c"))
			{
				useCheckOrSaveAccForInvestmentFullWord = "Checking Account";
				checkOrSaveAccAmountForLimit = checkingAccAmount;
			} else if (useCheckOrSaveAccForInvestment.equals("s"))
			{
				useCheckOrSaveAccForInvestmentFullWord = "Savings Account";
				checkOrSaveAccAmountForLimit = savingsAccAmount;
			}
			
			// ask where the payments will go (checking or savings account) ====================================================================================================
			System.out.print("\r\n"
					+ " - Second, would you like your recieved payments to be sent to your checking or saving acccount?"
					+ "\r\n"
					+ "(Type \"c\" for checking account and \"s\" for savings account.): ");
			useCheckOrSaveAccForPayment = info.next();
			
			// check to see if input is valid
						if (useCheckOrSaveAccForPayment.equals("c") || useCheckOrSaveAccForPayment.equals("s"))
							{
							useCOSForPaymentInputValid = true;
							} else
							{
								useCOSForPaymentInputValid = false;
							}
						
						// if not valid, ask again till valid
						while (useCOSForPaymentInputValid == false)
						{
							System.out.print("\r\n"
									+ "It would appear you have not entered a valid response, please try again: ");
							useCheckOrSaveAccForPayment = info.next();
							if (useCheckOrSaveAccForPayment.equals("c") || useCheckOrSaveAccForPayment.equals("s"))
							{
								useCOSForPaymentInputValid = true;
							} else
							{
								useCOSForPaymentInputValid = false;
							}
						}
						
						// create full word variables for display
						if (useCheckOrSaveAccForPayment.equals("c"))
						{
							useCheckOrSaveAccForPaymentFullWord = "Checking Account";
						} else if (useCheckOrSaveAccForPayment.equals("s"))
						{
							useCheckOrSaveAccForPaymentFullWord = "Savings Account";
						}		
			
			// get investment amount ====================================================================================================
						
						// get investment ammount
						System.out.print("\r\n"
								+ " - Thirdly, how much would to like to put into the investment?"
								+ "\r\n"
								+ "(Your limit is $" + twoDecimalPlaces.format(checkOrSaveAccAmountForLimit) + " [Use \".\" for cents]): " + "$");
						principle = Math.round((info.nextDouble()) * 100.00) / 100.00;
						
						while (principle < 0 && principle <= checkOrSaveAccAmountForLimit)
						{
							System.out.print("\r\n"
									+ "It would appear you have not entered a valid response, please try again: ");
							principle = Math.round((info.nextDouble()) * 100.00) / 100.00;
						}
						
						if (useCheckOrSaveAccForInvestment.equals("c"))
						{
							checkingAccAmount -= principle;
						} else if (useCheckOrSaveAccForInvestment.equals("s"))
						{
							savingsAccAmount -= principle;
						}
			
			// decide on investment time frame ====================================================================================================
			System.out.print("\r\n"
					+ " - Fourthly, please choose from our list of payment terms."
					+ "\r\n"
					+ "(with recieved payments and interest added to the principle every month)"
					+ "\r\n"
					+ "> 5 years at 4.75% interest"
					+ "\r\n"
					+ "> 10 years at 4.45% interest"
					+ "\r\n"
					+ "> 20 years at 4.70% interest"
					+ "\r\n"
					+ "> 30 years at 4.75% interest"
					+ "\r\n"
					+ "(Type 5 for 5 years, 10 for 10 years, 20 for 20 years, and 30 for 30 years): ");
			investmentTerm = info.nextInt();
			
		// check for valid period response
			if (investmentTerm == 5 || investmentTerm == 10 || investmentTerm == 20 || investmentTerm == 30)
			{
				investmentTermInputValid = true;
			} else
			{
				investmentTermInputValid = false;
			}
			
			while (investmentTermInputValid == false)
			{
				System.out.print("\r\n"
						+ "It would appear you have not entered a valid response, please try again: ");
				investmentTerm = info.nextInt();
				if (investmentTerm == 5 || investmentTerm == 10 || investmentTerm == 20 || investmentTerm == 30)
				{
					investmentTermInputValid = true;
				} else
				{
					investmentTermInputValid = false;
				}
			}
			
			// get interest rate
			if (investmentTerm == 5 || investmentTerm == 30)
			{
				interestRate = 0.0475;
			} else if (investmentTerm == 10)
			{
				interestRate = 0.0445;
			} else if (investmentTerm == 20)
			{
				interestRate = 0.0470;
			}
			
			// ask if they wish for the payments to start immediately or in one month ====================================================================================================
			System.out.print("\r\n"
					+ " - Lastly, would you like to recieve your first payment immediatly or in thirty days?"
					+ "\r\n"
					+ "(Respond with \"i\" for immediatly and \"t\" for thirty days): ");
			firstPaymentOption = info.next();
			
			// check for valid payment response
			if (firstPaymentOption.equals("i") || firstPaymentOption.equals("t"))
			{
				firstPaymentOptionInputValid = true;
			} else
			{
				firstPaymentOptionInputValid = false;
			}
		
		// if not valid, ask again till valid
		while (firstPaymentOptionInputValid == false)
		{
			System.out.print("\r\n"
					+ "It would appear you have not entered a valid response, please try again: ");
			firstPaymentOption = info.next();
			if (firstPaymentOption.equals("i") || firstPaymentOption.equals("t"))
			{
				firstPaymentOptionInputValid = true;
			} else
			{
				firstPaymentOptionInputValid = false;
			}
		}
		
		// set up i or t as the full word in a variable
		if (firstPaymentOption.equals("i"))
		{
			firstPaymentOptionFullWord = "Immediatly";
		} else if (firstPaymentOption.equals("t"))
		{
			firstPaymentOptionFullWord = "In Thirty Days";
		}
		
		// congratulate on creation of investment ====================================================================================================
		System.out.print("\r\n"
				+ "-------------------------"
				+ "\r\n"
				+ "You have successfully created your investment!"
				+ "\r\n"
				+ "We will now display the current info for your annuity investment!"
				+ "\r\n"
				+ " "
				+ "\r\n");
		
		// get non user inputed info about investment ====================================================================================================
		
		// get first payment date if immediate or get first payment date if in thirty days
		if (firstPaymentOption.equals("i"))
		{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");  
		    Date date = new Date();  
		    firstPaymentDate = formatter.format(date);
		    
		 } else if (firstPaymentOption.equals("t"))
		 {
			 DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			 Date currentDate = new Date();
			 Calendar c = Calendar.getInstance();
			 c.setTime(currentDate);
			 c.add(Calendar.DATE, 30);
			 Date currentDatePlusThirtyDays = c.getTime();
			 firstPaymentDate = dateFormat.format(currentDatePlusThirtyDays);
		 }
		
		// get monthly payment amount
		monthylPaymentInterestRateVariable = interestRate + 1;
		monthlyPaymentAmount = Math.round(( principle / ((1 - (Math.pow(monthylPaymentInterestRateVariable,(-60)))) / interestRate))* 100.00) / 100.00;
		
		// if "i" make first monthly payment
		if (firstPaymentOption.equals("i"))
		{
			if (useCheckOrSaveAccForPayment.equals("c"))
			{
				checkingAccAmount += monthlyPaymentAmount;
			} else if (useCheckOrSaveAccForPayment.equals("s"))
			{
				savingsAccAmount += monthlyPaymentAmount;
			}
		}
		
		System.out.print("~~~~~~~~~~"
				+ "\r\n"
				+ "> Principle: " + "$" + twoDecimalPlaces.format(principle)
				+ "\r\n"
				+ "> The Principle Ammount Will Be Taken From: " + useCheckOrSaveAccForInvestmentFullWord
				+ "\r\n"
				+ "> Term: " + investmentTerm + " Years"
				+ "\r\n"
				+ "> Interest Rate: " + twoDecimalPlaces.format((interestRate * 100)) + "%"
				+ "\r\n"
				+ "> First Payment Type: " + firstPaymentOptionFullWord
				+ "\r\n"
				+ "> Date of First Payment: " + firstPaymentDate + " (Format: Year, Month, Day, Hour, Minute, and Second)"
				+ "\r\n"
				+ "> Monthly Payment Amount: $" + twoDecimalPlaces.format(monthlyPaymentAmount)
				+ "\r\n"
				+ "> Payments Will Be Made To: " + useCheckOrSaveAccForPaymentFullWord
				+ "\r\n"
				+ "~~~~~~~~~~"
				+ "\r\n");
			
		// goodbye message ====================================================================================================
		System.out.print(" "
				+ "\r\n"
				+ "Have a great rest of your day!"
				+ "\r\n"
				+ "==================================================");
		
		// change false in main csv file to true ====================================================================================================
		mainCSVTFChange = "true";
		mainCSVTFCol = 22;
		modifyCSV("accounts.csv", mainCSVTFChange, mainCSVTFRow, mainCSVTFCol);
		
		// change checking or savings acc value in csv
		if (useCheckOrSaveAccForInvestment.equals("c") )
		{
			checkingAccAmount = Math.round((checkingAccAmount) * 100.00) / 100.00;
			checkingAccAmountString = Double.toString(checkingAccAmount);
			modifyCSV("checkingsaccount.csv", checkingAccAmountString, checkingsaccountCSVRow, 1);
		} else if (useCheckOrSaveAccForInvestment.equals("s"))
		{
			savingsAccAmount = Math.round((savingsAccAmount) * 100.00) / 100.00;
			savingsAccAmountString = Double.toString(savingsAccAmount);
			modifyCSV("savingsaccount.csv", savingsAccAmountString, savingsaccountCSVRow, 1);
		}
		
		// change checking or savings acc value in csv for payment
		if (useCheckOrSaveAccForPayment.equals("c") )
		{
			checkingAccAmount = Math.round((checkingAccAmount) * 100.00) / 100.00;
			checkingAccAmountString = Double.toString(checkingAccAmount);
			modifyCSV("checkingsaccount.csv", checkingAccAmountString, checkingsaccountCSVRow, 1);
		} else if (useCheckOrSaveAccForPayment.equals("s"))
		{
			savingsAccAmount = Math.round((savingsAccAmount) * 100.00) / 100.00;
			savingsAccAmountString = Double.toString(savingsAccAmount);
			modifyCSV("savingsaccount.csv", savingsAccAmountString, savingsaccountCSVRow, 1);
		}
		
		// add user to annuity investment csv file and create object ====================================================================================================
		
		//create object
		AnnuityInvestmentObject annuityInvestment = new AnnuityInvestmentObject(currentlyLoggedInUser, firstPaymentDate, useCheckOrSaveAccForInvestment, useCheckOrSaveAccForPayment, principle, monthlyPaymentAmount, interestRate, investmentTerm, firstPaymentOption);
		
		//method to add a row of information to a csv file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("Annuity Investment Users.csv", true))) //open the file with a second paramet thats true
            {
                // Writing data
                //technically each element in the array below should be data that the user inputs and is gotten through the Scanner
                String[] data = {annuityInvestment.getUser(), annuityInvestment.getfirstPaymentDate(), annuityInvestment.getRecentPaymentDate(), annuityInvestment.getNextPaymentDate(), annuityInvestment.getCheckOrSaveAccForInvestment(), annuityInvestment.getCheckOrSaveAccForPayments(), annuityInvestment.getStringPrinciple(), annuityInvestment.getStringNewBalance(), annuityInvestment.getStringMonthlyPaymentAmount(), annuityInvestment.getStringInterestRate(), annuityInvestment.getStringTerm(), annuityInvestment.getStringAmountOfPaymentsMadeIncludingFirst(), annuityInvestment.getStringAmountOfPaymentsLeft(), annuityInvestment.getStringHasInvestment()}; //have each piece of data in the row in an array
                String newRowline = String.join(", ", data); //put each object in the array into one line (mind the space after the comma)
                writer.write(newRowline); //write line
                writer.newLine();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
		
		// does not wish to create loan ====================================================================================================
		} else if (createInvestmentResponse.equals("n"))
		{
			// does not want to create loan response
			System.out.println("\r\n"
					+ "Have a great rest of your day, we hope to have you back soon."
					+ "\r\n"
					+ "==================================================");
		}
	}
		//if no checking or savings acc present, do not run program ====================================================================================================
	} else if (checkingAccMade == false && savingsAccMade == true)
	{
		System.out.print("=================================================="
				+ "\r\n"
				+ "Hello " + currentusersName + ","
				+ "\r\n"
				+ "unfortunately we cannot help you create an annuity investment since you are missing a checking account."
				+ "\r\n"
				+ "Please come back once you've created this account. "
				+ "\r\n"
				+ "We hope to have you back soon!"
				+ "\r\n"
				+ "==================================================");
	} else if (savingsAccMade == false && checkingAccMade == true)
	{
		System.out.print("=================================================="
				+ "\r\n"
				+ "Hello " + currentusersName + ","
				+ "\r\n"
				+ "unfortunately we cannot help you create an annuity investment since you are missing a savings account."
				+ "\r\n"
				+ "Please come back once you've created this account. "
				+ "\r\n"
				+ "We hope to have you back soon!"
				+ "\r\n"
				+ "==================================================");
	} else if (checkingAccMade == false && savingsAccMade == false)
	{
		System.out.print("=================================================="
				+ "\r\n"
				+ "Hello " + currentusersName + ","
				+ "\r\n"
				+ "unfortunately we cannot help you create an annuity investment since you are missing both a checking and savings account."
				+ "\r\n"
				+ "Please come back once you've created these accounts. "
				+ "\r\n"
				+ "We hope to have you back soon!"
				+ "\r\n"
				+ "==================================================");
	}
		// info.close(); -- Don't do this, apparently.
	}
	
	// modify csv method ====================================================================================================
	public static void modifyCSV(String fileName, String newValue, int row, int col) throws IOException
    {
    	//ALL OUTPUTS ARE DISPLAYED THROUGH THE READ () METHOD INSIDE THE MAIN METHOD
        File inputFile = new File(fileName);    //IN THIS CASE, data.csv
        List<String> fileContent = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile)))
        {
            String tempLine;
            int tempRow = 0;
            while ((tempLine = reader.readLine()) != null) //as longs as there are lines in the csv file, read each line one by one
            {
                if (tempRow == row) //if the row is the desired line from the function
                {
                    String[] newTempLine = tempLine.split(",\\s*");  //break up the line into an array and get rid of commas and whitespaces
                    if (col < newTempLine.length) //checks for sufficent index
                    {
                        newTempLine[col] = newValue;
                    }
                    tempLine = String.join(", ", newTempLine); //transform the array back into one line of a csv file with commas
                }
                fileContent.add(tempLine); //add the line to the array list of lines
                tempRow++;
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile)))
        {
            for (String line : fileContent) //for each line in the array list
            {
                writer.write(line); //write each line
                writer.newLine();
            }
        }
        //System.out.println("information in _fileContent_ ArrayList " + fileContent);

    }
	
	
}
