import java.util.Random;

public class Item {
    private String itemName;
    private String itemId;
    private String itemType;
    private double itemPrice;
    private int amount = 0;
    public static int maxStack = 32;
    private static int nextId = 1;
    private Random randomizer = new Random();
    public Item(String itemName, String itemType, int amount) {
        this.itemName = itemName;
        this.itemType = itemType;
        this.itemPrice = randomizer.nextDouble(1, 51);
        this.amount = amount;
        this.itemId = "I" + String.format("%03d", nextId++);
    }
    public Item(String itemName, String itemType, int amount, double price) {
        this.itemName = itemName;
        this.itemType = itemType;
        this.itemPrice = price;
        this.amount = amount;
        this.itemId = "I" + String.format("%03d", nextId++);
    }
    public void addAmount(int amount){
        this.amount += amount;
    }
    public boolean deleteAmount(int amount){
        if(amount > this.amount){
            return false;
        }
        this.amount -= amount;
        return true;
    }
    public String getId(){
        return itemId;
    }
    public String getName(){
        return itemName;
    }
    public String getType(){
        return itemType;
    }
    public double getPrice(){
        return itemPrice;
    }
    public int getAmount(){
        return amount;
    }
    @Override
    public String toString() {
        return String.format("Name: %s\nQuantity: %d\nType: %s\nPrice: %.2f", itemName, amount, itemType, itemPrice);
    }
    public String toSave(){
        return itemName + "," + itemType + "," + itemPrice + "," + amount;
    }
}
