

public class cAccount {
	
	public String getAccNumber() {
		return AccNumber;
	}
	public void setAccNumber(String AccNumber) {
		this.AccNumber = AccNumber;
	}

	public double getBalance() {
		return cbalance;
	}
	public void setBalance(double cbalance) {
		this.cbalance = cbalance;
	}

	public double getDeposit() {
		return deposit;
	}
	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}
	public double getTransfer() {
		return transfer;
	}

	public void setTransfer(double transfer) {
		this.transfer = transfer;
	}

	public double getWithdrawal() {
		return withdrawal;
	}

	public void setWithdrawal(double withdrawal) {
		this.withdrawal = withdrawal;
	}
	public double getSavingsB() {
		return SavingsB;
	}

	public void setSavingsB(double SavingsB) {
		this.SavingsB = SavingsB;
	}
	public double changeBalance(double deposit, double withdrawal){
    	cbalance = cbalance + deposit;
    	cbalance = cbalance - withdrawal;
		return cbalance;
	}

	public double transfrom(double transfer){
		cbalance = cbalance - transfer;
		return cbalance;
	}

	
	public double transto(double transfer){
		cbalance = cbalance + transfer;
		return cbalance;
	}

	private double deposit;
	private double withdrawal;
	private double cbalance;
	private double SavingsB;
	private double transfer;
	private String AccNumber;

}
