//imports --------------------------------------------------
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Date;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.lang.reflect.Method;

public class RealHomeEquityLoanClient {

	public static void main(String[] args) 
	{	  

// variable initilizations 
		LocalDate currentDate = LocalDate.now();
		String dateofLCreation = "1900-01-01";
		LocalDate dateofCreation = LocalDate.parse(dateofLCreation);
		String lastPaymentDate = "1900-01-01";
		LocalDate lastPayment = LocalDate.parse(lastPaymentDate);
		LocalDate nextPayment = lastPayment.plusMonths(1);
		String finalPaymentDate = "1900-01-01";
		LocalDate finalPayment = LocalDate.parse(finalPaymentDate);
		
		
		BankAccountSignup.BankAccount MyObj = new BankAccountSignup.BankAccount();
		String UserID = MyObj.getAccountId();
		
		String current = UserID;
		
		Scanner info = new Scanner(System.in);
		
		String createLoan = "";
		String line = "";  
		String splitBy = ",";
		
		double mortgage = 0.0;
		double homeValue = 0.0;
		double downPayment = 0.0;
		double loanAmount = 0.0;
		int loanPeriod = 0;
		double maxLoan = 0.0;
		double monthPayment = 0.0;
		double monthPaymentfinal = 0.0;
		int creditScore = 850;
		double checkingBal = 0.0;
		double savingsBal = 0.0;
		double interestRate = 0.0895;
		double amountPaid = 0.0;
		double totalLoans = 0.0;
		String paymentMethod = "1";
		
		int payOffTimeLimit = 0;
		
		String accountfile = "accounts.csv";
		String homeequityfile = "HomeEquityLoanClientInfo.csv";
		String checkingfile = "checkingsaccount.csv";
		String savingsfile = "savingsaccount.csv";
		String mortgagefile = "mortgage.csv";
		String creditscorefile = "ccard.csv";
		
		Boolean checkUser = false;
		String fname = null;
		String lname = null;
		Boolean checksave = false;
		Boolean checkchecking = false;
		Boolean checkmortgage = false;
		Boolean isCompleted = false;
		maxLoan = Math.round(((homeValue * .85) - mortgage) * 100.0) / 100.0;
		
		
// reads main csv file and checks to see if home equity loan value is true or false
		try   
		{   BufferedReader readMainCSV = new BufferedReader(
				new FileReader(accountfile));  
		while ((line = readMainCSV.readLine()) != null)    
			{  
				String[] currentUser = line.split(splitBy); 
				if(currentUser[0].equals(current)){
					checkUser = Boolean.valueOf(currentUser[16]);
					checksave = Boolean.valueOf(currentUser[13]);
					checkchecking = Boolean.valueOf(currentUser[18]);
					fname = currentUser[1];
					lname = currentUser[2];
				}
			}  
		}   
		catch (IOException e)   
		{  
			e.printStackTrace();  
		}  	
		try   
		{   BufferedReader readMortgageCSV = new BufferedReader(
				new FileReader(mortgagefile));  
		while ((line = readMortgageCSV.readLine()) != null)  
			{  
				String[] currentUser = line.split(splitBy); 
				if(currentUser[0].equals(current)){
					mortgage = Double.valueOf(currentUser[2]);
					downPayment = Double.valueOf(currentUser[3]);

				}
				
			}  
		}   
		catch (IOException e)   
		{  
			e.printStackTrace();  
		}  	
		try   
		{   BufferedReader readCreditCSV = new BufferedReader(
				new FileReader(creditscorefile));  
		while ((line = readCreditCSV.readLine()) != null)  
			{  
				String[] currentUser = line.split(splitBy); 
				if(currentUser[5].equals(current)){
					creditScore = Integer.valueOf(currentUser[1]);
				}
			}  
		}   
		catch (IOException e)   
		{  
			e.printStackTrace();  
		}  	
		if (mortgage > 0) {
			checkmortgage = true;
		}
		homeValue = mortgage + downPayment;
		maxLoan = Math.round(((homeValue * .85) - mortgage) * 100.0) / 100.0;
// reads home equity loan csv and checks values
		
// if user does not have a loan
		if (checkUser == false && checkmortgage == true && (checkchecking == true || checksave == true))
		{
		// if user does not have a loan 
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println("Hello " + fname + ", it would appear you do not have a Home Equity Loan. Would you like to create one? (Please answer with \"y\" or \"n\"): ");
             	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				createLoan = info.next();
				while(!(createLoan.equals("y") || createLoan.equals("yes") || createLoan.equals("Y") || createLoan.equals("n") || createLoan.equals("no") || createLoan.equals("N"))) {
					if (!(createLoan.equals("y") || createLoan.equals("yes") || createLoan.equals("Y") || createLoan.equals("n") || createLoan.equals("no") || createLoan.equals("N"))) {
							System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		                 	System.out.println("The answer you have typed is invalid. Please type another response: ");
		                 	createLoan = info.next();
		                 	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					}
				}
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		// wishes to create a loan 
				if (createLoan.equals("y") || createLoan.equals("yes") || createLoan.equals("Y"))
				{
					System.out.println("\r\n"
							+ "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + "\r\n"
							+ "Our loan policy includes the below listed rules: " + "\r\n"
							+ "\r\n"
							+ "Time to Pay Off: 5-30 Years" + "\r\n"
							+ "Payments: Monthly" + "\r\n"
							+ "Failure to Pay on Time: Interest Rate Increased By 0.1%" + "\r\n"
							+ "Interest Rate: 8.95% (unless a payment is missed)" + "\r\n"
							+ "FICO Score must be calculated to be greater than 620" + "\r\n"
							+ "Maximum Home Equity Loans: 1" + "\r\n"
							+ "Payment Begins: One Month After Loan Initialization" + "\r\n"
							+ "You have Until the Next Payment to Pay Off the First One" + "\r\n"
							+ "Payments Must be Paid through Bank Approved Checking or Savings Account" + "\r\n"
							+ "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					// find out how much of a loan the person would like to take out
					System.out.println("");
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					System.out.println("We'd like to begin with a few questions: " + "\r\n");
					System.out.print("How much money you wish to take out for this loan? - [Please enter a value above 0 and less than or equal to your maximum loan value of $" + (maxLoan) + "] " + "$");
					// round response to two decimal places in case they tryna be funny
					loanAmount = Math.round((Double.valueOf(info.next())) * 100.0) / 100.0;
					System.out.println("");
					while(0 > loanAmount || loanAmount > maxLoan) {
						if (0 > loanAmount || loanAmount > maxLoan) {
			                 	System.out.println("The Requested Loan Value(" + loanAmount + ") is too High/Low. The actual possible loan value is $" + maxLoan );
			                 	System.out.println("Please Enter a Lower Value.");
			                 	loanAmount = Math.round((info.nextDouble()) * 100.0) / 100.0;
						}
					}
					// find out how long the payoff period is
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					System.out.print("How long would you like to pay off this loan?: [Input must be a number between 5 and 30 (years)] Years = ");
					loanPeriod = info.nextInt();
					System.out.println("");
					while(30 < loanPeriod || loanPeriod < 5) {
						if (30 < loanPeriod || loanPeriod < 5) {
		                 	System.out.println("The Requested Loan Period(" + loanPeriod + " years) is too High/Low. You can only pay of the loan between 5 and 30 years" );
		                 	System.out.println("Please Enter a Different Value.");
		                 	loanPeriod = info.nextInt();

					}
				}
					 
					
		// find first payment date
					monthPayment = Math.round((((loanAmount) / (12 * loanPeriod))  )* 100.0) / 100.0;
					monthPaymentfinal = Math.round(((monthPayment * (1.0 + interestRate))  )* 100.0) / 100.0;
					if (creditScore >= 620) {
					payOffTimeLimit = loanPeriod * 12;
					lastPayment = currentDate;
					lastPaymentDate = lastPayment.toString();
					nextPayment = lastPayment.plusMonths(1);
					finalPayment = currentDate.plusYears(loanPeriod);
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					System.out.println("Congratulations! You have been approved for a Home Equity Loan!");
					System.out.println("Date Created : " + currentDate);
					System.out.println("The monthly payment would end to up being $" + monthPaymentfinal);
					System.out.println("Your next official payment is due " + nextPayment);
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					System.out.println("");
					try {
						modifyCSV(accountfile, "true", current, 16);
						writeRow(homeequityfile,current,fname,lname,Double.toString(homeValue),Double.toString(loanAmount),Double.toString(amountPaid),lastPaymentDate,currentDate.toString(),finalPayment.toString(),Integer.toString(loanPeriod),Integer.toString(payOffTimeLimit),Double.toString(monthPayment),Double.toString(interestRate),Boolean.toString(isCompleted));

					} catch (IOException e) {
						e.printStackTrace();
					}
					} else {
						System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
						System.out.println("                     LOAN NOT APPROVED");
						System.out.println("Sorry. You were not able to be approved for this Home Equity Loan");
						System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					}
		// when its time to pay for the loan
				}
		// does not wish to create a loan 
				else if (createLoan.equals("n") || createLoan.equals("no") || createLoan.equals("N"))
				{
					System.out.println("");
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					System.out.println("We hope to have you back soon, enjoy the rest of your day.");
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				}
				
				
			} else if (checkUser == true && (checkmortgage == true)) {
				
				
				// reads home equity loan csv and checks values
				try   
				{  
				//parsing a CSV file into BufferedReader class constructor  
				BufferedReader readHomeCSV = new BufferedReader(
						new FileReader(homeequityfile));  
				while ((line = readHomeCSV.readLine()) != null)   //returns a Boolean value  
					{  
						String[] currentUser = line.split(splitBy); 
						if(currentUser[0].equals(current)){
							loanAmount = Double.parseDouble(currentUser[4]);
							amountPaid = Double.parseDouble(currentUser[5]);
							lastPaymentDate = currentUser[6];
							dateofLCreation = currentUser[7];
							loanPeriod = Integer.parseInt(currentUser[9]);
							payOffTimeLimit = Integer.parseInt(currentUser[10]);
							monthPayment = Double.parseDouble(currentUser[11]);
							interestRate = Double.parseDouble(currentUser[12]);
						}
					}  
				}   
				catch (IOException e)   
				{  
					e.printStackTrace();  
				}  	
				if (checkchecking == true) {
					try   
					{  
					//parsing a CSV file into BufferedReader class constructor  
					BufferedReader readCheckingCSV = new BufferedReader(
							new FileReader(checkingfile));  
					while ((line = readCheckingCSV.readLine()) != null)   //returns a Boolean value  
						{  
							String[] currentUser = line.split(splitBy); 
							if(currentUser[0].equals(current)){
								checkingBal = Double.parseDouble(currentUser[1]);
							}
						}  
					}   
					catch (IOException e)   
					{  
						e.printStackTrace();  
					}  	
				}
				if (checksave == true) {
					try   
					{  
					//parsing a CSV file into BufferedReader class constructor  
					BufferedReader readSavingsCSV = new BufferedReader(
							new FileReader(savingsfile));  
					while ((line = readSavingsCSV.readLine()) != null)   //returns a Boolean value  
						{  
							String[] currentUser = line.split(splitBy); 
							if(currentUser[0].equals(current)){
								savingsBal = Double.parseDouble(currentUser[1]);
							}
						}  
					}   
					catch (IOException e)   
					{  
						e.printStackTrace();  
					}  	
				}
				Scanner info2 = new Scanner(System.in);
				double requiredPayment = 0.0;
				String paymentInfo = "";
				requiredPayment = monthPayment;
				lastPayment = LocalDate.parse(lastPaymentDate);
				nextPayment = lastPayment.plusMonths(1);
				dateofCreation = LocalDate.parse(dateofLCreation);
				int monthsbet =  Period.between(lastPayment, LocalDate.now()).getMonths();
				int yearsbet = Period.between(lastPayment,LocalDate.now()).getYears();
				int monthstotal = monthsbet + (yearsbet * 12);
				if ((monthstotal-1) >= 1) {
					interestRate = interestRate + ((monthstotal - 1) * 0.01);
				}
				monthPaymentfinal = Math.round((monthPayment * (1 + interestRate)) * 100.0) / 100.0;
				requiredPayment = monthPaymentfinal * monthstotal; 
				interestRate = Math.round((interestRate) * 10000.0) / 10000.0;
				checkingBal = Math.round((checkingBal) * 100.0) / 100.0;
				savingsBal = Math.round((savingsBal) * 100.0) / 100.0;
				requiredPayment = Math.round((requiredPayment) * 100.0) / 100.0;
				if (payOffTimeLimit > 0) {
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					System.out.println("Loan Info:");
					System.out.println("Loan Holder Name: " + fname + " " + lname);
					System.out.println("Account ID: " + current );
					System.out.println("Date Created: " + dateofLCreation);
					System.out.println("Current Monthly Payment: $" + monthPaymentfinal);
					System.out.println("Next Payment is Due: " + nextPayment);
					System.out.println("Current Remaining Payments: " + payOffTimeLimit);
					System.out.println("");
					System.out.println("AMOUNT DUE TODAY ---------------------- $" + requiredPayment);
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					if(monthstotal >= 1) {
						System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
						System.out.println("Your Payment is due now");
						System.out.println("You are required to pay $" + requiredPayment);
					if (monthstotal > 1) {
						System.out.println("You have been OVERDUE for " + monthstotal + " months.");
						System.out.println("Because of this, your penalty is an increased Interest Rate of " + interestRate * 100 + "%");
					}
						System.out.println("Please select the account below that you would like to take the payment from.(Please enter C for Checking or S for Savings if applicable)");
					if (checkchecking == true) {
						System.out.println("Checking Account Balance: $" + checkingBal);
					}
					if (checksave == true) {
						System.out.println("Savings Account Balance: $" + savingsBal);
					}
						System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				}
					paymentInfo = info2.nextLine();
						while (paymentMethod.equals("1")) {
							if ((paymentInfo.equals("s")|| paymentInfo.equals("S") )&& savingsBal > monthPaymentfinal && monthstotal >= 1){
								if (checksave = true) {
									paymentMethod = "savings";
									savingsBal = savingsBal - requiredPayment;
									amountPaid = amountPaid + requiredPayment;
									payOffTimeLimit = payOffTimeLimit - monthstotal;
									savingsBal = Math.round((savingsBal) * 100.0) / 100.0;
									lastPayment = dateofCreation.plusMonths((loanPeriod*12) - payOffTimeLimit);
									System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
									System.out.println("The payment was taken from your Savings Account. Current Balance: $" + savingsBal);
									System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
								}
							} 
							else if ((paymentInfo.equals("c") || paymentInfo.equals("C"))&& checkingBal > monthPaymentfinal && monthstotal >= 1) {
								if (checkchecking = true) {
									paymentMethod = "checking";
									checkingBal = checkingBal - requiredPayment;
									amountPaid = amountPaid + requiredPayment;
									payOffTimeLimit = payOffTimeLimit - monthstotal;
									checkingBal = Math.round((checkingBal) * 100.0) / 100.0;
									lastPayment = LocalDate.now();
									System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
									System.out.println("The payment was taken from your Checking Account. Current Balance: $" + checkingBal);
									System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
								}
							} else {
								System.out.println("It appears that the account you've selected is invalid / has insufficient funds. Please select another account.");
								paymentInfo = info2.nextLine();
							}
							try {
								modifyCSV(checkingfile,Double.toString(checkingBal),current,1);
								modifyCSV(savingsfile,Double.toString(savingsBal),current,1);
								modifyCSV(homeequityfile,Double.toString(amountPaid),current,5);
								modifyCSV(homeequityfile,lastPayment.toString(),current,6);
								modifyCSV(homeequityfile,Integer.toString(payOffTimeLimit),current,10);
								modifyCSV(homeequityfile,Double.toString(interestRate),current,12);
								
							} catch (IOException e) {
								e.printStackTrace();
							}
						} 
					} else if (payOffTimeLimit == 0 && checkUser == true) {
							System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
							System.out.println("Loan Info:");
							System.out.println("Loan Holder Name: " + fname + " " + lname);
							System.out.println("Account ID: " + current );
							System.out.println("Date Created: " + dateofLCreation);
							System.out.println("Current Monthly Payment: COMPLETED");
							System.out.println("Current Remaining Payments: " + payOffTimeLimit);
							System.out.println("Amount Paid: " + Math.round((amountPaid) * 100.0) / 100.0);
							System.out.println("-----------------  LOAN PAYMENT COMPLETED -----------------");
							System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
							try {
								modifyCSV(homeequityfile,"true",current,13);
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					} else if (checkmortgage == false){
						System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
						System.out.println("It appears that there is no house saved in our systems. Please contact the bank if this is an error.");
						System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					} else if (checksave == false & checkchecking == false) {
						System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
						System.out.println("It appears that there is no savings/checkings account in our system. Please contact the bank if this is a error");
						System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					}
			}
	public static void writeRow(String fileName, String accountNum, String fname, String lname, String HomeValue, String LoanAmount, String AmountPaid, String LastPaymentDate, String DateOfCreation, String FinalPaymentDate, String LoanPeriod, String TimeLeftPayOff, String PaymentAmount, String InterestRate, String IsCompleted) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) // open the file with a second
																							
		{
			// Writing data
			// technically each element in the array below should be data that the user
			// inputs and is gotten through the Scanner
			String[] data = { accountNum, fname, lname, HomeValue, LoanAmount, AmountPaid, LastPaymentDate, DateOfCreation,FinalPaymentDate,LoanPeriod,TimeLeftPayOff,PaymentAmount,InterestRate, IsCompleted}; // have each piece of data in the row in an array
			String line = String.join(",", data); // put each object in the array into one line (mind the space after
													// the comma)
			writer.write(line); // write line
			writer.newLine();

			System.out.println("Your Loan Info has been saved");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void modifyCSV(String fileName, String newNameorNumber, String currentUser, int col) throws IOException {
		// ALL OUTPUTS ARE DISPLAYED THROUGH THE READ () METHOD INSIDE THE MAIN METHOD
		File inputFile = new File(fileName); // IN THIS CASE, data.csv
		List<String> fileContent = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
			String tempLine;
			int tempRow = 0;
			while ((tempLine = reader.readLine()) != null) // as longs as there are lines in the csv file, read each
															// line one by one
			{
				if (tempLine.contains(currentUser)) // if the row is the desired line from the function
				{
					String[] newTempLine = tempLine.split(",\\s*"); // break up the line into an array and get rid of
																	// commas and whitespaces
					if (col < newTempLine.length) // checks for sufficent index
					{
						newTempLine[col] = newNameorNumber;
					}
					tempLine = String.join(",", newTempLine); // transform the array back into one line of a csv file
																// with commas
				}
				fileContent.add(tempLine); // add the line to the array list of lines
				tempRow++;
			}
		}
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile))) {
			for (String line : fileContent) // for each line in the array list
			{
				writer.write(line); // write each line
				writer.newLine();
			}
		}
	}
}
