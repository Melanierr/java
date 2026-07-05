public class Character extends Entity{
    int level = 0;
    Character(String name, int maxHp, int atk) {
        super(name, maxHp, atk);
        level = 1;
    }
    @Override
    public String toString(){
        return (name + "\nHP: " + hp +"/"+maxHP+"\nATK: "+atk+"\nLevel:" + level);
    }

    void attack(Entity target) {
        System.out.println(name + " attacked " + target.name);
        target.takeDamage(atk);
    }
    void levelUp(){
        level++;
        maxHP+=20;
        atk+=5;

    }
}
