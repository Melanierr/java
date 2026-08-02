import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        InventoryManager inv = new InventoryManager(sc);
        InventorySave save = new InventorySave();
        boolean isExit = false;
        while (!isExit) {
            System.out.println("\n==== INVENTORY ====");
            System.out.print("""
                    1.View inventory
                    2.Search item
                    3.Add item
                    4.Delete item
                    5.Save inventory
                    6.Load new inventory
                    7.Sort inventory
                    8.Exit
                    Choose an option:\s""");
            String option = sc.nextLine();
            switch (option) {
                case "1" -> {
                    boolean viewed = inv.viewInventory();
                    if(viewed){
                        System.out.println("Enter R to open sort mode.\nEnter F to view by type.\nEnter anything else to quit.");
                        if(sc.nextLine().equalsIgnoreCase("r")){
                            System.out.print("Sort by? (Name/Amount/Price) ");
                            inv.sortItem(sc.nextLine());
                        }
                        if(sc.nextLine().equalsIgnoreCase("f")){
                            System.out.print("Enter type of item: ");
                            inv.viewByType(sc.nextLine().trim());
                        }
                    }
                }
                case "2" -> {
                    System.out.print("Enter item's name: ");
                    String userInput = sc.nextLine().trim();
                    Item item = inv.searchItemName(userInput);
                    if(item != null) {
                        System.out.println(item);
                    }else{
                        System.out.println("Item not found.");
                    }
                }
                case "3" -> {
                    System.out.print("Enter item's name: ");
                    String userInput1 = sc.nextLine().trim();
                    System.out.print("Enter item's type: ");
                    String userInput2  = sc.nextLine().trim();
                    System.out.print("Enter item amount: ");
                    int userInput3 =  sc.nextInt(); sc.nextLine();
                    inv.addItem(userInput1, userInput2, userInput3, 10);
                }
                case "4" -> {
                    System.out.print("Enter item's name: ");
                    String userInput1 = sc.nextLine().trim();
                    System.out.print("Delete how many? ");
                    int userInput2 = sc.nextInt(); sc.nextLine();
                    inv.deleteItem(userInput1, userInput2);
                }
                case "5" -> save.saveInventory(inv.getInventory());
                case "6" -> inv.setNewInv(save.loadInventory());
                case "7" -> {
                    System.out.println("Sort by? (Name/Amount/Price)");
                    inv.sortItem(sc.nextLine().trim().toLowerCase());
                }
                case "8" -> isExit = true;
                case "2026" -> { // secret
                    boolean isDebugConsole = true;

                    do{
                        System.out.print("""
                        Welcome to debug console
                        Sort inventory list
                        Choose an option:\s""");
                        String choice = sc.nextLine();
                        switch(choice) {
                            case "1" -> {
                                inv.getInventory().sort(
                                        Comparator.comparingInt(Item::getAmount)
                                                .reversed()
                                );
                                for(Item item : inv.getInventory()){
                                    System.out.println(item);
                                }
                            }
                            case "exit" -> isDebugConsole = false;
                            default -> System.out.println("Invalid choice.");
                        }
                    }while(!isDebugConsole);
                }
            }
        }
    }
}
