import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManager studentManager = new StudentManager(scanner);
        DataSaver dataTask = new DataSaver(studentManager.getStudentList(), studentManager.getNextId());
        boolean isExit = false;
        do{
            System.out.println("===STUDENT DATABASE===");
            System.out.println("""
                    1.Add Student
                    2.View Students
                    3.Search Students
                    4.Edit Students
                    5.Delete Student
                    6.Save Data
                    7.Load Data
                    8.Exit""");
            String selection = scanner.nextLine().trim().toLowerCase();
            switch (selection) {
                case "1" -> {
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine().trim();
                    System.out.print("Enter age: ");
                    int age = scanner.nextInt();
                    System.out.print("Enter GPA: ");
                    double GPA = scanner.nextDouble();
                    scanner.nextLine();
                    studentManager.addStudent(name, age, GPA);
                }
                case "2" -> studentManager.viewAllStudents();
                case "3" -> studentManager.searchStudent();
                case "4" -> studentManager.editStudent();
                case "5" -> studentManager.removeStudent();
                case "6" -> dataTask.save();
                 case "7" -> studentManager.loadStudentList(dataTask.load(), dataTask.currentCount);
                case "8" -> isExit = true;
                default -> System.out.println("The system does not support the feature.");
            }
        } while (!isExit);
    }
}
