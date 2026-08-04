import java.util.ArrayList;
import java.util.Comparator;

public class InventoryManager {
    private ArrayList<Item> inventory;
    public InventoryManager(){
        this.inventory = new ArrayList<>();
    }
    public void addItem(String name, String type, int amount){
        if(amount < 0){
            System.out.println("Count must be larger than 0.");
            return;
        }
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
        inventory.add(new Item(name, type, amount));
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
    public void sortItem(String sortType){
        switch(sortType.toLowerCase()){
            case "name" -> inventory.sort(Comparator.comparing(Item::getName));
            case "amount" -> inventory.sort(Comparator.comparingInt(Item::getAmount).reversed());
            case "price" -> inventory.sort(Comparator.comparingDouble(Item::getPrice));
            default -> {
                System.out.println("Invalid sort type.");
                return;
            }
        }
        viewInventory();
    }
    public void viewByType(String type){ // new version
        boolean found = false;
        for(Item item : inventory){
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
        if(inventory.isEmpty()){
            System.out.println("Inventory is empty.");
            return false;
        }
        for(Item item : inventory){
            System.out.println(item);
            System.out.println();
        }
        return true;
    }
    public ArrayList<Item> getInventory(){
        return inventory;
    }
    public void setNewInv(ArrayList<Item> inventory){
        this.inventory = inventory;
    }
}
