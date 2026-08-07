import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Task {
    private final String taskName;
    private final UUID taskID;
    private LocalDateTime dueDate;
    private boolean taskCompleted;
    private Priority priorityLevel;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");;

    public Task(String taskName, String dueDate, int priority) {
        this.taskName = taskName;
        this.taskID = UUID.randomUUID();
        this.dueDate = LocalDateTime.parse(dueDate, formatter);
        this.taskCompleted = false;
        checkPriority(priority);
    }
    public Task(String taskName, String taskID, int priority, LocalDateTime date) {
        this.taskName = taskName;
        this.taskID = UUID.fromString(taskID);
        this.dueDate = date;
        checkPriority(priority);
    }

    public String getTaskName() {
        return taskName;
    }
    public String getDate(){
        return dueDate.toString();
    }
    public int getPriorityLevel(){
        return priorityLevel.getLevel();
    }
    public void setState(boolean state){
        this.taskCompleted = state;
    }
    public boolean getState(){
        return this.taskCompleted;
    }
    public boolean isOverdue(){
        return dueDate.isBefore(LocalDateTime.now()) && !taskCompleted;
    }
    void checkPriority(int priority){
        switch(priority) {
            case 1 -> priorityLevel = Priority.IMMINENT;
            case 2-> priorityLevel = Priority.IMPORTANT;
            case 3 -> priorityLevel = Priority.NORMAL;
            default -> System.out.println("Invalid priority level.");
        }
    }
    // toStrings
    @Override
    public String toString(){
        return String.format("""
                Task: %s
                Deadline : %s
                Priority: %s""", getTaskName(), getDate(), priorityLevel);
    }
    public String toSave(){
        return String.format("%s,%s,%s,%d", taskName, taskID, dueDate.toString(), getPriorityLevel());
    }
}
