package Pay;
import java.util.Scanner; 
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Payroll {
	
	private static double total = 0;
	private static double deduction = 0;
	private static double wage = 0;
	private static int S = 0;
	private static double roundOff = 0;
	static  Double hours ;
	static  String Hours ;
	private static double d1 = 0;
	private static double d2 = 0;
	private static double d3 = 0;
	private static double d4 = 0;
	private static double d5 = 0;
	
	
	public static void InitialInput()
	{
		
		      Scanner reader = new Scanner(System.in); 
		      System.out.print("What is your current wage?");
		      
		      while (true)
				{

		    	  wage = reader.nextDouble();
				 
				 if (wage <= 100 )
					{
				
					 	State();
				     
					}
					 
					else
					 {
						 System.out.print("Please enter appropriate wage ");  
						
					 }
				}
		   }
		      
		      
		      public static void State()
		  	{
		      
		    	  Scanner reader = new Scanner(System.in); 
		    	  
		      System.out.print("Please enter your current state ");	  
		      System.out.print("New Jersey, California, Texas, Michigan, or Colorado ");
		     
		      while (true) {
		      
		      String state = reader.nextLine();
		      
		      if (state.equalsIgnoreCase("New Jersey") || state.equalsIgnoreCase("NJ"))
				{
		    	  S = 1;
		    	  Hours();
				}
		      
		      else if (state.equalsIgnoreCase("Texas")  || state.equalsIgnoreCase("TX"))
				{
		    	  S = 2;
		    	  Hours();
				}
		      
		      else if (state.equalsIgnoreCase("California")  || state.equalsIgnoreCase("CA") || state.equalsIgnoreCase("Cali"))
				{
		    	  S = 3;
		    	  Hours();
				}
		      
		      else if (state.equalsIgnoreCase("Michigan")  || state.equalsIgnoreCase("MI"))
				{
		    	  S = 4;
		    	  Hours();
				}
		      
		      else if (state.equalsIgnoreCase("Colorado")  || state.equalsIgnoreCase("CO"))
				{
		    	  S = 5;
		    	  Hours();
				}
		      
		      else
				{
					System.out.print("");
				}
		      }	  
		  	
	}
	
	
	public static void Hours() 
	{
		  Scanner reader = new Scanner(System.in); 
	      System.out.println("How many total hours have your worked in two weeks?");
	      
	      while (true)
			{

	    	  hours = reader.nextDouble();
			 
			 if (hours <= 80 )
				{
			
				 total = hours * wage;
			      StateTax(S);
			     
				}
				 
				else
				 {
					 System.out.print("Please enter appropriate hours ");  
					
				 }
	        }
	}
	
	
	public static void StateTax(int S) {
		if (S == 1)
		{
			d1 = (total * 0.06625);
		}
		else if (S == 3)
		{
			d1 = (total * 0.0725);
		}
		else if (S == 4)
		{
			d1 = (total * 0.0425);
		}
		else if (S == 5)
		{
			d1 = (total * 0.044);
		}
		
		FUTA(total);		
	}
	
	
 /*	public static void FederalTax() {  	
		if (salary>=0.00 && salary<=10275.00)
		{
			deduction = salary*0.10;
			salary -= deduction;
		}
		
		else if (salary>=10275.00 && salary<=41775.00 )
		{
			deduction = salary*0.12;
			salary -= deduction;
		}
		
		else if (salary>=41775.00 && salary<=89075.00 )
		{
			deduction = salary*0.22;
			salary -= deduction;
		}
		
		else if (salary>=89075.00 && salary<=170050.00 )
		{
			deduction = salary*0.24;
			salary -= deduction;
		}
		
		else if (salary>=170050.00 && salary<=215950.00 )
		{
			deduction = salary*0.32;
			salary -= deduction;
		}
		
		else if (salary>=215950.00 && salary<=539900.00 )
		{
			deduction = salary*0.34;
			salary -= deduction;
		}
		
		else if (salary>=539900.00)
		{
			deduction = salary*0.37;
			salary -= deduction;
		}
		
		else
		{
			System.out.print("Error");
		}
		
		FUTA(salary);
		
	}
	*/
	
	public static void FUTA(Double total) { 

	    d2 = total*0.06; 
	    SUI(total);

	} 

	  

	public static void SUI(Double total) { 

	    d3 = total*0.06; 
	    Medicare(total);

	} 

	  

	public static void Medicare(Double total) { 

	    d4 = total*0.029; 
	    socialSecurity(total);
	} 

	  

	public static void socialSecurity(Double total) { 

	    d5 = total*0.062; 
	    total -= (d1+d2+d3+d4+d5);
	    roundOff = Math.round(total*100.0)/100.0;
	    
	    Hours = Double.toString (hours);
	    Payroll2.Catch(roundOff, Hours);
	    
	  
	    
	} 
	
	
	}
 
 
