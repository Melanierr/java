package item;
import entity.Character;
public abstract class Item {
    String name;
    String description;
    int amount;
    public Item(String name, String description, int amount) {
        this.name = name;
        this.description = description;
        this.amount = amount;
    }
    @Override
    public String toString() {
        return (name+"\nAmount: " +amount);
    }
    public String getName() {
        return name;
    }
    public int getAmount() {
        return amount;
    }
    public abstract void useItem(Character player);

    public abstract String toSaveString();
}