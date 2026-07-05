public abstract class Entity {
    String name;
    int hp;
    int maxHP;
    int atk;
    public Entity(String name, int maxHp, int atk) {
        this.name = name;
        this.maxHP = maxHp;
        this.atk = atk;
        this.hp = maxHp;
    }
    // concrete methods
    boolean isAlive(){
        return hp>0;
    }
    void takeDamage(int damage){
        hp -= damage;
        if(hp < 0){
            hp = 0;
        }
    }
    void heal(int heal){
        hp += heal;
        if(hp > maxHP){
            hp = maxHP;
        }
    }
    // toString
    public String toString(){
        return (name + "\nHP: " + hp +"/"+maxHP+"\nATK: "+atk);
    }
    abstract void attack(Entity target);
}
