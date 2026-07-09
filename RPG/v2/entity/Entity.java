package entity;
public abstract class Entity {
    public String name;
    private int maxHp;
    private int maxMana;
    int level = 1;
    int hp;
    int atk;
    int mana;

    public Entity(String name, int maxHp, int atk, int maxMana){
        this.name = name;
        this.maxHp = maxHp;
        this.atk = atk;
        this.maxMana = maxMana;

        this.mana = maxMana;
        this.hp = maxHp;
    }
    public abstract void attack(Entity target);
    void takeDamage(double dmg){
        hp -= (int) dmg;
        System.out.println(this.name + " received " + dmg + " DMG");
    }
    abstract void levelUp();
    @Override
    public String toString(){
        return String.format("%s\nHP: %d/%d\nATK: %d\nMANA: %d/%d", this.name, this.hp, this.maxHp, this.atk, this.mana, this.maxMana);
    }
    public int getMaxHp(){
        return maxHp;
    }
    public int getHp(){
        return hp;
    }
    public String getName(){
        return name;
    }
    protected void increaseMaxHp(int amount){
        maxHp += amount;
    }
    protected void increaseMaxMana(int amount){
        maxMana += amount;
    }
    protected void restoreHp(){
        hp = maxHp;
    }
}
