import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

public class InventoryManagerv3 {
    private Scanner sc;
    private HashMap<String, Item> newInv;
    public InventoryManagerv3(Scanner scanner){
        this.sc = scanner;
        this.newInv = new HashMap<>();
    }
    public void addItem(String name, String type, int amount, double price){
        String key = name.toLowerCase();
        if(newInv.containsKey(key)){
            newInv.get(key).addAmount(amount);
        } else {
            newInv.put(key, new Item(name, type, amount, price));
        }
    }

    public void deleteItem(String name, int amount){
        String key = name.toLowerCase();
        if(newInv.containsKey(key)){
            Item item = newInv.get(key);
            if(!item.deleteAmount(amount)){
                System.out.println("Not enough items to remove.");
                return;
            }
            System.out.println("Successfully removed item.");
            if(item.getAmount() == 0){
                newInv.remove(key);
            }
        } else {
            System.out.println("Item not found.");
        }
    }

    public Item searchItemName(String name){
        return newInv.get(name.toLowerCase());
    }

    public void viewByType(String type){
        boolean found = false;
        for(Item item : newInv.values()){
            if(item.getType().equalsIgnoreCase(type)){
                System.out.println(item);
                found = true;
            }
        }
        if(!found){
            System.out.println("No items found in this category.");
        }
    }
    public boolean viewInventory(){
        if(newInv.isEmpty()){
            System.out.println("Inventory is empty.");
            return false;
        }
        for(Item items :newInv.values()){
            System.out.println(items);
        }
        return true;
    }
    public void sortByName(){
        ArrayList<Item> sorted = new ArrayList<>(newInv.values());
        sorted.sort(Comparator.comparing(Item::getName));

        for(Item item : sorted){
            System.out.println(item);
        }
    }
    public HashMap<String, Item> getInventory(){
        return newInv;
    }
    public void setNewInv(HashMap<String, Item> newInv){
        this.newInv = newInv;
    }
}
