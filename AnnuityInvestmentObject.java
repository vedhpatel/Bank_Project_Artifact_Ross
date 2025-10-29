import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;
import java.text.DecimalFormat;

public class AnnuityInvestmentObject 
{
	// instance variables (listed in order that will be displayed in csv file) ====================================================================================================
	private String user;
	private String firstPaymentDate;
	private String recentPaymentDate;
	private String nextPaymentDate;
	private String checkOrSaveAccForInvestment;
	private String checkOrSaveAccForPayments;
	
	private double principle;
	private double newBalance;
	private double monthlyPaymentAmount;
	private double interestRate;
	
	private int term;
	private int amountOfPaymentsMadeIncludingFirst;
	private int amountOfPaymentsLeft;
	
	private Boolean hasInvestment;
	
	// default constructor ====================================================================================================
	public AnnuityInvestmentObject()
	{
		user = "unknown";
		firstPaymentDate = "unknown";
		recentPaymentDate = "unknown";
		nextPaymentDate = "unknown";
		checkOrSaveAccForInvestment = "unknown";
		checkOrSaveAccForPayments = "unknown";
		
		principle = 0.0;
		newBalance = 0.0;
		monthlyPaymentAmount = 0.0;
		interestRate = 0.0;
		
		term = 0;
		amountOfPaymentsMadeIncludingFirst = 0;
		amountOfPaymentsLeft = 0;
		
		hasInvestment = false;
	}
	
	// overloaded constructor (new investment) ====================================================================================================
	public AnnuityInvestmentObject(String user, String firstPaymentDate, String checkOrSaveAccForInvestment, String checkOrSaveAccForPayments, double principle, double monthlyPaymentAmount, double interestRate, int term, String firstPaymentType)
	{
		this.user = user;
		this.firstPaymentDate = firstPaymentDate;
		this.checkOrSaveAccForInvestment = checkOrSaveAccForInvestment;
		this.checkOrSaveAccForPayments = checkOrSaveAccForPayments;
		
		this.principle = principle;
		this.monthlyPaymentAmount = monthlyPaymentAmount;
		this.interestRate = interestRate;
		
		this.term = term;
		amountOfPaymentsMadeIncludingFirst = 1;
		
		hasInvestment = true;
		
		// VARIABLES NOT GIVEN
			// (RECENT PAYMENT DATE) if first payment was immediate, recent payment will be first payment, if in thirty days, recent payment will be none
				if (firstPaymentType.equals("i"))
				{
					recentPaymentDate = firstPaymentDate;
				} else if (firstPaymentType.equals("t"))
				{
					recentPaymentDate = "None";
				}
			// (NEXT PAYMENT DATE) if first payment was immediate, next payment will be in thirty days, if in thirty days, next payment will refer to first payment
				if (firstPaymentType.equals("i"))
				{
					 DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					 Date currentDate = new Date();
					 Calendar c = Calendar.getInstance();
					 c.setTime(currentDate);
					 c.add(Calendar.DATE, 30);
					 Date currentDatePlusThirtyDays = c.getTime();
					 nextPaymentDate = dateFormat.format(currentDatePlusThirtyDays);
				} else if (firstPaymentType.equals("t"))
				{
					nextPaymentDate = firstPaymentDate;
				}
				
			// (NEW BALANCE) if first payment was immediate, new balance will be calculated, if in thirty days, new balance will be zero
				if (firstPaymentType.equals("i"))
				{
					newBalance = Math.round((principle + (principle * interestRate)) * 100.00) / 100.00;
				} else if (firstPaymentType.equals("t"))
				{
					newBalance = principle;
				}
			// (AMOUNT OF PAYMENTS LEFT) if first payment was immediate, amount of payments left will be term times 12 minus 1, if in thirty days, amount of payments left will be term times 12
				if (firstPaymentType.equals("i"))
				{
					amountOfPaymentsLeft = (term * 12) - 1;
				} else if (firstPaymentType.equals("t"))
				{
					amountOfPaymentsLeft = term * 12;
				}
	}
	
	// getters ====================================================================================================
	// string getters
	public String getUser()
	{
		return user;
	}
	public String getfirstPaymentDate()
	{
		return firstPaymentDate;
	}
	public String getRecentPaymentDate()
	{
		return recentPaymentDate;
	}
	public String getNextPaymentDate()
	{
		return nextPaymentDate;
	}
	public String getCheckOrSaveAccForInvestment()
	{
		return checkOrSaveAccForInvestment;
	}
	public String getCheckOrSaveAccForPayments()
	{
		return checkOrSaveAccForPayments;
	}
	
	// double getters and double string getters
	public double getPrinciple()
	{
		return principle;
	}
	public String getStringPrinciple()
	{
		String principle = String.valueOf(this.principle);
		return principle;
	}
	
	public double getNewBalance()
	{
		return newBalance;
	}
	public String getStringNewBalance()
	{
		String newBalance = String.valueOf(this.newBalance);
		return newBalance;
	}
	
	public double getMonthlyPaymentAmount()
	{
		return monthlyPaymentAmount;
	}
	public String getStringMonthlyPaymentAmount()
	{
		String monthlyPaymentAmount = String.valueOf(this.monthlyPaymentAmount);
		return monthlyPaymentAmount;
	}
	
	public double getInterestRate()
	{
		return interestRate;
	}
	public String getStringInterestRate()
	{
		String interestRate = String.valueOf(this.interestRate);
		return interestRate;
	}
	
	// int getters and int string getters
	public int getTerm()
	{
		return term;
	}
	public String getStringTerm()
	{
		String term = String.valueOf(this.term);
		return term;
	}
	
	public int getAmountOfPaymentsMadeIncludingFirst()
	{
		return amountOfPaymentsMadeIncludingFirst;
	}
	public String getStringAmountOfPaymentsMadeIncludingFirst()
	{
		String amountOfPaymentsMadeIncludingFirst = String.valueOf(this.amountOfPaymentsMadeIncludingFirst);
		return amountOfPaymentsMadeIncludingFirst;
	}
	
	public int getAmountOfPaymentsLeft()
	{
		return amountOfPaymentsLeft;
	}
	public String getStringAmountOfPaymentsLeft()
	{
		String amountOfPaymentsLeft = String.valueOf(this.amountOfPaymentsLeft);
		return amountOfPaymentsLeft;
	}
	
	// boolean getter and string boolean getter
	public Boolean getHasInvestment()
	{
		return hasInvestment;
	}
	public String getStringHasInvestment()
	{
		String hasInvestment = String.valueOf(this.hasInvestment);
		return hasInvestment;
	}
	
	// for users viewing
	public void getDisplayAttributes()
	{
		DecimalFormat twoDecimalPlaces = new DecimalFormat("0.00");
		
		String checkOrSaveAccForPaymentsFullWord = "";
		
		if (checkOrSaveAccForPayments.equals("c"))
		{
			checkOrSaveAccForPaymentsFullWord = "Checking Account";
		} else if (checkOrSaveAccForPayments.equals("s"))
		{
			checkOrSaveAccForPaymentsFullWord = "Savings Account";
		}		
		System.out.print("\r\n"
				+ "~~~~~~~~~~"
				+ "\r\n"
				+ "> Principle: $" + twoDecimalPlaces.format(principle)
				+ "\r\n"
				+ "> New Balance: $" + twoDecimalPlaces.format(newBalance)
				+ "\r\n"
				+ "> Monthly Payment Amount: $" + twoDecimalPlaces.format(monthlyPaymentAmount)
				+ "\r\n"
				+ "> Payments Will Go To: " + checkOrSaveAccForPaymentsFullWord
				+ "\r\n"
				+ "> Term: " + term + " Years"
				+ "\r\n"
				+ "> Interest Rate: " + twoDecimalPlaces.format((interestRate * 100)) + "%"
				+ "\r\n"
				+ "> Amount Of Payments Left: " + amountOfPaymentsLeft
				+ "\r\n"
				+ "> Next Payment Date: " + nextPaymentDate + " (Format: Year, Month, Day, Hour, Minute, and Second)"
				+ "\r\n"
				+ "~~~~~~~~~~"
				+ "\r\n");	
	}
	
	// for programmers viewing
	public void getAllAttributes()
	{
		System.out.print("\r\n"
				+ "~~~~~~~~~~"
				+ "\r\n"
				+ "> User: " + user
				+ "\r\n"
				+ "> First Payment Date: " + firstPaymentDate
				+ "\r\n"
				+ "> Recent Payment Date: " + recentPaymentDate
				+ "\r\n"
				+ "> Next Payment Date: " + nextPaymentDate
				+ "\r\n"
				+ "> Check Or Save Account For Investment: " + checkOrSaveAccForInvestment
				+ "\r\n"
				+ "> Check Or Save Account For Payments: " + checkOrSaveAccForPayments
				+ "\r\n"
				+ "> Principle: " + principle
				+ "\r\n"
				+ "> New Balance: " + newBalance
				+ "\r\n"
				+ "> Monthyl Payment Amount: " + monthlyPaymentAmount
				+ "\r\n"
				+ "> Interest Rate: " + interestRate
				+ "\r\n"
				+ "> Term: " + term
				+ "\r\n"
				+ "> Amount Of Payments Made Including First: " + amountOfPaymentsMadeIncludingFirst
				+ "\r\n"
				+ "> Amount Of Payments Left: " + amountOfPaymentsLeft
				+ "\r\n"
				+ "~~~~~~~~~~");
	}
	
	// setters ====================================================================================================
	public void setUser(String user)
	{
		this.user = user;
	}
	public void setFirstPaymentDate(String firstPaymentDate)
	{
		this.firstPaymentDate = firstPaymentDate;
	}
	public void setRecentPaymentDate(String recentPaymentDate)
	{
		this.recentPaymentDate = recentPaymentDate;
	}
	public void setNextPaymentDate(String nextPaymentDate)
	{
		this.nextPaymentDate = nextPaymentDate;
	}
	public void setCheckOrSaveAccForInvestment(String checkOrSaveAccForInvestment)
	{
		this.checkOrSaveAccForInvestment = checkOrSaveAccForInvestment;
	}
	public void setCheckOrSaveAccForPayments(String checkOrSaveAccForPayments)
	{
		this.checkOrSaveAccForPayments = checkOrSaveAccForPayments;
	}
	
	public void setPrinciple(double principle)
	{
		this.principle = principle;
	}
	public void setNewBalance(double newBalance)
	{
		this.newBalance = newBalance;
	}
	public void setMonthlyPamymentAmount(double monthlyPaymentAmount)
	{
		this.monthlyPaymentAmount = monthlyPaymentAmount;
	}
	public void setInterestRate(double interestRate)
	{
		this.interestRate = interestRate;
	}
	
	public void setTerm(int term)
	{
		this.term = term;
	}
	public void setAmountOfPaymentsMadeIncludingFirst(int amountOfPaymentsMadeIncludingFirst)
	{
		this.amountOfPaymentsMadeIncludingFirst = amountOfPaymentsMadeIncludingFirst;
	}
	public void setAmountOfPaymentsLeft(int amountOfPaymentsLeft)
	{
		this.amountOfPaymentsLeft = amountOfPaymentsLeft;
	}
	
	public void setHasInvestment(boolean hasInvestment)
	{
		this.hasInvestment = hasInvestment;
	}
	
	// special methods ====================================================================================================
	 // old investment info into new object
	public void reCreateAnnuityInvestmentObject(String userCSV, String firstPaymentDateCSV, String recentPaymentDateCSV, String nextPaymentDateCSV, String checkOrSaveAccForInvestmentCSV, String checkOrSaveAccForPaymentsCSV, double principleCSV, double newBalanceCSV, double monthlyPaymentAmountCSV, double interestRateCSV, int termCSV, int amountOfPaymentsMadeIncludingFirstCSV, int amountOfPaymentsLeftCSV, Boolean hasInvestmentCSV)
	{
		// strings
		user = userCSV;
		firstPaymentDate = firstPaymentDateCSV;
		recentPaymentDate = recentPaymentDateCSV;
		nextPaymentDate = nextPaymentDateCSV;
		checkOrSaveAccForInvestment = checkOrSaveAccForInvestmentCSV;
		checkOrSaveAccForPayments = checkOrSaveAccForPaymentsCSV;
		
		// doubles
		principle = principleCSV;
		newBalance = newBalanceCSV;
		monthlyPaymentAmount = monthlyPaymentAmountCSV;
		interestRate = interestRateCSV;
		
		// integers
		term = termCSV;
		amountOfPaymentsMadeIncludingFirst = amountOfPaymentsMadeIncludingFirstCSV;
		amountOfPaymentsLeft = amountOfPaymentsLeftCSV;
		
		// booleans
		hasInvestment = hasInvestmentCSV;
	}
	
	// perform required maintenance to existing investment variables ====================================================================================================
	public double existingInvestmentVariableMaintenance(double paymentAccAmount) throws ParseException
	{  
		// create needed variables
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");  
		
	    Date currentDate = new Date();
	    Date nextDate = new Date();
	    
		String differenceInTime = "";
		String currentDateString = "";
		String currentDateNumberOnlyString = "";
		String nextPaymentDateNumberOnlyString = "";
		
		long currentDateLong = 0;
		long nextPaymentDateLong = 0;
		
		int monthsPassed = 0;
		
		// change recent and next payment dates into numeric values
		currentDateString = formatter.format(currentDate);
		currentDate = formatter.parse(currentDateString);
		currentDateNumberOnlyString = (((formatter.format(currentDate).replace(" ", "")).replace("/", "")).replace(":", ""));
		currentDateLong = Long.parseLong(currentDateNumberOnlyString);
		
		nextDate = formatter.parse(nextPaymentDate);
		nextPaymentDateNumberOnlyString = (((formatter.format(nextDate).replace(" ", "")).replace("/", "")).replace(":", ""));
		nextPaymentDateLong = Long.parseLong(nextPaymentDateNumberOnlyString);
		
		// if recent payment date later than next payment date, run "findDateTimeDifference" code with starting variables fliped, if not, run normally
		if (currentDateLong > nextPaymentDateLong)
		{
			differenceInTime = findDateTimeDifference(nextPaymentDate, currentDateString);
			
			// adjust variables depending on how many payments were missed
			monthsPassed = (int)((Integer.parseInt(differenceInTime.substring(9, 11))) / 30);
			
			recentPaymentDate = currentDateString;
			
			 Date currentDateCopy = new Date();
			 Calendar c = Calendar.getInstance();
			 c.setTime(currentDateCopy);
			 c.add(Calendar.DATE, 30);
			 Date currentDatePlusThirtyDays = c.getTime();
			 nextPaymentDate = formatter.format(currentDatePlusThirtyDays);
			 
			 for (int i = 0; i <= monthsPassed; i++)
			 {
				 newBalance = Math.round((newBalance + (newBalance * interestRate)) * 100.00) / 100.00;
				 
				 amountOfPaymentsMadeIncludingFirst += monthsPassed;
				 
				 amountOfPaymentsLeft -= monthsPassed;
				 
				 paymentAccAmount += monthlyPaymentAmount;
			 }
			 
		} else if (currentDateLong < nextPaymentDateLong)
		{
			// nothing needs to be done since the user hasn't reached their next payment date yet
		}
		return paymentAccAmount;
	}
	
	// find difference between two dates ====================================================================================================
	static String findDateTimeDifference(String start_date, String end_date)
	{
		String differenceInTime = "";
		// SimpleDateFormat converts the string format to date object
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

		// Try Class
		try {

			// parse method is used to parse the text from a string to produce the date
			Date d1 = sdf.parse(start_date);
			Date d2 = sdf.parse(end_date);

			//Calculate time difference in milliseconds
			long difference_In_Time = d2.getTime() - d1.getTime();

			//Calculate time difference in seconds, minutes, hours, years, and days
			long difference_In_Seconds = TimeUnit.MILLISECONDS.toSeconds(difference_In_Time) % 60;

			long difference_In_Minutes = TimeUnit.MILLISECONDS.toMinutes(difference_In_Time) % 60;

			long difference_In_Hours = TimeUnit.MILLISECONDS.toHours(difference_In_Time) % 24;

			long difference_In_Days = TimeUnit.MILLISECONDS.toDays(difference_In_Time) % 365;

			long difference_In_Years = TimeUnit.MILLISECONDS.toDays(difference_In_Time) / 365l;
			
			// make a display variable for time between the two dates
			differenceInTime = (difference_In_Years + " years, " + difference_In_Days + " days, " + difference_In_Hours + " hours, " + difference_In_Minutes + " minutes, " + difference_In_Seconds + " seconds");
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		return differenceInTime;
	}
 
	
}