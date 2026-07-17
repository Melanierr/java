public abstract class BankAccount {
    private String ownerName;
    private double balance;
    private String accountNumber;
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
    public void transfer(BankAccount destination, double amount){
        if(withdraw(amount)){
            System.out.println("Successfully transferred " + amount + " to account " + getName());
            destination.deposit(amount);
        }
    }
    public double getBalance(){
        return this.balance;
    }
    public void setName(String name){
        this.ownerName = name;
    }
    public String getName(){
        return this.ownerName;
    }
    @Override
    public String toString() {
        return String.format("Name: %s\nAccount number: %s\nBalance: %f", ownerName, accountNumber, balance);
    }
}
