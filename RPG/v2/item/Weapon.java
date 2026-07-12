package item;

public class Weapon extends Item{
    private final double dmgMultiplier;
    public Weapon(String name, String description, int amount, double dmgMultiplier){
        super(name, description, amount);
        this.dmgMultiplier = dmgMultiplier;
    }
    public double getDmgMultiplier(){
        return 1 + dmgMultiplier / 100;
    }
    @Override
    public void useItem(entity.Character player) {
        System.out.println(this.name +" equipped! ATK increased " + getDmgMultiplier() + "%");
        player.setWeaponEquipped(this);
    }
    public String toSaveString() {
        return "Weapon," + getName() + "," + getAmount() + "," + getDmgMultiplier();
    }
}
