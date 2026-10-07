import java.util.ArrayList;
import java.util.Scanner;

public class BankManager {
    ArrayList<BankAccount> bankAccounts = new ArrayList<>();
    private final Scanner scanner;
    private int accountId = 1;
    BankManager(Scanner scanner){
        this.scanner = scanner;
    }
    public void createAccount(String name, String accountType){
        switch(accountType.toLowerCase()){
            case "1", "checkingaccount" -> {
                BankAccount newAccount = new CheckingAccount(name, generateId(), 0);
                bankAccounts.add(newAccount);
            }
            case "2", "savingsaccount" -> {
                BankAccount newAccount = new SavingsAccount(name, generateId(), 0);
                bankAccounts.add(newAccount);
            }
        }
    }
    public BankAccount searchAccount(){
        System.out.println("Enter account ID: ");
        String ID = scanner.nextLine();
        System.out.println("Searching...");
        BankAccount searchedAccount = null;
        for(BankAccount bankAccount : bankAccounts){
            if(bankAccount.getAccountNumber().equals(ID)){
                searchedAccount = bankAccount;
                break;
            }
        }
        return searchedAccount;
    }
    String generateId(){
        String id = String.format("%03d",accountId);
        accountId++;
        return id;
    }
    void transfer(BankAccount sender, BankAccount receiver, double amount){
        if(sender.withdraw(amount)){
            System.out.println("Successfully transferred " + amount + " to account " + receiver.getName());
            receiver.deposit(amount);
        }
    }
    public ArrayList<BankAccount> getAccountList(){
        return bankAccounts;
    }
    public void setNewList(ArrayList<BankAccount> newList){
        this.bankAccounts.clear();
        this.bankAccounts = newList;
    }
    public void setId(int newId){
        this.accountId = newId;
    }
    public int getId(){
        return accountId;
    }
}
