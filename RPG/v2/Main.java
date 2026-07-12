import Managers.*;
import entity.*;
import item.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList < Entity > mobs = new ArrayList < > ();
        Scanner scanner = new Scanner(System.in);
        CharacterManager charManager = new CharacterManager(scanner);
        DataSaver saveGame = new DataSaver();
        boolean isExit = false;
        while (!isExit) {
            System.out.println("========================");
            System.out.println("RPG GAME");
            System.out.println("""
            1. Character
            2. Battle
            3. Exit
            4. Load save""");
            String mode = scanner.nextLine();
            switch (mode) {
                case "1" -> charManager.run();
                case "2" -> {
                    if(charManager.getCharacter() == null){
                        System.out.println("No character found");
                    }
                    else {
                        charManager.runBattle();
                    }
                }
                case "3" -> {
                    isExit = true;
                    saveGame.saveData(charManager.getInventory(), charManager.getCharacter());
                }
                case "4" -> { // load save
                    saveGame.loadData();
                    charManager.loadCharacter(saveGame.name, saveGame.className, saveGame.level, saveGame.gold, saveGame.tempInventory);
                }
                default -> {
                    System.out.println("Invalid option");
                }
            }
        }
        scanner.close();
    }
}
