public class SavingsAccount extends BankAccount{
    private double monthlyInterestRate = 3; // 3% per month
    private int monthPeriod = 1;
    private String accountType = "savingsaccount";
    SavingsAccount(String name, String accountNumber, double balance){
        super(name,accountNumber,balance);
    }
    // profit will be monthly, not yearly yet
    double calculateMoneyInterest(){
        double totalMoney = (this.getBalance() * (monthlyInterestRate / 100) * monthPeriod);
        return totalMoney;
    }
    public String getAccountType(){
        return accountType;
    }
}
