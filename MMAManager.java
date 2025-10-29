package bankProject;
import java.util.*; 
import java.time.LocalTime;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;  


public class MMAManager 
{
	public static final String  MMA_ACCOUNT_FILE = "mmaAccounts.csv";
	public static final String  MAIN_ACCOUNT_FILE = "accounts.csv";
	
	private static int readMainCSV(String fileName, String loggedAccount, String[] mainAccount)
    {
		int count = 0;
        String line = "";
        String splitBy = ",";
            
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName)))
        {
            System.out.println("\nReading data from " + fileName + ":");
            while ((line = reader.readLine()) != null)
            {
                String[] user = line.split(splitBy);
                if(user[0].equals(loggedAccount))
            	{
                	for (int i=0; i<user.length; i++)
                	{
                		mainAccount[i] = user[i];
                	}
            		return count;
            	}
                count++;
            }
        }       
        catch (IOException e)
        {
            e.printStackTrace();
        }     
        return -1;
    }
	
	private static void modifyCSV(String fileName, String newNameorNumber, int row, int col) throws IOException
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
                        newTempLine[col] = newNameorNumber;
                    }
                    tempLine = String.join(", ", newTempLine); //transform the array back into one line of a csv file with commas
                }
                fileContent.add(tempLine); //add the line to the array list of lines
                tempRow++;
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false))) 
	    {
	    	 File file = new File(fileName);
	    	 for (int i = 0; i < fileContent.size(); i++)
	    	 {
	    		 writer.write(fileContent.get(i) + "\n");
	    	 }
	         writer.close();
	     }
	     catch (IOException e) 
	     {
	    	 e.printStackTrace();
	     }
    }

	 private static void writeMMAAccountToCSV(MoneyMarketAccount account) 
	 {
		 // Write account information to CSV file
	     try (BufferedWriter writer = new BufferedWriter(new FileWriter(MMA_ACCOUNT_FILE, true))) 
	     {
	    	 File file = new File(MMA_ACCOUNT_FILE);
	         writer.write(account.toCSV() + "\n");
	         writer.close();
	     }
	     catch (IOException e) 
	     {
	    	 e.printStackTrace();
	     }
	 }
	 
	 private static MoneyMarketAccount accountCreation(String accountId, int idx) throws IOException
	 {
		 	MoneyMarketAccount mmaV = new MoneyMarketAccount();
		 	mmaV.setAccountId(accountId);
			Scanner keyboard = new Scanner(System.in);
			System.out.println("Welcome to the creation of your money market account");
			System.out.println("A $5000 minimum deposit is needed to open the account. How much money would you like to deposit?");	
			double initialDep = keyboard.nextDouble(); //
			while(initialDep < 5000)
			{
				System.out.println("Insufficient amount. Please deposit money again.");
				System.out.println("A $5000 minimum deposit is needed to open the account. How much money would you like to deposit?");
				initialDep = keyboard.nextDouble();
			}
			
			mmaV.deposit(initialDep);
			writeMMAAccountToCSV(mmaV);
			modifyCSV(MAIN_ACCOUNT_FILE, "TRUE", idx, 14);
			System.out.println("Your account has succesfully been created.");
			return mmaV;
			
	 }

	private static List<MoneyMarketAccount> CsvToArrayList()
	{
		MoneyMarketAccount mmaV = new MoneyMarketAccount();
	    List<MoneyMarketAccount> mmaUserAccounts = new ArrayList<>();
	    String line = "";
    	String splitBy = ",";
	    
	    try (BufferedReader br = new BufferedReader(new FileReader(MMA_ACCOUNT_FILE))) {
	    	while ((line = br.readLine()) != null) 
	        {
	        	String[] acc = line.split(splitBy);
	        	String accountId = acc[0];
				Double balance  = Double.parseDouble(acc[1]);
				Double interestAmount = Double.parseDouble(acc[2]);
				Double deposit =Double.parseDouble(acc[3]);
				int countWithdraw = Integer.parseInt(acc[4]);
				mmaUserAccounts.add(new MoneyMarketAccount(accountId, balance, interestAmount, deposit, countWithdraw));
	        }
	    } 
	    catch (Exception e) 
	    {
	    	e.printStackTrace();
	    }
	    //for(int i = 0; i < mmaUserAccounts.size(); i++)
	   //{
	    	//System.out.println(mmaUserAccounts.get(i).toCSV());
	    //}
	    return mmaUserAccounts;
	}

	 private static void writeAccountListToCSV(List<MoneyMarketAccount> accts) 
	 {
		 // Write account information to CSV file
	     try (BufferedWriter writer = new BufferedWriter(new FileWriter(MMA_ACCOUNT_FILE, false))) 
	     {
	    	 File file = new File(MMA_ACCOUNT_FILE);
	    	 for (int i = 0; i < accts.size(); i++)
	    	 {
	    		 MoneyMarketAccount acc = accts.get(i);
	    		 writer.write(acc.toCSV() + "\n");
	    	 }
	         writer.close();
	     }
	     catch (IOException e) 
	     {
	    	 e.printStackTrace();
	     }
	 }
	 
	 private static void dailyInterest(MoneyMarketAccount mmaV)
	 {
		 if(LocalTime.now() == LocalTime.MIDNIGHT)
		 {
			 mmaV.calculateDailyInterest(mmaV.getBalance());
		 }
	 }
	 
	 private static void payInterest(MoneyMarketAccount mmaV)
	 {
		 if(mmaV.getDayOfMonth() == 1 && LocalTime.now() == LocalTime.MIDNIGHT)
			{
				//Pays interest money at the end of the month
				mmaV.monthlyFee(mmaV.getBalance());
				mmaV.payOut(mmaV.getBalance());
				//Sets the withdrawal amount to 0 for the new month
				mmaV.setCountWithdraw(0);
			}
	 }
	 
	public void process(String loggedAccount)
	{
		try {
		    //String loggedAccount = MainMenu.loggedInAccountId;
		    
			String[] mainAccount = new String[18];
			int mainAccountIdx = readMainCSV(MAIN_ACCOUNT_FILE, loggedAccount, mainAccount);
			if (mainAccountIdx < 0)
			{
				System.out.println("Account does not exist");
				return;
			}
				
			MoneyMarketAccount mmaV = new MoneyMarketAccount();
			if(mainAccount[14].trim().equals("FALSE"))
			{
				mmaV = accountCreation(mainAccount[0], mainAccountIdx);			
			} 
	
			List<MoneyMarketAccount> mmaAccountsList = CsvToArrayList();
			int mmaidx = -1;
			for(int i = 0; i < mmaAccountsList.size(); i++)
			{
				MoneyMarketAccount acc = mmaAccountsList.get(i);
				if(acc.getAccountId().equals(loggedAccount))
				{
					mmaV = acc;
					mmaidx = i;
					break;
				}
			}
			//int mmaidx = findLoggedMMAAccount(mmaAccountsList, loggedAccount, mmaV);
			if (mmaidx < 0)
			{
				System.out.println("MMA Account does not exist");
				return;
			}
			
			//compounds interest daily
			dailyInterest(mmaV);
			//Pays interest at the beginning of the new month
			payInterest(mmaV);
	
			Scanner keyboard = new Scanner(System.in);
			
			int decision = 1;
			int decisionIterate = 0;
			int accountCloseRepeat = 0;
			System.out.println("Welcome to your money market account");
			System.out.println("Current balance: $" + mmaV.getBalance());
			
			//Deposits, or withdraws depending on what the user chose
			//while(decision == 1 || decision == 2 || decision == 3) 
			while(true)
			{
					System.out.println("Would you like to withdraw money, deposit money, go back to the main menu, or close the account?");
					System.out.println("Type 1 to withdraw\n" + "Type 2 to deposit\n" + "Type 3 to close the account\n" + "Type any other number to leave.");
	
				 	accountCloseRepeat = 0;
					decision = keyboard.nextInt();
					if(decision == 1)
					{
						System.out.println("You have withdrawn money " + mmaV.getCountWithdraw() + " times this month");
						System.out.println("A fee of $10 will be added after 6 withdrawals");
						System.out.println("How much money would you like to withdraw?");
						double withdrawAmount = keyboard.nextDouble();
						String msg = mmaV.withdraw(withdrawAmount);
						System.out.println(msg);
						System.out.println("Current balance: $" + mmaV.getBalance());
						writeAccountListToCSV(mmaAccountsList);
					}	
					else if(decision == 2)
					{
						System.out.println("How much money would you like to deposit?");
						double depositAmount = keyboard.nextDouble();
						mmaV.deposit(depositAmount);
						System.out.println("Current balance: $" + mmaV.getBalance());
						writeAccountListToCSV(mmaAccountsList);
					}
					else if(decision == 3)
					{
						//Create a new method, or make accountClose equal something
						while(accountCloseRepeat == 0)
						{
							System.out.println("Are you sure you want to close the account?[Yes/No]");
							String accountClose = keyboard.next();
							if(accountClose.toLowerCase().equals("yes"))
							{
								System.out.println("Withdrawing the remaining balance");
								mmaV.withdraw(mmaV.getBalance());
								mmaAccountsList.remove(mmaidx);
								modifyCSV(MAIN_ACCOUNT_FILE, "FALSE", mainAccountIdx, 14);
								writeAccountListToCSV(mmaAccountsList);
								return;
							}
							else if(accountClose.toLowerCase().equals("no"))
							{
								break;
							}
							else
							{
								System.out.println("Invalid Option");
								continue;
							}
						}
					}
					else 
					{
						return;
					}
					
				}
		}
		catch (Exception e) {
			System.out.println(e.getStackTrace());
		}

	}

	public void process()
	{
		try {
		        String loggedAccount = MainMenu.loggedInAccountId;
		    
			String[] mainAccount = new String[18];
			int mainAccountIdx = readMainCSV(MAIN_ACCOUNT_FILE, loggedAccount, mainAccount);
			if (mainAccountIdx < 0)
			{
				System.out.println("Account does not exist");
				return;
			}
				
			MoneyMarketAccount mmaV = new MoneyMarketAccount();
			if(mainAccount[14].trim().equals("FALSE"))
			{
				mmaV = accountCreation(mainAccount[0], mainAccountIdx);			
			} 
	
			List<MoneyMarketAccount> mmaAccountsList = CsvToArrayList();
			int mmaidx = -1;
			for(int i = 0; i < mmaAccountsList.size(); i++)
			{
				MoneyMarketAccount acc = mmaAccountsList.get(i);
				if(acc.getAccountId().equals(loggedAccount))
				{
					mmaV = acc;
					mmaidx = i;
					break;
				}
			}
			//int mmaidx = findLoggedMMAAccount(mmaAccountsList, loggedAccount, mmaV);
			if (mmaidx < 0)
			{
				System.out.println("MMA Account does not exist");
				return;
			}
			
			//compounds interest daily
			dailyInterest(mmaV);
			//Pays interest at the beginning of the new month
			payInterest(mmaV);
	
			Scanner keyboard = new Scanner(System.in);
			
			int decision = 1;
			int decisionIterate = 0;
			int accountCloseRepeat = 0;
			System.out.println("Welcome to your money market account");
			System.out.println("Current balance: $" + mmaV.getBalance());
			
			//Deposits, or withdraws depending on what the user chose
			//while(decision == 1 || decision == 2 || decision == 3) 
			while(true)
			{
					System.out.println("Would you like to withdraw money, deposit money, go back to the main menu, or close the account?");
					System.out.println("Type 1 to withdraw\n" + "Type 2 to deposit\n" + "Type 3 to close the account\n" + "Type any other number to leave.");
	
				 	accountCloseRepeat = 0;
					decision = keyboard.nextInt();
					if(decision == 1)
					{
						System.out.println("You have withdrawn money " + mmaV.getCountWithdraw() + " times this month");
						System.out.println("A fee of $10 will be added after 6 withdrawals");
						System.out.println("How much money would you like to withdraw?");
						double withdrawAmount = keyboard.nextDouble();
						String msg = mmaV.withdraw(withdrawAmount);
						System.out.println(msg);
						System.out.println("Current balance: $" + mmaV.getBalance());
						writeAccountListToCSV(mmaAccountsList);
					}	
					else if(decision == 2)
					{
						System.out.println("How much money would you like to deposit?");
						double depositAmount = keyboard.nextDouble();
						mmaV.deposit(depositAmount);
						System.out.println("Current balance: $" + mmaV.getBalance());
						writeAccountListToCSV(mmaAccountsList);
					}
					else if(decision == 3)
					{
						//Create a new method, or make accountClose equal something
						while(accountCloseRepeat == 0)
						{
							System.out.println("Are you sure you want to close the account?[Yes/No]");
							String accountClose = keyboard.next();
							if(accountClose.toLowerCase().equals("yes"))
							{
								System.out.println("Withdrawing the remaining balance");
								mmaV.withdraw(mmaV.getBalance());
								mmaAccountsList.remove(mmaidx);
								modifyCSV(MAIN_ACCOUNT_FILE, "FALSE", mainAccountIdx, 14);
								writeAccountListToCSV(mmaAccountsList);
								return;
							}
							else if(accountClose.toLowerCase().equals("no"))
							{
								break;
							}
							else
							{
								System.out.println("Invalid Option");
								continue;
							}
						}
					}
					else 
					{
						return;
					}
					
				}
		}
		catch (Exception e) {
			System.out.println(e.getStackTrace());
		}

	}
	
	public static void main(String args[])
	{
	    String loggedAccount = MainMenu.loggedInAccountId;
		MMAManager m = new MMAManager();
		m.process(loggedAccount);
		return;
		
	}
	
}
