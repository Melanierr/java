public class Enemy extends Entity{
    Enemy(String name, int maxHp, int atk){
        super(name,maxHp, atk);
    }
    @Override
    void attack(Entity target) {
        System.out.println(name + " attacked " + target.name);
        target.takeDamage(atk);

    }
}

