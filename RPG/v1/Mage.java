public class Mage extends Character{
    Mage(String name) {
        super(name, 140, 40);
    }




    @Override
    void attack(Entity target) {
        System.out.println(name + " casts Fireball!");
        target.takeDamage((int) (atk * 1.4));
    }
    void levelUp(){
        super.levelUp();
        atk += 8;
    }
}
