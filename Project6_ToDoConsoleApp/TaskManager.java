import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;

public class TaskManager {
    private ArrayList<Task> taskList;
    private ArrayList<Task> taskOverdue;
    private ArrayList<Task> taskFinished;
    private ArrayList<Task> taskPending;
    private final static int maxTaskAllowed = 10;

    public TaskManager(){
        taskList = new ArrayList<>();
    }
    // getters & setters
    public ArrayList<Task> getTaskList(){
        return taskList;
    }
    public void setNewList(ArrayList<Task> newList){
        this.taskList = newList;
    }

    // task handler
    public void createTask(String taskName, int priority, String dueDate){
        try{
            if (taskList.size() >= maxTaskAllowed) {
                System.out.println("Max task created.");
                return;
            }
            Task newTask = new Task(taskName, dueDate, priority);
            taskList.add(newTask);
        }catch(DateTimeParseException error){
            System.out.println("Invalid date format.");
        }
    }
    public void deleteTask(String taskName){
        boolean success = taskList.removeIf(task -> task.getTaskName().equalsIgnoreCase(taskName));
        if(!success){
            System.out.println("Task not found.");
        }
    }
    public void updateTaskState(){
        taskOverdue = new ArrayList<>();
        taskFinished = new ArrayList<>();
        taskPending = new ArrayList<>();
        for(Task task : taskList){
            if(task.isOverdue()){
                taskOverdue.add(task);
            }
            if(task.getState()){
               taskFinished.add(task);
            } else{
                taskPending.add(task);
            }

        }

    }
    // basic function
    public void showAllTasks(){
        printTaskList(taskList, "You have no tasks.");
    }
    public void showFinishedTask(){
        printTaskList(taskFinished, "You haven't finished any tasks.");
    }
    public void showOverdueTask(){
        printTaskList(taskOverdue, "You have no overdue tasks.");
    }
    public void showPendingTask(){
        printTaskList(taskPending, "No task available.");
    }
    public void printTaskList(ArrayList<Task> type, String failmsg){
        if(type.isEmpty()){
            System.out.println(failmsg);
            return;
        }
        for(Task task : type){
            System.out.println(task);
        }
    }
    public void setTaskFinish(String taskName){
        for(Task task : taskList){
            if(task.getTaskName().equalsIgnoreCase(taskName)){
                task.setState(true);
            }
        }
        updateTaskState();
    }
    // sorter
    public void sortByPriority(){
        ArrayList<Task> tempTaskHolder = new ArrayList<>(taskList);
        tempTaskHolder.sort(Comparator.comparingInt(Task::getPriorityLevel));
        for(Task task : tempTaskHolder){
            System.out.println(task);
        }
    }
    public void sortByStatus(){
        ArrayList<Task> tempTaskHolder = new ArrayList<>(taskList);
        tempTaskHolder.sort(Comparator.comparing(Task::getState).reversed());
        for(Task task : tempTaskHolder){
            System.out.println(task);
        }
    }

}
