public class info 
{
    public String getAccNumber()
    {
        return accnum;
    }
    public void setAccNumber(String accnum)
    {
        this.accnum = accnum;
    }
    
    public double getDownPayment()
    {
        return downpayment;
    }
    
    public void setDownPayment(double downpayment)
    {
        this.downpayment = downpayment;
    }
    
    public double getLoanAmount()
    {
        return loanamount;
    }
    
    public void setLoanAmount(double loanamount)
    {
        this.loanamount = loanamount;
    }
    
    public int getLoanLength()
    {
        return loanlength;
    }
    
    public void setLoanLength(int loanlength)
    {
        this.loanlength = loanlength;
    }
    
    public double getInterest()
    {
        return interest;
    }
    
    public void setInterest(double interest)
    {
        this.interest = interest;    
    }
    
    public double getMonthlyPayment()
    {
        return monthlypayment;
    }
    
    public void setMonthlyPayment(double monthlypayment)
    {
        this.monthlypayment = monthlypayment;
    }
    
    private String accnum;
    private double loanamount;
    private int loanlength;
    private double interest;
    private double monthlypayment;
    private double downpayment;
}
