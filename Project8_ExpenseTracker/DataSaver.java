import java.io.*;
import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class DataSaver {
    public DataSaver(){
        System.out.println("Data saver is loading...");
    }

    public void saveFile(ArrayList<Transaction> transactions){ // better to use the array then get the uuid when loading
        try(FileWriter fw = new FileWriter("savefile.txt")){
            for(Transaction tr : transactions){
                fw.write(tr.toSave());
                fw.write("\n");
            }
        }
        catch(IOException error){
            System.out.println("Error during file writing process.");
        }
    }
    public HashMap<UUID,Transaction> loadFile() {
        HashMap<UUID, Transaction> transactions = new HashMap<>();
        try(BufferedReader br = new BufferedReader(new FileReader("savefile.txt"))){
            String line;
                while ((line = br.readLine()) != null) {
                    try {
                        String[] data = line.split(",");
                        if (data.length != 5) {
                            System.out.println("Malformed save format.");
                            continue;
                        }
                        String description = data[0];
                        UUID uuid = UUID.fromString(data[1]);
                        Type type = Type.valueOf(data[2]);
                        BigDecimal amount = new BigDecimal(data[3]);
                        LocalDate date = LocalDate.parse(data[4]);
                        Transaction newTransaction = new Transaction(description, uuid, type, amount, date);
                        transactions.put(uuid, newTransaction);
                    }
                    catch(DateTimeException error){
                        System.out.println("Malformed date format. Skipping.");
                    }
                    catch(IllegalArgumentException error){
                        System.out.println("Malformed UUID format. Skipping.");
                    }
                }

        }

        catch(FileNotFoundException error){
            System.out.println("Cannot locate save file.");
        }
        catch(IOException error){
            System.out.println("Error during file reading.");
        }
        return transactions;
    }
}
