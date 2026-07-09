package entity;

public class Character extends Entity{
    boolean isDefend = false;
    boolean isArmorEquipped = false;
    double currentXP = 0;
    double xpThreshold = 100;
    Character(String name, int maxHp, int atk, int mana) {
        super(name, maxHp, atk, mana);
    }
    public void healSelf(int healAmount){
        hp += healAmount;
        if(hp > getMaxHp()){
            hp = getMaxHp();
        }
    }
    @Override
    public // I tried making an evade chance here but not pro enough
    void attack(Entity target){
        target.takeDamage(atk);
    }
    void levelUp() {
        restoreHp();
        level++;
        xpThreshold *= 1.2;
    }
    public void addXp(double xp){
        this.currentXP += xp;
        if(this.currentXP >= xpThreshold) {
            levelUp();
            currentXP = 0;
        }
        else{
            System.out.println(currentXP + "/" + xpThreshold);
        }
    }
    void takeDamage(double damage){
        if(isDefend){
            hp -= 0.8 * damage;
        }
        else{
            hp -= (int) damage;
        }
    }
    public boolean isAlive(){
        return hp > 0;
    }
    public void setDefend(boolean state){
        this.isDefend = state;
    }
}
