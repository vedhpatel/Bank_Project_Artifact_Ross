public class BankAccount{


    private long accountNumber;
    private long routingNumber;
    private double accountBalance;
    
    public BankAccount(long accountNumber, long routingNumber, double accountBalance){
        this.accountBalance = accountBalance;
        this.routingNumber = routingNumber;
        this.accountNumber = accountNumber;
    
    }
    
    public long getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }
    public long getRoutingNumber() {
        return routingNumber;
    }
    public void setRoutingNumber(long routingNumber) {
        this.routingNumber = routingNumber;
    }
    public double getAccountBalance() {
        return accountBalance;
    }
    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }
    
    public void depositMoney(double depositAmount){
        accountBalance = accountBalance  + depositAmount;
    }
    
    public double withDrawMoney(double withdrawlAmount){
        if (accountBalance >= withdrawlAmount){
            accountBalance = accountBalance - withdrawlAmount;
            return withdrawlAmount;
        }
        else {
            System.out.println("Sorry your account balance is too low for this withdrawl");
            System.out.println("Account Balance:" + accountBalance );
            return -1;
    
        }
        
        
    }
    
    }