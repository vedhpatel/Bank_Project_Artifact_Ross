public class HomeEquityLoan {

	private double totalLoans;
	private double loanAmount;
	private double homeValue;
	private String nextPaymentDate;
	private String lastPaymentDate;
	private String dateOfCreation;
	private String finalPaymentDate;
	private double loanPeriod;
	private boolean paymentOverdue;
	private double timeLeftPayoff;
	private double interestRate;
	private double currentBalance;
	private String paymentMethod;
	private boolean hasLoan;
	private double paymentAmount;

//default constructor --------------------------------------------------
	public HomeEquityLoan() {
		totalLoans = 0.0;
		loanAmount = 0.0;
		homeValue = 0.0;
		nextPaymentDate = "";
		lastPaymentDate = "";
		dateOfCreation = "";
		finalPaymentDate = "";
		loanPeriod = 0.0;
		paymentOverdue = false;
		timeLeftPayoff = 0.0;
		interestRate = 0.0;
		currentBalance = 0.0;
		paymentMethod = "";
		hasLoan = false;
		paymentAmount = 0.0;

	}

// overloaded constuctor --------------------------------------------------
	public HomeEquityLoan(double tl, double la, double hv, String npd, String lpd, String doc, String fpd, boolean op, double lp, double tfpo, double ir,
			double cb, String pm, boolean hl, double pa) {
		totalLoans = tl;
		loanAmount = la;
		homeValue = hv;
		nextPaymentDate = npd;
		lastPaymentDate = lpd;
		dateOfCreation = doc;
		finalPaymentDate = fpd;
		loanPeriod = lp;
		paymentOverdue = op;
		timeLeftPayoff = tfpo;
		interestRate = ir;
		currentBalance = cb;
		paymentMethod = pm;
		hasLoan = hl;
		paymentAmount = pa;
	}

// getters --------------------------------------------------
	public double getTotalLoans() {
		return totalLoans;
	}

	public double getLoanAmount() {
		return loanAmount;
	}

	public double getHomeValue() {
		return homeValue;
	}

	public String getNextPaymentDate() {
		return nextPaymentDate;
	}

	public String getLastPaymentDate() {
		return lastPaymentDate;
	}
	public String getDateOfCreation() {
		return dateOfCreation;
	}
	public String getFinalPaymentDate() {
		return finalPaymentDate;
	}

	public double getLoanPeriod() {
		return loanPeriod;
	}
	public boolean getPaymentOverdue() {
		return paymentOverdue;
	}

	public double getTimeLeftPayOff() {
		return timeLeftPayoff;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public double getCurrentBalance() {
		return currentBalance;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public boolean getHasLoan() {
		return hasLoan;
	}

	public double getPaymentAmount() {
		return paymentAmount;
	}

// setters --------------------------------------------------
	public void setTotalLoans(double tl) {
		totalLoans = tl;
	}

	public void setLoanAmount(double la) {
		loanAmount = la;
	}

	public void setHomeValue(double hv) {
		homeValue = hv;
	}

	public void setNextPaymentDate(String npd) {
		nextPaymentDate = npd;
	}

	public void setLastPaymentDate(String lpd) {
		lastPaymentDate = lpd;
	}
	public void setDateOfCreation(String doc) {
		dateOfCreation = doc;
	}
	public void setFinalPaymentDate(String fpd) {
		finalPaymentDate = fpd;
	}
	public void setLoanPeriod(double lp) {
		loanPeriod = lp;
	}
	public void setPaymentOverdue(boolean op) {
		paymentOverdue = op;
	}

	public void setTimeLeftPayoff(double tfpo) {
		timeLeftPayoff = tfpo;
	}

	public void setInterestRate(double ir) {
		interestRate = ir;
	}

	public void setCurrentBalancef(double cb) {
		timeLeftPayoff = cb;
	}

	public void setPaymentMethod(String pm) {
		paymentMethod = pm;
	}

	public void setHasLoan(boolean hl) {
		hasLoan = hl;
	}

	public void setPaymentAmount(double pa) {
		paymentAmount = pa;
	}

}
