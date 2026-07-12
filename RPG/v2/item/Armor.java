package item;

public class Armor extends Item {
    double defense;
    public Armor(String name, String description, int amount, double defense){
        super(name, description, amount);
        this.defense = defense;
    }
    public double getDefMultiplier(){
        return 1 - (defense / 100);
    }
    @Override
    public void useItem(entity.Character player) {
        System.out.println(this.name + " equipped! DEF increased " + defense + "%");
        player.setArmorEquipped(this);
    }
    public String toSaveString() {
        return "Armor," + getName() + "," + getAmount() + "," + getDefMultiplier();
    }
}
