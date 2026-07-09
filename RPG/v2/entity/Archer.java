package entity;

public class Archer extends Character{
    public Archer(String name){
        super(name, 200, 15, 25);
    }
    @Override
    void levelUp(){
        level++;
        increaseMaxHp(15);
        increaseMaxMana(10);
        restoreHp();
    }
}
