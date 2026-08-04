import java.util.Random;

public class Item {
    private final String itemName;
    private final String itemId;
    private final String itemType;
    private final double itemPrice;
    private int amount;
    public static int maxStack = 32;
    private static int nextId = 1;
    private static final Random randomizer = new Random();
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
        if(amount < 0){
            System.out.println("Cannot add negative amount.");
            return;
        }
        this.amount += amount;
    }
    public boolean deleteAmount(int amount){
        if(amount > this.amount){
            return false;
        }
        if(amount < 0){
            this.amount = 0;
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
