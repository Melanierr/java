import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);
         BankManager bankManager = new BankManager(scanner);
         AccountsSaver accountsSaver = new AccountsSaver();
         boolean isExit = false;
         do {
             System.out.println("==== JAVA BANK ====");
             System.out.println("""
                     1.Create Account
                     2.Withdraw
                     3.Deposit
                     4.Transfer money
                     5.View account
                     6.Delete account
                     7.Exit
                     8.Load account database""");
             String selection = scanner.nextLine();
             switch(selection){
                 case "1" -> {
                     System.out.print("Enter your name: ");
                     String name = scanner.nextLine();
                     System.out.print("Select an account type: ( Checking / Savings ) ");
                     String type = scanner.nextLine();
                     bankManager.createAccount(name, type);
                 }
                 case "2" -> {
                     BankAccount inUse = bankManager.searchAccount();
                     if(inUse == null){
                         return;
                     }
                     System.out.println("How much $ do you want to withdraw?");
                     double amount = scanner.nextDouble(); scanner.nextLine();
                     inUse.withdraw(amount);
                 }
                 case "3" -> {
                     BankAccount inUse = bankManager.searchAccount();
                     if(inUse == null){
                         return;
                     }
                     System.out.println("How much $ do you want to deposit?");
                     double amount = scanner.nextDouble(); scanner.nextLine();
                     inUse.deposit(amount);
                 }
                 case "4" -> {
                     BankAccount senderAcc = bankManager.searchAccount();
                     System.out.print("Enter receiver's account ID");
                     BankAccount receiverAcc = bankManager.searchAccount();
                     System.out.println("How much $ to transfer ");
                     double amount = scanner.nextDouble(); scanner.nextLine();
                     bankManager.transfer(senderAcc, receiverAcc, amount);
                 }
                 case "5" -> System.out.println(bankManager.searchAccount());
                 case "6" ->{
                     BankAccount deleteAccount =  bankManager.searchAccount();
                     System.out.println(deleteAccount);
                     System.out.println("Are you sure you want to delete (Y/N)");
                     String choice = scanner.nextLine().toLowerCase();
                     switch(choice){
                         case "y", "yes" -> bankManager.bankAccounts.remove(deleteAccount);
                         case "n", "no" -> System.out.println("Deletion aborted");
                     }
                 }
                 case "7" -> {
                     accountsSaver.saveList(bankManager.getAccountList(), bankManager.getId());
                     isExit = true;
                 }
                 case "8" -> {
                     bankManager.setNewList(accountsSaver.loadList());
                     bankManager.setId(accountsSaver.currentId);
                 }
                 default -> System.out.println("Invalid selection");
             }
         }while (!isExit);
    }
}
