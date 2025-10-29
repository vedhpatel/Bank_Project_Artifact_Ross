import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;;

public class MoneyTransfer {

    // Private variables for transfer fee and exchange rate

    private double transferFee = 10.00;
    private double exchangeRate = 0.80;

    // Transaction log file name
    private String logFileName = "transactionlog.csv";

    // Private BankAccount objects for sender and receiver

    private BankAccount senderAccount;
    private BankAccount receiverAccount;

    // Getter methods for transfer fee and exchange rate
    public double getExchangeRate() {
        return exchangeRate;
    }

    // Setter methods for transfer fees and exchange rates
    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public double getTransferFee() {
        return transferFee;
    }

    public void setTransferFee(double transferFee) {
        this.transferFee = transferFee;
    }

    // Method to set senders BankAccount
    public void setSendersBankAccount(BankAccount senderAccount) {
        this.senderAccount = senderAccount;
    }

    // Method to set receivers account
    public void setReceiverBankAccount(BankAccount receiverAccount) {
        this.receiverAccount = receiverAccount;
    }

    // Method to transfer money between sender and receiver accounts
    public void transferMoney(double amountToTransfer) {
        double senderAccountBalance = senderAccount.getAccountBalance();
        // sender account must have the amount to cover the transfer and our
        // fee
        double moneyNeeded = amountToTransfer + transferFee;
        if (senderAccountBalance < moneyNeeded) {
            System.out.println("Sorry sender's account does not have enough funds");
        } else { // doing the actual transfer here
            double transferAmount = senderAccount.withDrawMoney(amountToTransfer);
            // If the accounts are in different banks, apply exchange rate
            if (senderAccount.getRoutingNumber() != receiverAccount.getRoutingNumber()) {
                transferAmount = transferAmount * exchangeRate;
            }
            receiverAccount.depositMoney(transferAmount);
            senderAccount.withDrawMoney(transferFee); // charge sender the fee

            System.out.println(" Your transfer is completed. With a fee of " + transferFee);

            //Transaction is complete, add it to log. 
            logTransaction(senderAccount.getAccountNumber(), 
            receiverAccount.getAccountNumber(), amountToTransfer, transferFee);
            
            System.out.println("Total amount withdrawn is: $" + moneyNeeded);
        }

    }

    //Method to create Transaction Log 
    private void logTransaction(long fromAccount, long toAccount, double amount, double fee){
        File file=null;
        FileWriter fWriter=null;

        try{
            //open file for logging transaction
            file = new File(logFileName);
            fWriter = new FileWriter(file, true); // open file in append mode 

            //get current time/date 
            Date timeStamp = new Date();

            // create log message in comma separated values
            // format is:  "date , from account, to account , amount , fee"
            String logMessage = timeStamp + "," + fromAccount +"," + toAccount + "," + amount + "," + fee + "\n";
            
            //write the message
            fWriter.write(logMessage);

            //close the file
            fWriter.close();
        } catch (IOException e) { // Catch any exception and print error
            System.out.println(" ALERT: An error occurred when trying to log transaction" + e.getMessage());
        }
    }

   }
