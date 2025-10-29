import java.util.Scanner;
import java.text.DecimalFormat;

public class RothIRA {
    private static final DecimalFormat df = new DecimalFormat("#.##");
    private double initialInvestment;
    private double annualInterestRate;
    private int years;
    private double annualContribution;
    

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double initialInvestment = 0.0;
        
        System.out.println("Enter the initial investment amount: ");
         initialInvestment = scanner.nextDouble();
        
        while (initialInvestment < 0 )
        {
            System.out.println("Please enter a positive value for initial investment.");
            initialInvestment = scanner.nextDouble();
        }
        


        System.out.println("Enter the number of years: ");
        int years = scanner.nextInt();
        
        while (years < 0)
        {
            System.out.println("Please enter a positive value for number of years.");
            years = scanner.nextInt();
        }



        System.out.println("Enter the annual contribution amount: ");
        double annualContribution = scanner.nextDouble();
        
        while (annualContribution < 0)
        {
            System.out.println("Please enter a positive value for annual contribution amount.");
            annualContribution = scanner.nextDouble();
        }
        
        double annualInterestRate = 0.059;

        RothIRA rothIRA = new RothIRA();
        rothIRA.setInitialInvestment(initialInvestment);
        rothIRA.setYears(years);
        rothIRA.setAnnualContribution(annualContribution);
        rothIRA.setAnnualInterestRate(annualInterestRate);

        double futureValue = rothIRA.calculateFutureValue();

        System.out.println("The future value of your Roth IRA investment after " + years + " years is: $" + futureValue);
    }

    public double getInitialInvestment() {
        return initialInvestment;
    }

    public void setInitialInvestment(double initialInvestment) {
        this.initialInvestment = initialInvestment;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public double getAnnualContribution() {
        return annualContribution;
    }

    public void setAnnualContribution(double annualContribution) {
        this.annualContribution = annualContribution;
    }

    public double calculateFutureValue() {
        double futureValue = initialInvestment;

        for (int i = 0; i < years; i++) {
            futureValue *= (1 + annualInterestRate); // Compounding interest
            futureValue += annualContribution; // Adding annual contribution
        }

        return Double.parseDouble(df.format(futureValue));
    }
}
