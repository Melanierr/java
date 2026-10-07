import java.io.*;
import java.util.ArrayList;

public class DataSaver {
    ArrayList<Student> studentList;
    int currentCount;
    DataSaver(ArrayList<Student> studentList, int currentCountId) {
        this.studentList = studentList;
        this.currentCount = currentCountId;
    }
    public void save(){
        try(FileWriter fileWriter = new FileWriter("C:\\Users\\Musc\\Desktop\\student_list.txt")){
            for(Student studentSave : studentList){
                fileWriter.write(studentSave.toSaveString()+"\n");
            }
            fileWriter.write("Count:" + currentCount);
        }
        catch (IOException error){
            System.out.println("Something interrupted file writing.");
        }
    }
    public ArrayList<Student> load(){
        ArrayList<Student> loadedStudentList = new  ArrayList<>();
        String line;
        try(BufferedReader fileReader = new BufferedReader(new FileReader("C:\\Users\\Musc\\Desktop\\student_list.txt"))){
            while((line = fileReader.readLine()) != null){
                if (line.startsWith("Count:")) {
                    currentCount = Integer.parseInt(line.split(":")[1].trim());
                }
                else {
                    String[] parts = line.split(",");
                    Student student = new Student(parts[0], Integer.parseInt(parts[1]), parts[2], Double.parseDouble(parts[3]));
                    loadedStudentList.add(student);
                }
            }
        }
        catch(FileNotFoundException error){
            System.out.println("Cannot locate student list.");
        }
        catch(IOException error){
            System.out.println("Something interrupted file reading.");
        }
        return loadedStudentList;
    }
}
