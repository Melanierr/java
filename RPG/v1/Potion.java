public class Potion {
    String name;
    int healAmount;

    Potion(String name, int healAmount){
        this.name = name;
        this.healAmount = healAmount;
    }

    void usePotion(Character player){
        System.out.println(player.name + " uses a potion!");
        player.heal(healAmount);
    }
}
