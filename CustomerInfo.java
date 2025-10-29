import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class CustomerInfo
{
   //Main Method that calls to display CSV INFO
       public static void main(String[] args) throws IOException, ParseException
    {
     
        String basicInfo = "accounts.csv";
        String mmaInfo = "mmaAccounts.csv";
        String homeEquityInfo = "HomeEquityLoanClientInfo.csv";
        String foreignInfo = "foreignaccounts.csv";
        String savingsInfo = "savingsaccount.csv";
        String creditInfo = "ccard.csv";
        String mortInfo = "Mortgage.csv";
        String annInfo = "Annuity Investment/Annuity Investment Users.csv";
        String checkInfo = "TreasuryBonds/checkingsaccount.csv";
        String debitInfo = "dcard.csv";
        String payInfo = "Payroll.csv";

       
        
      
        CustomerInfo displayMeth = new CustomerInfo();
        
        System.out.println("Basic User Info");
         System.out.println(" ");
        displayMeth.displayAny(displayMeth.UserID, basicInfo);
         System.out.println("Money Market Account User Info");
         System.out.println(" ");
        displayMeth.displayAny(displayMeth.UserID, mmaInfo);
         System.out.println("Home Equity Account User Info");
         System.out.println(" ");
        displayMeth.displayAny(displayMeth.UserID, homeEquityInfo);
        System.out.println("Foreign Account User Info");
         System.out.println(" ");
        displayMeth.displayAny(displayMeth.UserID, foreignInfo);
         System.out.println("Savings Account User Info");
         System.out.println(" ");
        displayMeth.displayAny(displayMeth.UserID, savingsInfo);
          System.out.println("Credit Account User Info");
         System.out.println(" ");
        displayMeth.displayAny(displayMeth.UserID, creditInfo);

            System.out.println("Mortgage Account User Info");
         System.out.println(" ");
        displayMeth.displayAny(displayMeth.UserID, mortInfo);
          System.out.println("Annuity Investment Account User Info");
         System.out.println(" ");
        displayMeth.displayAny(displayMeth.UserID, annInfo);
           System.out.println("Checkings Account User Info");
         System.out.println(" ");
        displayMeth.displayAny(displayMeth.UserID, checkInfo);
        System.out.println("Debit Card User Info");
         System.out.println(" ");
        displayMeth.displayAny(displayMeth.UserID, debitInfo);
        System.out.println("Payroll User Info");
         System.out.println(" ");
        displayMeth.displayAny(displayMeth.UserID, payInfo);
        System.out.println(displayMeth.UserID);
        Scanner goToMenu = new Scanner(System.in);
        System.out.println("Input anything except 0 if you would like to leave this menu.");
        String backMenu = goToMenu.nextLine();
        if (!backMenu.equals('0')){
            
        MainMenu.showMainMenu(displayMeth.UserID);
        
        }
      
        
    }
 
 
    
	//Allows Employee team to easily see info of ANY id
		public void displayAny(String ID, String CSV)
	{

  
    List<String[]> content = new ArrayList<>();
    List<String[]> content2 = new ArrayList<>();
   
    
		try
		{
			List< List<String> > data = new ArrayList<>();//list of lists to store data
			String file = CSV;//file path
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			
			//Reading until we run out of lines
			String line = br.readLine();
		
		
		
			while (line != null)
			{
		
				List<String> lineData = Arrays.asList(line.split(","));//splitting lines
				data.add(lineData);
				line = br.readLine();
		
				
							
				
				if(line!= null && !line.isEmpty()&&line.contains(ID)) {
					boolean result = line.contains(ID);
					if (result == true) {
						
						
					     content.add(line.split(","));
					     int arrayLength = content.size();
					     for (int h = 0; h < arrayLength; h++) {
	                  System.out.println(Arrays.deepToString(content.get(h)));
	                    System.out.println(" "); 

			    		
					}
					     br.close();
				    		break;
			    }

		    		
				
                // Parses code to check what info pertains to what id
			
                    
				
					
					
                  
         
				}
			
			}
			

			
	
		} 
		catch(Exception e)
		{
			System.out.print(e);
		}
	}
	
	
	//Gets current Account ID
	BankAccountSignup.BankAccount MyObj = new BankAccountSignup.BankAccount();

	private String UserID = MainMenu.loggedInAccountId;
	
	
	public String GetID (){
	    return UserID;
	}

 public String SetID(String newID){
     return this.UserID = newID;
 }
	
	



}