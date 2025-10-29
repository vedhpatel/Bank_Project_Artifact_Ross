

public class sAccount {
	
	public String getAccNumber() {
		return AccNumber;
	}
	public void setAccNumber(String AccNumber) {
		this.AccNumber = AccNumber;
	}

	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getDeposit() {
		return deposit;
	}
	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}

	public double getWithdrawal() {
		return withdrawal;
	}

	public void setWithdrawal(double withdrawal) {
		this.withdrawal = withdrawal;
	}
	public double getTransfer() {
		return transfer;
	}

	public void setTransfer(double transfer) {
		this.transfer = transfer;
	}
	
	public double getCheckingsB() {
		return CheckingsB;
	}

	public void setCheckingsB(double CheckingsB) {
		this.CheckingsB = CheckingsB;
	}
	public double changeBalance(double deposit, double withdrawal){
    	balance = balance + deposit;
    	balance = balance - withdrawal;
		return balance;
	}
	public double transfrom(double transfer){
		balance = balance - transfer;
		return balance;
	}

	public double transto(double transfer){
	    balance = balance + transfer;
		return balance;
	}


	private double deposit;
	private double withdrawal;
	private double balance;
	private double transfer;
	private double CheckingsB;
	private String AccNumber;

}
