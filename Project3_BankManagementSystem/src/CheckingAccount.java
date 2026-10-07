public class CheckingAccount extends BankAccount{
    String accountType = "checkingaccount";
    CheckingAccount(String name, String accountNumber, double balance){
        super(name,accountNumber,balance);
    }
    public String getAccountType(){
        return accountType;
    }
}
