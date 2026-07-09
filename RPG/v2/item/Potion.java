package item;
import entity.Character;
public class Potion extends Item{ // let's just make it heal potion for now
    private final int heal;
    public Potion(String name, String description, int amount, int heal){
        super(name, description, amount);
        this.heal = heal;
    }
    @Override
    public void useItem(Character player){
        player.healSelf(heal);
        this.amount -= 1;
    }
}
