public class Main{
    static public void main(String[] args){
        Knight knight = new Knight("Arthur");
        Mage mage = new Mage("Merlin");
        Enemy slime = new Slime("Purple Slime");
        Enemy goblin = new Goblin("Goblin Thief");
        Potion potion1 = new Potion("Big Potion", 50);

        // battle
        knight.attack(slime);
        slime.attack(knight);
        mage.attack(goblin);
        goblin.attack(mage);
        potion1.usePotion(knight);
        System.out.println("=================================");
        // array
        Entity[] battlefield ={knight, mage, goblin, slime};
        for(Entity entity: battlefield){
            System.out.println(entity);
        }
    }
}
