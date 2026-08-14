import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Saver saver = new Saver();
        ContactBookManager manager = new ContactBookManager();
        boolean isExit = false;
        while(!isExit){
            System.out.print("""
                    \n
                    ==== OPEN-SOURCE CONTACT BOOK ====
                    1.Search contact
                    2.Add new contact
                    3.Delete contact
                    4.Edit contact
                    5.View all contacts
                    ==================================
                    6.Save contact book
                    7.Load new contact book
                    8.Exit
                    CMD:\s""");
            String selection = scanner.nextLine();
            switch (selection) {
                case "1" -> {
                    System.out.println("============");
                    System.out.print("1.Name\n2.Phone number\nCMD: ");
                    String choice = scanner.nextLine();
                    switch (choice.toLowerCase().trim()) {
                        case "1", "name" -> {
                            System.out.print("Enter contact name: ");
                            String input = scanner.nextLine();
                            Contact temp = manager.searchContactName(input);
                            if(temp != null){
                                System.out.println(temp);
                            }else{
                                System.out.println("Contact not found.");
                            }
                        }
                        case "2", "phone", "number" -> {
                            System.out.print("Enter contact number: ");
                            String input = scanner.nextLine();
                            Contact temp = manager.searchContactNumber(input);
                            if(temp != null){
                                System.out.println(temp);
                            }else{
                                System.out.println("Contact not found.");
                            }
                        }
                    }
                }
                case "2" -> {
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine().trim();
                    System.out.print("Enter phone number: ");
                    String number = scanner.nextLine().trim();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine().trim();
                    manager.addContact(name, email, number);
                }
                case "3" -> {
                    System.out.print("Enter phone number: ");
                    String number = scanner.nextLine().trim();
                    manager.deleteContact(number);
                }
                case "4" -> {
                    System.out.print("Enter current contact's number: ");
                    String old_contact = scanner.nextLine().trim();
                    Contact contact = manager.searchContactNumber(old_contact);
                    if(contact == null){
                        System.out.println("Contact not found.");
                        continue;
                    }
                    String currentName = contact.getName();
                    String currentEmail = contact.getEmail();
                    String currentNumber = contact.getNumber();

                    System.out.println("Leave blank if unchanged.");
                    System.out.print("Enter new newName: ");
                    String newName = scanner.nextLine().trim();
                    if (newName.isBlank()) {
                        newName = currentName;
                    }

                    System.out.print("Enter new newEmail: ");
                    String newEmail = scanner.nextLine().trim();
                    if (newEmail.isBlank()) {
                        newEmail = currentEmail;
                    }
                    else if(!newEmail.contains("@")){
                        System.out.println("Invalid email format.");
                        continue;
                    }

                    System.out.print("Enter new phone newNumber: ");
                    String newNumber = scanner.nextLine().trim();
                    if (newNumber.isBlank()) {
                        newNumber = currentNumber;
                    }
                    else if(manager.searchContactNumber(newNumber) != null){ // if it finds a contact with the said number it will tell this number is already used
                        System.out.println("This newNumber is already used.");
                        continue;
                    }

                    String[] updates = {newName, newEmail, newNumber};
                    manager.updateContact(manager.searchContactNumber(old_contact), updates);
                }
                case "5" -> {
                    manager.viewContacts();
                    System.out.println("Enter R to see sort list (A-Z)");
                    String option = scanner.nextLine();
                    if(option.equalsIgnoreCase("r")){
                        for(Contact objects : manager.viewSortedList()){
                            System.out.println(objects);
                        }
                    }
                }
                case "6" -> saver.save(manager.getContactBook());
                case "7" -> manager.setContactBook(saver.load());
                case "8" -> isExit = true;
                default -> System.out.println("Invalid command");
            }
        }
        scanner.close();
    }
}
