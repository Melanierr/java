import java.util.ArrayList;
import java.util.Scanner;

public class StudentManager {
    private int nextId = 1;
    private ArrayList<Student> studentList = new ArrayList<>();
    private final Scanner scanner;
    StudentManager(Scanner scanner) {
        this.scanner = scanner;
    }


    public void addStudent(String name, int age, double GPA){
        String id = String.format("%03d", nextId);
        Student student = new Student(name, age, id, GPA);
        studentList.add(student);
        nextId++;
    }
    public void removeStudent(){
        System.out.print("Please enter student's ID");
        String ID = scanner.nextLine();
        studentList.removeIf(studentRemoval -> ID.equals(studentRemoval.getStudentId()));
    }
    public Student searchStudent(){
        System.out.println("Search by Name or ID?");
        String selection  = scanner.nextLine();
        switch(selection.trim().toLowerCase()){
            case "name"-> {
                System.out.print("Enter student's name: ");
                String name = scanner.nextLine().trim();
                for(Student student : studentList){
                    if(student.getName().equalsIgnoreCase(name)){
                        return student;
                    }
                }
            }
            case "id" -> {
                System.out.print("Enter student's ID: ");
                String studentId = scanner.nextLine();
                for(Student student : studentList){
                    if(student.getStudentId().equals(studentId)){
                        return student;
                    }
                }
            }
        }
        return null;
    }
    public void editStudent(){
        Student currentStudent = searchStudent();
        if(currentStudent == null){
            return;
        }
        System.out.println("What would you like to edit?\n1.Name\n2.Age\n3.GPA");
        String type = scanner.nextLine().trim();
        switch(type.trim().toLowerCase()){
            case "name", "1" -> {
                System.out.print("Enter a new name: ");
                currentStudent.setName(scanner.nextLine());
            }
            case "age", "2" -> {
                System.out.print("Enter a new age: ");
                currentStudent.setAge(Integer.parseInt(scanner.nextLine()));
            }
            case "gpa", "3" -> {
                System.out.print("Enter a new GPA: ");
                currentStudent.setGPA(Double.parseDouble(scanner.nextLine()));
            }
            default -> System.out.println("System does not support that type.");
        }
    }
    public void viewAllStudents() {
        if(studentList.isEmpty()){
            System.out.println("There are no students in the system.");
            return;
        }
        for(Student student : studentList) {
            System.out.println(student);
        }
    }
    public ArrayList<Student> getStudentList() {
        return studentList;
    }
    public void loadStudentList(ArrayList<Student> studentList, int ID) {
        this.studentList = studentList;
        this.nextId = ID;
    }
    public int getNextId(){
        return nextId;
    }
}
