package Managers;
import entity.*;
import item.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class BattleManager {
    private final Scanner scanner;
    private final entity.Character character;
    boolean isDefeated = false;
    boolean isForfeit = false;
    ArrayList<Enemy> enemies = new ArrayList<>();
    ArrayList<Item> inventory;
    Random  rand = new Random();
    public BattleManager(entity.Character character,  ArrayList<Item> inventory, Scanner scanner) {
        this.scanner = scanner;
        this.character = character;
        this.inventory = inventory;
    }
    public void run(){
        generateEnemy();
        System.out.println("Enemies appear!!");
        for (int i = 0; i < enemies.size(); i++) {
            System.out.println(i + " " + enemies.get(i).toString());
        }
        System.out.println(character);
        do {
            System.out.println("Choose an option:\n1.Attack\n2.Defend\n3.Use Item\n4.Forfeit");
            String option = scanner.nextLine();
            switch (option) {
                case "1" -> {
                    for (int i = 0; i < enemies.size(); i++) {
                        System.out.println(i + " " + enemies.get(i).toString());
                    }
                    System.out.println("Attack who?");
                    int enemySelection = Integer.parseInt(scanner.nextLine());
                    if (enemySelection < 0 || enemySelection >= enemies.size()){
                        System.out.println("Enemy not found");
                        continue;
                    }
                    else {
                        character.attack(enemies.get(enemySelection));
                    }
                }
                case "2" -> {
                    System.out.println(character.getName() + " defended for the next turn");
                    character.setDefend(true);
                }
                case "3" -> {
                    InventoryManager inv = new InventoryManager(scanner, inventory, character);
                    inv.run(true);
                    }
                case "4" -> isForfeit = true;
                default -> {
                    System.out.println("Invalid option.");
                    continue;
                }
            }
            if(!isForfeit && !isDefeated) {
                randomEnemiesAttack();
                if (!character.isAlive()) {
                    isDefeated = true;
                }
                updateBattlefield();
                character.setDefend(false);
            }
            if(enemies.isEmpty()){
                System.out.println("Victory! ⚔️🔥");
                break;
            }

        }while(!isForfeit && !isDefeated);

    }
    void generateEnemy() {
        enemies.clear();
        enemies.add(new Enemy("Goblin Thief", 100, 15));
        /* enemies.add(new Enemy("Slime", 80, 5));
        enemies.add(new Enemy("Fire Dragon", 350, 20));
        enemies.add(new Enemy("Ninja", 130, 25)); */
    }
    void randomEnemiesAttack(){
        int randomChoice =  rand.nextInt(enemies.size());
        if(enemies.get(randomChoice).isAlive()) {
            enemies.get(randomChoice).attack(character);
        }
    }
    void updateBattlefield(){
        for(int i = enemies.size() - 1; i >= 0; i--){
            if(!enemies.get(i).isAlive()){
                System.out.println(enemies.get(i).getName() + " has been defeated!");
                double gainedXp = enemies.get(i).getMaxHp() * 0.75;
                System.out.printf("%s received %.1f XP.\n", character.getName(), gainedXp);
                character.addXp(gainedXp);
                Item droppedItem = generateLoot();
                System.out.println(enemies.get(i).getName() + " dropped " +  droppedItem.getName() + "x" + droppedItem.getAmount());
                inventory.add(droppedItem);
                character.addGold(rand.nextInt(20, 51));
                enemies.remove(i);
            }
        }
    }
    Item generateLoot(){ // imaginge a randomized list
        Item lootdrop = new Potion("Potion of Healing", "", 1, 75);
        return lootdrop;
    }

}
