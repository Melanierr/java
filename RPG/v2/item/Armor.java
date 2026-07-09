package item;

public class Armor extends Item {
    double defense;
    public Armor(String name, String description, int amount, double defense){
        super(name, description, amount);
        this.defense = defense / 100;
    }
    public double getDefense(){
        return defense;
    }
    @Override
    public void useItem(entity.Character player) {
        System.out.println("Armor has been equipped! Taken less " + getDefense() + "x DMG");
        this.amount -= 1;
    }
}
