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
            System.out.println("==== EXPENSE TRACKING APP [ CLI VERSION ] ====");
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
                    for(Transaction transaction : searchResult){
                        System.out.println(transaction + "\n");
                    }
                }
                case "3" -> {
                    // pre-declare
                    String newDescription;
                    BigDecimal newAmount;
                    Type newType;
                    Category newCategory;
                        // prompting
                        System.out.println("==================");

                        System.out.print("Enter description: ");
                        newDescription = scanner.nextLine();

                        System.out.print("Enter amount: ");
                        newAmount = scanner.nextBigDecimal();
                        scanner.nextLine();

                        System.out.print("Enter type (income/expense): ");
                        String type = scanner.nextLine().trim();
                        while (true){
                            try {
                                if (type.equalsIgnoreCase("income")) {
                                    newType = Type.INCOME;
                                    System.out.println("What type of " + type);
                                    System.out.println("Salary, Freelance");
                                } else if (type.equalsIgnoreCase("expense")) {
                                    newType = Type.EXPENSE;
                                    System.out.println("What type of " + type);
                                    System.out.println("Entertainment, Food, Transport");
                                } else {
                                    System.out.println("Invalid transaction type, try again");
                                    continue;
                                }
                                String category = scanner.nextLine().trim().toUpperCase();

                                if (!newType.hasCategory(Category.valueOf(category))) {
                                    System.out.println(category + " does not belong to " + newType.name() + " ,try again.");
                                    continue;
                                } else {
                                    newCategory = Category.valueOf(category);
                                }

                                manager.createTransaction(newDescription, newType, newCategory, newAmount);
                                break;
                            }
                            catch(IllegalArgumentException e) {
                                System.out.println("Malformed string/number found, try again.");
                            }
                        }
                }
                case "4" -> {
                    System.out.println("==================");
                    ArrayList<Transaction> temp = manager.tempListIndexing();
                    try {
                        System.out.print("Pick a number correspond to the transaction: ");
                        int index = Integer.parseInt(scanner.nextLine());
                        if (index < 0 || index >= temp.size()) {
                            System.out.println("Index out of bounds. Try again");
                            continue;
                        }
                        manager.deleteTransaction(temp.get(index).getUUID());
                    }catch(NumberFormatException e){
                        System.out.println("Invalid number format!");
                    }

                }
                case "5" -> {
                    ArrayList<Transaction> temp = manager.tempListIndexing();
                    if(temp.isEmpty()){
                        System.out.println("There are no transactions.");
                        continue;
                    }
                    System.out.println("==================");
                    System.out.print("Pick a number correspond to the transaction: ");
                    int index =  Integer.parseInt(scanner.nextLine());
                    if(index < 0 || index >= temp.size()){
                        System.out.println("Index out of bounds. Try again");
                        continue;
                    }
                    Transaction tempPlaceholder = temp.get(index);
                    System.out.println("Edit transaction, leave empty if unchanged.");

                    System.out.print("Description: ");
                    String description = scanner.nextLine();
                    if(description.isBlank()){
                        description = tempPlaceholder.getDescription();
                    }

                    System.out.print("Type income/expense: ");
                    String type = scanner.nextLine().toUpperCase();
                    if(type.isBlank()){
                        type = tempPlaceholder.getTransactionType().name();
                    }
                    String category;
                        while (true) {
                            try {
                                System.out.println("Type of " + type + "?");
                                System.out.println("Salary, Freelance OR Entertainment, Food, Transport");
                                category = scanner.nextLine().trim().toUpperCase();
                                if (!Type.valueOf(type).hasCategory(Category.valueOf(category))) {
                                    System.out.println(category + " does not belong to " + type + " ,try again.");
                                    continue;
                                }
                                break;
                            } catch (IllegalArgumentException e) {
                                System.out.println("Malformed string/number found, try again.");
                            }
                        }

                    if(category.isBlank()){
                        category = tempPlaceholder.getCategory().name();
                    }

                    System.out.print("Amount: ");
                    String amount = scanner.nextLine();
                    if(amount.isBlank()){
                        amount = tempPlaceholder.getAmount().toString();
                    }

                    String[] edits = {description, type, category, amount};
                    manager.editTransaction(tempPlaceholder.getUUID(), edits);

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
