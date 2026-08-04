import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

public class InventoryManager_V3 {
    private HashMap<String, Item> newInv;
    public InventoryManager_V3(){
        this.newInv = new HashMap<>();
    }
    public void addItem(String name, String type, int amount){
        String key = name.toLowerCase();
        if(newInv.containsKey(key)){
            newInv.get(key).addAmount(amount);
        } else {
            newInv.put(key, new Item(name, type, amount));
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
    // type search
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
            System.out.println(items + "\n");
        }
        return true;
    }
    // sort using array list here
    public void sortItem(String sortType){
        ArrayList<Item> sorted = new ArrayList<>(newInv.values());
        if(sortType.equalsIgnoreCase("name")){
            sorted.sort(Comparator.comparing(Item::getName));
        }
        else if(sortType.equalsIgnoreCase("amount")){
            sorted.sort(Comparator.comparingInt(Item::getAmount).reversed());
        }
        else if(sortType.equalsIgnoreCase("price")){
            sorted.sort(Comparator.comparingDouble(Item::getPrice));
        }
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