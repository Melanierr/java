public class Slime extends Enemy{
    Slime(String name){
        super(name,80,8);
    }
    @Override
    void attack(Entity target) {
        System.out.println(name + " jumps at " + target.name + " !");
        target.takeDamage(atk);
    }
}
