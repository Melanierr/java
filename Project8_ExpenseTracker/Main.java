import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        TransactionManager manager = new  TransactionManager();
        DataSaver saver = new DataSaver();
        boolean isExit = false;
        while(!isExit){
            System.out.println("==== EXPENSE TRACKING APP ====");
            System.out.println("""
                    1.View summary
                    2.Search transaction
                    3.Add new transaction
                    4.Delete transaction
                    5.Edit transaction
                    6.Save
                    7.Load
                    8.Exit
                    """);
            String choice = scanner.nextLine();
            switch (choice){
                case "1" -> manager.viewSummary();
                case "2" -> {
                    System.out.println("==================");
                    System.out.print("Enter keyword or full description: ");
                    String description = scanner.nextLine();
                    ArrayList<Transaction> searchResult = manager.searchTransactionName(description);
                    if(searchResult.isEmpty()){
                        System.out.println("No such transaction found. Try again");
                        continue;
                    }
                    System.out.println(searchResult);
                }
                case "3" -> {
                    // pre-declare
                    String description;
                    BigDecimal amount;
                    Type newType;

                    // prompting
                    System.out.println("==================");
                    System.out.print("Enter description: ");
                    description = scanner.nextLine();
                    System.out.print("Enter amount: ");
                    amount = scanner.nextBigDecimal();
                    scanner.nextLine();
                    System.out.print("Enter type (income/expense): ");
                    String type = scanner.nextLine().trim();

                    // check type
                    if(type.equalsIgnoreCase("income")){
                        newType = Type.INCOME;
                    }
                    else if(type.equalsIgnoreCase("expense")){
                        newType = Type.EXPENSE;
                    }
                    else{
                        System.out.println("Invalid transaction type. Try again");
                        continue;
                    }
                    manager.createTransaction(description, newType, amount);
                }
                case "4" -> {
                    System.out.println("==================");
                    ArrayList<Transaction> temp = manager.tempListIndexing();
                    System.out.print("Pick a number correspond to the transaction: ");
                    int index =  Integer.parseInt(scanner.nextLine());
                    if(index < 0 || index >= temp.size()){
                        System.out.println("Index out of bounds. Try again");
                        continue;
                    }
                    manager.deleteTransaction(temp.get(index).getUUID());

                }
                case "5" -> {
                    boolean isConflict = false;
                    ArrayList<Transaction> temp = manager.tempListIndexing();
                    System.out.println("==================");
                    System.out.print("Pick a number correspond to the transaction: ");
                    int index =  Integer.parseInt(scanner.nextLine());
                    if(index < 0 || index >= temp.size()){
                        System.out.println("Index out of bounds. Try again");
                        continue;
                    }
                    Transaction tempPlaceholder = temp.get(index);
                    System.out.print("Edit transaction, leave empty if unchanged.");

                    System.out.print("Description: ");
                    String description = scanner.nextLine();

                    System.out.print("Type income/expense: ");
                    String type = scanner.nextLine().toUpperCase();

                    System.out.print("Amount: ");
                    String amount = scanner.nextLine();

                    if(description.isBlank()){
                        description = tempPlaceholder.getDescription();
                    }
                    if(type.isBlank()){
                        type = tempPlaceholder.getTransactionType().name();
                    }
                    if(amount.isBlank()){
                        amount = tempPlaceholder.getAmount().toString();
                    }

                    manager.editTransaction(tempPlaceholder.getUUID(), description, type, amount);

                }
                case "6" -> saver.saveFile(new ArrayList<>(manager.getTransactions().values()));
                case "7" -> manager.setTransactions(saver.loadFile());
                case "8" -> isExit = true;
                default -> System.out.println("Invalid choice.");
            }
        }
        scanner.close();
    }
}
