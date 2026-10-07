public abstract class BankAccount {
    private String ownerName;
    private double balance;
    private String accountNumber;
    private String accountType = "";
    BankAccount(String ownerName, String accountNumber, double balance) {
        this.ownerName = ownerName;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
    public void deposit(double amount){
        System.out.println("Deposited " + amount + " to account " + getName());
        this.balance += amount;
    }
    public boolean withdraw(double amount){
        if(amount > balance){
            System.out.println("Insufficient funds");
            return false;
        }
        else {
            System.out.println("Successfully withdrawn $" + amount);
            this.balance -= amount;
            return true;
        }
    }
    public double getBalance(){
        return this.balance;
    }
    public String getName(){
        return this.ownerName;
    }
    public String getAccountNumber(){
        return this.accountNumber;
    }
    @Override
    public String toString() {
        return String.format("Name: %s\nAccount number: %s\nBalance: %f\nType: %s", getName(), accountNumber, getBalance(), getAccountType());
    }
    public abstract String getAccountType();
}
