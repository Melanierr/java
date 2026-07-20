import java.io.*;
import java.util.ArrayList;

public class AccountsSaver {
    private ArrayList<BankAccount> accountsList = new ArrayList<>();
    int currentId;
    AccountsSaver(){}
    public void saveList(ArrayList<BankAccount> accountsList, int id){
        try(FileWriter fw = new FileWriter("AccountsList.txt")){
            for (BankAccount bankAccount : accountsList) {
                fw.write(bankAccount.toString());
            }
            fw.write("ID:" + id);
        }
        catch(IOException e){
            System.out.println("Something interrupted.");
        }
    }
    public ArrayList<BankAccount> loadList(){
        try(BufferedReader reader = new BufferedReader(new FileReader("AccountsList.txt"))){
            String line;
            String name = "";
            String accId = "";
            String type = "";
            double balance;
            while( (line = reader.readLine()) != null){
                String[] lineSplit = line.split(":");
                switch(lineSplit[0]){
                    case "ID" -> currentId = Integer.parseInt(lineSplit[1]);
                    case "Name" -> name  = lineSplit[1].trim();
                    case "Account number" ->  accId = lineSplit[1].trim();
                    case "Type" -> type = lineSplit[1].trim();
                    case "Balance" -> {
                        balance = Double.parseDouble(lineSplit[1]);
                        switch(type){
                            case "savingsaccount" -> {
                                BankAccount newAcc = new SavingsAccount(name, accId, balance);
                                accountsList.add(newAcc);
                            }
                            case "checkingaccount" -> {
                                BankAccount newAcc = new CheckingAccount(name, accId, balance);
                                accountsList.add(newAcc);
                            }

                        }
                    }
                }

            }
        }
        catch(IOException e){
            System.out.println("Something interrupted.");
        }
        return accountsList;
    }
}
