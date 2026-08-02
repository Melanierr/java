import java.io.*;
import java.util.ArrayList;
public class InventorySave {
    public void saveInventory(ArrayList<Item> items){
        try(FileWriter fw = new FileWriter("inv.txt")){
            for(Item save : items){
                fw.write(save.toSave() + "\n");
            }
        }
        catch(IOException error){
            System.out.println("Error writing to file.");
        }
    }
    public ArrayList<Item> loadInventory(){
        ArrayList<Item> items = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader("inv.txt"))){
            String line;
            while((line = reader.readLine()) != null){
                String[] lineParts = line.split(",");
                String itemName = lineParts[0];
                String itemType = lineParts[1];
                double price = Double.parseDouble(lineParts[2]);
                int amount = Integer.parseInt(lineParts[3]);
                Item newItem = new Item(itemName, itemType, amount, price);
                items.add(newItem);
            }

        }
        catch(IOException error){
            System.out.println("Error trying to read file.");
        }
        return items;
    }
}
