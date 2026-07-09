package entity;
public class Knight extends Character{
    public Knight(String name){
        super(name, 250, 25, 15);
    }
    @Override
    void levelUp(){
        level++;
        increaseMaxHp(20);
        increaseMaxMana(10);
        restoreHp();
    }
}
