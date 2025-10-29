import java.util.HashMap;
import java.util.Map;

class BankLocation {

    private Map<String, String> createLocationDetails(String zipCode, String routingNumber, String interestTax) {
        Map<String, String> details = new HashMap<>();
        details.put("ZIP Code", zipCode);
        details.put("Routing Number", routingNumber);
        details.put("Interest Tax", interestTax + "%"); // Add interest tax as a percentage
        return details;
    }

    public String getDetailsAsString(Map<String, String> details) {
        return details.toString();
    }

    public String oldBridge() {
        Map<String, String> oldBridgeList = createLocationDetails("08857", "832954724", "2.5");
        return getDetailsAsString(oldBridgeList);
    }

    public String dallas() {
        Map<String, String> dallasList = createLocationDetails("75001", "732297259", "3.0");
        return getDetailsAsString(dallasList);
    }

    public String losAngeles() {
        Map<String, String> losAngelesList = createLocationDetails("90008", "563934953", "3.5");
        return getDetailsAsString(losAngelesList);
    }

    public String detroit() {
        Map<String, String> detroitList = createLocationDetails("48208", "384239475", "4.0");
        return getDetailsAsString(detroitList);
    }

    public String denver() {
        Map<String, String> denverList = createLocationDetails("80014", "274539242", "2.0");
        return getDetailsAsString(denverList);
    }
}

class BankInformation {

    private String typeBank = "Franchise Bank";
    private double taxesPercent = 6.0; // Represented as a percentage
    private double interest = 0.57; // Represented as a percentage
    private String bankPhoneNum = "732-309-5643";
    private String bankEmail = "contact@franchisebank.com";

    public String getTypeBank() {
        return typeBank;
    }

    public void setTypeBank(String typeBank) {
        this.typeBank = typeBank;
    }

    public double getTaxesPercent() {
        return taxesPercent;
    }

    public void setTaxesPercent(double taxesPercent) {
        this.taxesPercent = taxesPercent;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public String getBankPhoneNum() {
        return bankPhoneNum;
    }

    public void setBankPhoneNum(String bankPhoneNum) {
        this.bankPhoneNum = bankPhoneNum;
    }

    public String getBankEmail() {
        return bankEmail;
    }

    public void setBankEmail(String bankEmail) {
        this.bankEmail = bankEmail;
    }
}

public class Main {
    public static void main(String[] args) {
        BankLocation bankLocation = new BankLocation();
        BankInformation bankInfo = new BankInformation();

        System.out.println("Old Bridge: " + bankLocation.oldBridge());
        System.out.println("Dallas: " + bankLocation.dallas());
        System.out.println("Los Angeles: " + bankLocation.losAngeles());
        System.out.println("Detroit: " + bankLocation.detroit());
        System.out.println("Denver: " + bankLocation.denver());

        System.out.println("Bank Type: " + bankInfo.getTypeBank());
        System.out.println("Taxes Percent: " + bankInfo.getTaxesPercent() + "%");
        System.out.println("Interest Rate: " + bankInfo.getInterest() + "%");
        System.out.println("Bank Phone Number: " + bankInfo.getBankPhoneNum());
        System.out.println("Bank Email: " + bankInfo.getBankEmail());
    }
}
