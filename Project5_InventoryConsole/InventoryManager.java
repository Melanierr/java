import java.util.ArrayList;
import java.util.Scanner;

public class InventoryManager {
    private Scanner sc;
    private ArrayList<Item> inventory;

    public InventoryManager(Scanner scanner){
        this.sc = scanner;
        this.inventory = new ArrayList<>();
    }
    public void addItem(String name, String type, int amount, double price){
        for(Item item : inventory){
            if(item.getName().equalsIgnoreCase(name)){
                if(item.getAmount() + amount > Item.maxStack){
                    System.out.println("Stack limit exceeded.");
                    return;
                }

                item.addAmount(amount);
                return;
            }
        }

        inventory.add(new Item(name, type, amount, price));
    }

    public void deleteItem(String name, int amount){
        if(inventory.isEmpty()){
            System.out.println("Inventory is empty.");
            return;
        }
        Item target = null;
        for(Item item : inventory){
            if(item.getName().equalsIgnoreCase(name)){
                if(!item.deleteAmount(amount)){
                    System.out.println("Not enough items to delete.");
                    return;
                }

                if(item.getAmount() == 0){
                    target = item;
                }

                System.out.printf("Removed %s x%d%n", name, amount);
                break;
            }
        }
        if(target != null){
            inventory.remove(target);
        }
    }

    public Item searchItemID(String id){
        if(inventory.isEmpty()){
            System.out.println("Inventory is empty.");
            return null;
        }
        for(Item item : inventory){
            if(item.getId().equalsIgnoreCase(id)){
                return item;
            }
        }
        return null;
    }
    public Item searchItemName(String name){
        if(inventory.isEmpty()){
            System.out.println("Inventory is empty.");
            return null;
        }
        for(Item item : inventory){
            if(item.getName().equalsIgnoreCase(name)){
                return item;
            }
        }
        return null;
    }


    public void viewInventory(){
        if(inventory.isEmpty()){
            System.out.println("Inventory is empty.");
            return;
        }
        for(Item item : inventory){
            System.out.println(item);
            System.out.println();
        }
    }
    public ArrayList<Item> getInventory(){
        return inventory;
    }
    public void setNewInv(ArrayList<Item> inventory){
        this.inventory = inventory;
    }
}
