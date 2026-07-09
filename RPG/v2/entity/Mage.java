package entity;

public class Mage extends Character{
    public Mage(String name){
        super(name, 180, 30, 50);
    }
    @Override
    void levelUp(){
        level++;
        increaseMaxHp(15);
        increaseMaxMana(15);
        restoreHp();
    }
}
