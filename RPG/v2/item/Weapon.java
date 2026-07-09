package item;

public class Weapon extends Item{
    private final double dmgMultiplier;
    public Weapon(String name, String description, int amount, double dmgMultiplier){
        super(name, description, amount);
        this.dmgMultiplier = dmgMultiplier;
    }
    double getDmgMultiplier(){
        return dmgMultiplier;
    }
    @Override
    public void useItem(entity.Character player) {
        System.out.println(this.name +" equipped! Buffed atk by " + getDmgMultiplier());
        this.amount -= 1;
    }
}
