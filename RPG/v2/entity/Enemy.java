package entity;
public class Enemy extends Entity {
    public Enemy(String name, int maxHp, int atk){
        super(name, maxHp, atk, 0);
    }
    @Override
    void levelUp(){};
    public void attack(Entity target){
        target.takeDamage(atk);
        System.out.println(name + " attacked " + target.name);
    }
    public boolean isAlive(){
        return hp > 0;
    }
}
