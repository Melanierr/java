import java.util.Scanner;
import Class.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new  Scanner(System.in);
        LibraryManager manager = new LibraryManager(sc);
        DataSaver saver = new DataSaver(manager.getBookList(), manager.getMemberList(), manager.getLoanList(), manager);
        boolean isExit = false;
        do {
            System.out.println("====DONUT LIBRARY MANAGER====");
            System.out.println("Category:\n1.Manage Books\n2.Manage Members\n3.Save Database\n4.Load Database\n5.Exit");
            String category = sc.nextLine();
            switch (category) {
                case "1" -> {
                    boolean isExitBook = false;
                    do {
                        System.out.println("The library currently has " + Book.numberOfBooks + " books.");
                        System.out.print("1.Search book\n2.Add book\n3.Delete book\n4.Import book list\n5.Exit\nChoose an option: ");
                        String option = sc.nextLine().trim();
                        switch (option) {
                            case "1" -> {
                                if (manager.isEmpty("book")) {
                                    continue;
                                }
                                System.out.print("Enter book name:");
                                String bookName = sc.nextLine();
                                System.out.println(manager.searchBook(bookName));
                            }
                            case "2" -> {
                                System.out.print("Enter book name: ");
                                String userInput1 = sc.nextLine();
                                System.out.print("Enter author's name: ");
                                String userInput2 = sc.nextLine();
                                System.out.print("Enter release year:");
                                int userInput3 = sc.nextInt();
                                sc.nextLine();
                                manager.addBook(userInput1, userInput2, userInput3);
                            }
                            case "3" -> {
                                if (manager.isEmpty("book")) {
                                    continue;
                                }
                                System.out.print("Enter book's ISBN: ");
                                String userInput1 = sc.nextLine();
                                manager.deleteBook(userInput1);
                            }
                            case "4" -> manager.importBook();
                            case "5" -> isExitBook = true;
                        }
                    }while(!isExitBook);
                }
                case "2" -> {
                    boolean isExitMember = false;
                    do {
                        System.out.print("1.Add member\n2.Delete member\n3.View member\n4.View current loan list\n5.Add new loan\n6.Delete loan\n7.Exit\nChoose an option: ");
                        String option = sc.nextLine().trim();
                        switch (option) {
                            case "1" -> {
                                System.out.println("Enter name: ");
                                String userInput1 = sc.nextLine();
                                System.out.println("Enter age:");
                                int userInput2 = sc.nextInt();
                                sc.nextLine();
                                manager.addMember(userInput1, userInput2);
                            }
                            case "2" -> {
                                System.out.print("Enter name: ");
                                String userInput1 = sc.nextLine();
                                System.out.println("Confirm? Y/N");
                                if (sc.nextLine().equalsIgnoreCase("Y")) {
                                    manager.deleteMember(userInput1);
                                }
                            }
                            case "3" -> {
                                System.out.print("Enter name: ");
                                String userInput1 = sc.nextLine();
                                if(manager.searchMember(userInput1) != null){
                                    System.out.println(manager.searchMember(userInput1));
                                }
                                else{
                                    System.out.println("Member not found.");
                                }

                            }
                            case "4" -> manager.viewLoanList();
                            case "5" -> {
                                System.out.println("Enter book name: ");
                                String userInput1 = sc.nextLine();
                                if(!manager.searchBook(userInput1).isAvailable()){
                                    System.out.println("Book is not available.");
                                }
                                else {
                                    System.out.println("Enter member name: ");
                                    String userInput2 = sc.nextLine();
                                    if (manager.searchMember(userInput2) == null || manager.searchBook(userInput1) == null) {
                                        System.out.println("Invalid member name or book name.");
                                        continue;
                                    }
                                    manager.addLoan(
                                            manager.searchBook(userInput1),
                                            manager.searchMember(userInput2)
                                    );
                                }
                            }
                            case "6" -> {
                                System.out.println("Enter member name: ");
                                String userInput1 = sc.nextLine();
                                System.out.println("Enter book's name: ");
                                String userInput2 = sc.nextLine();
                                manager.deleteLoan(manager.searchMember(userInput1), manager.searchBook(userInput2));
                            }
                            case "7" -> isExitMember = true;
                            default -> System.out.println("Invalid option.");
                        }
                    }while(!isExitMember);
                }
                case "3" -> saver.save();
                case "4" -> saver.load();
                case "5" -> isExit = true;
            }
        }while(!isExit);
    }
}
