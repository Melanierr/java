public class Goblin extends Enemy{
    Goblin(String name){
        super(name, 120, 15);
    }
    @Override
    void attack(Entity target) {
        System.out.println(name + " jumps at " + target.name + " !");
        target.takeDamage((int) (atk * (double) 1.1));
    }
}
