import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskSaver {
    public TaskSaver() {}
    public void saveTasks(ArrayList<Task> tasks) {
        try(FileWriter writer = new FileWriter("tasks.txt")){
            for(Task task : tasks){
                writer.write(task.toSave() + "\n");
            }
        } catch(IOException error){
            System.out.println("Failed to save tasks.");
        }
    }
    public ArrayList<Task> loadTasks(){
        ArrayList<Task> newTasks = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader("tasks.txt"))){
            String line;
            while((line = reader.readLine()) != null){
                String[] taskParts = line.split(",");
                if(taskParts.length != 4){
                    continue;
                }
                String taskName = taskParts[0];
                String taskID = taskParts[1];
                LocalDateTime date = LocalDateTime.parse(taskParts[2], DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                int taskPriority = Integer.parseInt(taskParts[3]);
                Task newTask = new Task(taskName, taskID, taskPriority, date);
                newTasks.add(newTask);
            }
        } catch(FileNotFoundException error){
            System.out.println("Cannot locate task file. Creating a new file...");
        } catch(IOException error){
            System.out.println("Failed to load tasks.");
        }
        return newTasks;
    }
}
