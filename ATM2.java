package ATM;
import java.util.Scanner; 
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter; 

public class ATM2 { 

	private static double balance = 0; 
	private static int choice = -1; 
	private static double withdraw;  
	private static double depos= -1; 
	private static double with = -1.00;  
	static int number;
	static int num;
    static double intialbalance;
    static int userChoix;
	static int userChoice; 
	static String userPass;
	static String userID;
	static Double intials;
	static Double intial;
    
	//Sign-In Password and ID
	public static void SignIn(int userChoic, String pas, String ID, Double intialbalances, Double intialbalance ) throws IOException {  
		
		userChoix = userChoic;
		intials = intialbalances;
		intial = intialbalance;
		
		
		Scanner myObj = new Scanner(System.in);
		
		while (true) 
		 {  
			
		 System.out.println(""); 
		 System.out.println("Welcome to the Old Bridge Bank! "); 
	/*	 System.out.print("Please, enter the ONLY the numbers of your Account ID: "); 
		 userID = myObj.nextLine(); 
		 System.out.print("Please, enter your Account Password: "); 
		 userPass = myObj.nextLine(); 
 
		 if (userPass.equals(pas) && userID.equals(ID) ) 
		 { 
		 System.out.println("Welcome!"); 
		 zipCode();
		 break;
		 } 

		 else  
		 { 
		 System.out.println("Error! Try again"); 
		 }
		 	 */
		 zipCode();
        } 
	 }
	
	
	//Check for in/out of branch ATM
	public static void zipCode() throws IOException  

	{ 

	    Scanner myObj = new Scanner(System.in); 

	     

	    System.out.print("Please, enter your zip code: "); 

	    String zipcode = myObj.nextLine(); 

	     

	    if (zipcode.equals("08857")  || zipcode.equals("75001") || zipcode.equals("90008") || zipcode.equals("48208") || zipcode.equals("80014")) 

	    { 

	    	 Chose();
	    	return;  

	    } 

	    else 

	    { 
	    	System.out.print("Out of branch ATM, fee of $3 applies "); 
	        balance -= 3; 
	        Chose();
	        return;  

	    } 

	} 
	
		
		//Chose Deposit or Withdraw transaction
		public static void Chose() throws IOException {
		
		userChoice = -1;	
			
	    Scanner myObj = new Scanner(System.in);
		while (userChoice == -1) 
		{  
			
		 System.out.println(""); 

		 System.out.println("Press 1 if you would like to deposit "); 

		 System.out.println("Press 2 if you would like to withdraw "); 

		 System.out.println("Chose your type of transaction: "); 

		  userChoice = myObj.nextInt(); 
		 
		  if (userChoice ==1)
		  {	 
			 ATM2.Deposit(); 
	      } 

		  else  if (userChoice==2)
		  {
			 ATM2.Withdraw(); 
		  }
		 
		  else 
		  {
		 	 System.out.println("Error! Try again!");  
		 	 userChoice = -1;
		  }
	    }
	}
	
		
	//Deposit
	public static void Deposit() throws IOException { 

		Scanner myObj = new Scanner(System.in);  
		
		
		
		System.out.print("How much would you like to deposit?");   	 
		Boolean a = true;	
			 
			 while (a == true)
			 {	 
				 
			 depos = myObj.nextInt();
				 
			 if (depos >= 0)
			 {
			 balance += depos; 
			 System.out.print("Congrtulations, you balance is $" + balance);  
			 ExitOr();
			 a = false;
			 break;
			 }
			 
			 else
			 {
				 System.out.print("Please enter an appropriate ammount");  
				 depos = -1;
			 } 
		  } 
		}  

	 
	//Withdrawl
	public static void Withdraw() throws IOException {  

		Scanner myO = new Scanner(System.in);  
		System.out.println("What type of currency you wish to withdraw in?");  
		System.out.println("Chose 1 for USD, Chose 2 for Foreign Currency");
		
		
		num = myO.nextInt(); 
		
		if ( num ==2) {
        
		System.out.println("Chose 1 for SAR , 2 for EUR, 3 for GBP, 4 for CAD, 5 for INDR"); 
		
            number = myO.nextInt(); 
    
			
			 Converttt convert = new Converttt(); 
			 
			 while (true)
			{

			 withdraw = myO.nextDouble();  
			 
			 if (userChoix==1 && withdraw >= 0 && withdraw <= intials )
				{
				 System.out.print("Your current balance is :" + intials);
				  System.out.print("How much would you like to withdraw?");  
					double result = convert.converttoUSD(withdraw, number); 
				 balance -= result; ; 
				 System.out.print("Congrtulations, you balance is $" + balance );  
			     ExitOr();
			     
				}
			 
			 else if (userChoix==2 && withdraw >= 0 && withdraw <= intial )
				{
				 System.out.print("Your current balance is :" + intial);
					 System.out.print("How much would you like to withdraw?");  
				 double result = convert.converttoUSD(withdraw, number); 
				 balance -= result; ; 
				 System.out.print("Congrtulations, you balance is $" + balance );  
			     ExitOr();
			     
				}
			 
			 
				 
				else
				 {
					 System.out.print("Please enter an appropriate ammount");  
					
				 }
			}
		}
		
		else if ( num == 1)
		{
			System.out.print("How much would you like to withdraw?");  
			
			 while (true)
				{

				 withdraw = myO.nextDouble();  
				 
				 if (userChoix==1 && withdraw >= 0 && withdraw <= intials )
					{
				
					 balance -= withdraw;
					 System.out.print("Congrtulations, you balance is $" + balance );  
				     ExitOr();
				     
					}
				 
				 else if (userChoix==2 && withdraw >= 0 && withdraw <= intial )
				 {
					 balance -= withdraw;
					 System.out.print("Congrtulations, you balance is $" + balance );  
				     ExitOr();
					 
				 }
					 
					else
					 {
						 System.out.print("Please enter an appropriate ammount");  
						
					 }
		   }
		}		 		 
    } 


	//Exit or Return
	public static void ExitOr() throws IOException {  
		Scanner op = new Scanner(System.in);  
	    
	    while (true) 
	    {
	    	System.out.println(" Would you like to exit or return to homepage? (exit/return)");  
		    String ER = op.nextLine();
	    	
		    
		    if (ER.equals("return") || ER.equals("Return"))
		    {
		    	if (userChoix ==1) 
		    	{ 
		    		intials -= withdraw;
		    	}
		    	
		    	else if (userChoix==2) 
		    	{
		    		intial-=withdraw;
		    	}
		    	
		    	else
		    	{
		    		System.out.print("  ");
		    	}
		    	
		    	Chose();  	
		    }	
	    
		    else if (ER.equals("exit") || ER.equals("Exit"))
		    {
		    	System.out.println("Thank you for using Old Bridge bank!");
	    	
		    	if (userChoix == 2) {
		    		balance = Math.round(balance*100)/100;
		    		Client.CatchC(balance, number);
		    		break;
		    	}
	    	
		    	else if (userChoix ==1) {
		    		balance = Math.round(balance*100)/100;
		    		Client.CatchS(balance, number);
		    		break;
	    	   }
		    	break;
	       }	
		
		    else 
		    {
		    	System.out.println("Error! Try again!");
		    	ExitOr();
		    }
	   }
	  }
     }   