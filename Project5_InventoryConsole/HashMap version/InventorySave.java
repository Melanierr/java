import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class InventorySave {
    public void saveInventory(HashMap<String, Item> inventory) {
        try(FileWriter fw = new FileWriter("inv.txt")){
            ArrayList<Item> tempList = new ArrayList<>(inventory.values());
            for(Item save : tempList){
                fw.write(save.toSave() + "\n");
            }
        }
        catch(IOException error){
            System.out.println("Error writing to file.");
        }
    }
    public HashMap<String, Item> loadInventory(){
        HashMap<String, Item> loadedItems = new HashMap<>();
        try(BufferedReader reader = new BufferedReader(new FileReader("inv.txt"))){
            String line;
            while((line = reader.readLine()) != null){
                String[] lineParts = line.split(",");
                String itemName = lineParts[0];
                String itemType = lineParts[1];
                double price = Double.parseDouble(lineParts[2]);
                int amount = Integer.parseInt(lineParts[3]);
                Item newItem = new Item(itemName, itemType, amount, price);
                loadedItems.put(itemName.toLowerCase(), newItem);
            }

        }
        catch(IOException error){
            System.out.println("Error trying to read file.");
        }
        return loadedItems;
    }
}
