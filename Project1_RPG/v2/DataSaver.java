import item.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class DataSaver { // requests inventory, character
    String line;
    String name;
    String className;
    int level;
    int gold;
    ArrayList<Item> tempInventory = new ArrayList<>();
    public void saveData(ArrayList<Item> inventory, entity.Character character) {
        try(FileWriter writer = new FileWriter("saved_data.txt")) {
            writer.write("Name=" + character.getName() + "\n");
            writer.write("Class=" + character.getCurrentClass() + "\n");
            writer.write("Level=" + character.getLevel() + "\n");
            writer.write("Gold=" + character.getGold() + "\n");
            for (Item item : inventory) {
                writer.write(item.toSaveString() + "\n");
            }
        }
        catch(IOException error) {
            System.out.println("Something went wrong.");
        }
    }
    public void loadData(){
        try(BufferedReader reader = new BufferedReader(new FileReader("saved_data.txt"))){
            while((line = reader.readLine()) != null){
                line = line.trim();
                if(line.contains("=")){
                    String[] charparts = line.split("=");
                    switch(charparts[0]){
                        case "Name" -> {
                            name = charparts[1];
                        }
                        case "Class" -> {
                            className = charparts[1];
                        }
                        case "Level" -> {
                            level = Integer.parseInt(charparts[1]);
                        }
                        case "Gold" -> {
                            gold = Integer.parseInt(charparts[1]);
                        }
                    }
                }
                else if(line.contains(",")) {
                    String[] invparts = line.split(",");
                    switch (invparts[0]) {
                        case "Potion" -> {
                            Item potion = new Potion(invparts[1], "", Integer.parseInt(invparts[2]), Integer.parseInt(invparts[3]));
                            tempInventory.add(potion);
                        }
                        case "Armor" -> {
                            Item armor = new Armor(invparts[1], "", Integer.parseInt(invparts[2]), Double.parseDouble(invparts[3]));
                            tempInventory.add(armor);
                        }
                        case "Weapon" -> {
                            Item weapon = new Weapon(invparts[1], "", Integer.parseInt(invparts[2]), Double.parseDouble(invparts[3]));
                            tempInventory.add(weapon);
                        }
                    }
                }
                if(line.isBlank()){
                    continue;
                }
            }
        }
        catch(FileNotFoundException error){
            System.out.println("Cannot find save file.");
        }
        catch(IOException error){
            System.out.println("Something went wrong.");
        }
    }
}
