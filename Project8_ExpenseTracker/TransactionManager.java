import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class TransactionManager {
    private HashMap<UUID, Transaction> transactions;
    private BigDecimal currentBalance;

    public TransactionManager() {
        transactions = new HashMap<>();
        currentBalance = BigDecimal.ZERO;
    }

    // transaction handling
    public void createTransaction(String description, Type type, BigDecimal amount){
        Transaction newTransaction = new Transaction(description, type, amount);
        transactions.put(newTransaction.getUUID(), newTransaction);
    }
    public void deleteTransaction(UUID id){
        if(!transactions.containsKey(id)){
            System.out.println("Transaction with id " + id + " not found");
            return;
        }
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
        BigDecimal newAmount = new BigDecimal(edits[2]);

        if(newType == Type.EXPENSE){
            newAmount = newAmount.negate();
        }

        tempTransaction.setDescription(newDescription);
        tempTransaction.setType(newType);
        tempTransaction.setAmount(newAmount);
    }
    public ArrayList<Transaction> searchTransactionName(String name){
        name = name.toLowerCase();
        ArrayList<Transaction> transactions = new ArrayList<>();
        for(Transaction searching : this.transactions.values()){
            if(searching.getDescription().toLowerCase().contains(name)) {
                transactions.add(searching);
                // why contain? cuz like if they type only some keyword it can check, on cases that there are repeated keywords then I guess show the other results also
                // but then another design appear because we only search for one transaction? oh, god ong im dying
                // ok bro just return an arraylist of the results 🤔 It's finally fixed.
            }
        }
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
            System.out.println(index + ": " + tempList.get(index));
        }
        return tempList;
    }
}
