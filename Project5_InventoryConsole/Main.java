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
                    7.Exit
                    Choose an option:\s""");
            String option = sc.nextLine();
            switch (option) {
                case "1" -> inv.viewInventory();
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
                case "7" -> isExit = true;
            }
        }
    }
}
