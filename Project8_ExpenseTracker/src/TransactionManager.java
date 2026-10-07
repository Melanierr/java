import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class TransactionManager {
    private HashMap<UUID, Transaction> transactions;
    private BigDecimal currentBalance;

    public TransactionManager() {
        System.out.println("Manager is loading...");
        transactions = new HashMap<>();
        currentBalance = BigDecimal.ZERO;
    }

    // transaction handling
    public void createTransaction(String description, Type type, Category category, BigDecimal amount){
        Transaction newTransaction = new Transaction(description, type, category, amount);
        transactions.put(newTransaction.getUUID(), newTransaction);
        System.out.println("Successfully created transaction");
    }

    public void deleteTransaction(UUID id){
        if(!transactions.containsKey(id)){
            System.out.println("Transaction with id " + id + " not found");
            return;
        }
        System.out.println("Transaction ID " + id + " deleted");
        transactions.remove(id);
    }

    public void editTransaction(UUID id, String... edits){
        Transaction tempTransaction = transactions.get(id);
        if(tempTransaction == null){
            System.out.println("Transaction with id " + id + " not found");
            return;
        }
        String newDescription = edits[0];
        Type newType = Type.valueOf(edits[1]);
        Category newCategory = Category.valueOf(edits[2]);
        BigDecimal newAmount = new BigDecimal(edits[3]);

        tempTransaction.setDescription(newDescription);
        tempTransaction.setType(newType);
        tempTransaction.setCategory(newCategory);
        tempTransaction.updateAmount(newAmount);
    }

    public ArrayList<Transaction> searchTransactionName(String name){
        ArrayList<Transaction> transactions = this.transactions.values().stream()
                .filter(transaction -> transaction.getDescription().equalsIgnoreCase(name))
                .collect(Collectors.toCollection(ArrayList::new));
        return transactions;
    }

    // calculating
    void updateBalance(){
        currentBalance = BigDecimal.ZERO;
        for(Transaction transaction : transactions.values()){
            currentBalance = currentBalance.add(transaction.getAmount());
        }
    }
    public void viewSummary(){
        updateBalance();
        tempListIndexing();
        System.out.println("You currently have " + transactions.size() + " transactions");
        System.out.println("Current balance: $ " + getCurrentBalance());
    }

    // set new list
    public HashMap<UUID, Transaction> getTransactions(){
        return transactions;
    }
    public void setTransactions(HashMap<UUID, Transaction> transactions){
        this.transactions = transactions;
    }

    // money stuff
    public BigDecimal getCurrentBalance(){
        return currentBalance;
    }
    public ArrayList<Transaction> tempListIndexing(){
        ArrayList<Transaction> tempList = new ArrayList<>(transactions.values());
        for(int index=0; index<tempList.size(); index++){
            System.out.println(index + ": " + tempList.get(index).toString());
        }
        return tempList;
    }

}
