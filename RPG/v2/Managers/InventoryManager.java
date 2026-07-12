package Managers;
import entity.*;
import item.*;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class InventoryManager {
    private final Scanner scanner;
    private final ArrayList<Item> inventory;
    private final entity.Character character;
    public InventoryManager(Scanner scanner, ArrayList<Item> inventory, entity.Character character) {
        this.scanner = scanner;
        this.inventory = inventory;
        this.character = character;
    }
    public void run(boolean inCombat){
        boolean inInventory = true;
        while (inInventory) {
            System.out.println("========================");
            System.out.println("INVENTORY MENU");
            System.out.println("""
                    1.View inventory
                    2.Use item
                    3.Discard item
                    4.Add item
                    5.Exit inventory""");
            String selectedMode = scanner.nextLine();
            switch (selectedMode) {
                case "1"-> showInventory();
                case "2"-> {
                    if(isEmpty()){
                        System.out.println("Inventory is empty.");
                        continue;
                    }
                    System.out.print("Select an item: ");
                    String name = scanner.nextLine();
                    if(!isEmpty()) {
                        for (Item item : inventory) {
                            if (name.equals(item.getName())) {
                                item.useItem(character);
                                break;
                            }
                        }
                    }
                }
                case "3" -> {
                    if(inCombat){
                        System.out.println("You are in combat!");
                        continue;
                    }
                    if(isEmpty()){
                        System.out.println("Inventory is empty.");
                        continue;
                    }
                    showInventory();
                    System.out.print("Select an item: ");
                    String name = scanner.nextLine();
                    for(int i=0; i< inventory.size(); i++) {
                        if(name.equals(inventory.get(i).getName())){
                            inventory.remove(i);
                            System.out.println("Sucessfully removed " + name);
                            break;
                        }
                        else{
                            System.out.println("No item found");
                        }
                    }
                }
                case "4"-> {
                    int itemAmount = 0;
                    if(inCombat){
                        System.out.println("You are in combat!");
                        continue;
                    }
                    System.out.print("Choose item type (Potion, Armor, Weapon): ");
                    String itemType = scanner.nextLine();
                    System.out.print("Enter item name: ");
                    String itemName = scanner.nextLine();
                    if(itemType.equals("Potion")) {
                        System.out.print("Enter item amount: ");
                        itemAmount = scanner.nextInt(); scanner.nextLine();
                    }
                    switch (itemType.toLowerCase()) {
                        case "potion"-> inventory.add(new Potion(itemName, "", itemAmount, 50));
                        case "armor"-> {
                            System.out.print("Defense multiplier %: ");
                            double defenseMultiplier = scanner.nextDouble();
                            inventory.add(new Armor(itemName, "", 1, defenseMultiplier));
                        }
                        case "weapon"-> {
                            System.out.print("Damage multiplier %: ");
                            double damageMultiplier = scanner.nextDouble();
                            inventory.add(new Weapon(itemName, "", 1, damageMultiplier));
                        }
                    }
                }
                case "5"-> inInventory = false;
            }
            updateInventory();
        }
    }
    void showInventory(){
        System.out.println("======================");
        System.out.println("INVENTORY");
        if(isEmpty()) {
            System.out.println("Inventory is empty.");
        }
        else {
            for (Item items : inventory) {
                System.out.println("\n");
                System.out.println(items);
            }
        }
    }
    void updateInventory(){
        for (int i=0; i < inventory.size(); i++) {
            if(inventory.get(i).getAmount() <= 0){
                inventory.remove(i); // remove item at 0 uses
            }
        }
    }
    public boolean isEmpty(){
        return inventory.isEmpty();
    }
}
