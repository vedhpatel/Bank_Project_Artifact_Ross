package bankProject;
import java.util.*;
import java.util.Scanner;


public class MoneyMarketAccount 
{
	Calendar c = Calendar.getInstance();
	private String accountId;
	private double deposit = 0;
	private double balance = 0;
	private double rate = 0.0475;
	private double interestAmount = 0;
	private double interest = 0;
	private double withdraw = 0;
	private int maxWithdrawalAmt = 6;
	private int countWithdraw = 0;

	private int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);

	
	//functions
	public void calculateDailyInterest(double b)
	{
		interest = b * (rate/365);
		interestAmount += interest;
	}
	//Adds the interest money into account after the month is done
	public void payOut(double b)
	{
		b += interestAmount;
		interestAmount = 0;
	}
	//Deposits money into account. Adds deposited money into balance
	public double deposit(double d)
	{
		deposit += d;
		balance += d;
		return balance;
	}
	//Withdraws money from the account. Subtracts withdrawn money from balance
	public String withdraw(double w)
	{
		if(w > balance)
		{
			return "You cannot withdraw more than your balance";
		}
		else
		{
			if(countWithdraw > maxWithdrawalAmt)
			{
				countWithdraw++;
				balance -= w - 10;
				return "A fee of $10 will be added as you have withdrawn money " + countWithdraw + "times this month";
			}
			else
			{
				countWithdraw++;
				//System.out.println("You have withdrawn money " + countWithdraw + "times.");
				//System.out.println("You can withdraw money " + (6 - countWithdraw) + "times this month before a fee of $10 is added to every withdrawal");
				balance -= w;
				return "Withdrawal successful";
			}		
		}
	}
	//$15 fee if there isnt $2500 in account. Fee waived if there is a minimum of $2500 in account
	public void monthlyFee(double b)
	{
		if (b < 2500)
		{
			b -= 15;
		}
	}
	
	public String toCSV() 
	{
        return String.format("%s,%.2f,%.2f,%.2f,%d", accountId, balance, interestAmount, deposit, countWithdraw);
    }
	
	
	public MoneyMarketAccount()
	{
		
	}
	public MoneyMarketAccount(String a, double b, double i, double d, int c)
	{
		accountId = a;
		balance = b;
		interestAmount = i;
		deposit = d;
		countWithdraw = c;
	}
	
	//Getters and Setters
	public double getBalance()
	{
		return balance;
	}
	public double getDeposit()
	{
	    return deposit;
	}

	public int getDayOfMonth()
	{
		return dayOfMonth;
	}
	public String getAccountId()
	{
		return accountId;
	}
	public int getCountWithdraw()
	{
		return countWithdraw;
	}
	
	public void setAccountId(String acId)
	{
		accountId = acId;
	}
	public void setBalance(double b)
	{
		balance = b;
	}
	public void setInterestAmount(double ia)
	{
		interestAmount = ia;
	}
	public void setDeposit(double d)
	{
		deposit = d;
	}
	public void setCountWithdraw(int c)
	{
		countWithdraw = c;
	}

}