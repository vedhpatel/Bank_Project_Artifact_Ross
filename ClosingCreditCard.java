import java.util.Scanner; 
@SuppressWarnings("all")


public class ClosingCreditCard
{
    CreditCard obj = new CreditCard(0,0);
    private double balance = obj.getBalance();
     public void close()
     {

        Scanner closing = new Scanner(System.in); 
         
        String responseClosing = closing.nextLine(); 
        
        
        if(balance == 0) {
            System.out.println("We will now delete the credit card information from the system that you are currently logged in to. ");
            System.out.println("Thank you for opening a credit card. Have a nice day!");
            


        }
        if (balance > 0) {
            System.out.println("Sorry, but you still owe some money on your credit card. Pay it off first and try again later.");
            
        }

        if (balance < 0) {
            System.out.println("Sorry, but you're in debt right now. Try filing for bankruptcy with one of our agents.");
            
        }
     
     
     }
     
}