public class Knight extends Character{
    Knight(String name){
        super(name, 200, 25);
    }




    @Override
    void attack(Entity target) {
        System.out.println(name + " swings his sword!");
        target.takeDamage((int) (atk * 1.2));
    }
    void levelUp(){
        super.levelUp();
        atk += 5;
    }
}
