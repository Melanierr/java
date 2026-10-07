import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager manager = new TaskManager();
        TaskSaver saver = new TaskSaver();
        boolean isExit = false;
        do{
            manager.updateTaskState();
            System.out.println("To Do App ✅");
            System.out.print("""
                    ============================
                    1.View task
                    2.Create new task
                    3.Delete task
                    4.Save task list
                    5.Load task list
                    6.Exit
                    Choose an option:\s""");
            String selection = scanner.nextLine();
            switch (selection) {
                case "1" -> {
                    if(manager.getTaskList().isEmpty()){
                        System.out.println("No task exists.");
                        continue;
                    }
                    boolean isInOne = true;
                    do {
                        manager.updateTaskState();
                        System.out.print("""
                                ============================
                                1.View all task
                                2.View overdue task
                                3.View finished task
                                4.View unfinished task
                                5.Submit a task
                                6.Sort task list
                                7.Exit
                                Choose an option:\s""");
                        String selection2 = scanner.nextLine();
                        switch (selection2) {
                            case "1" -> manager.showAllTasks();
                            case "2" -> manager.showOverdueTask();
                            case "3" -> manager.showFinishedTask();
                            case "4" -> manager.showPendingTask();
                            case "5" -> {
                                manager.showAllTasks();
                                System.out.print("Enter the task name to submit: ");
                                String taskName = scanner.nextLine().trim();
                                manager.setTaskFinish(taskName);
                            }
                            case "6" -> {
                                System.out.println("Sort by? (STATUS/PRIORITY)");
                                String selection3 = scanner.nextLine();
                                if(selection3.equalsIgnoreCase("STATUS")) {
                                    manager.sortByStatus();
                                }else if(selection3.equalsIgnoreCase("PRIORITY")) {
                                    manager.sortByPriority();
                                }
                            }
                            case "7" -> isInOne = false;
                            default -> System.out.println("Invalid option.");
                        }
                    }while(isInOne);
                }
                case "2" -> {
                    System.out.print("Enter task name: ");
                    String taskName = scanner.nextLine().trim();
                    System.out.print("Importance (HIGH/MEDIUM/LOW): ");
                    int taskLevel = 0;
                    switch(scanner.nextLine().toLowerCase()) {
                        case "high" -> taskLevel = 1;
                        case "medium" -> taskLevel = 2;
                        case "low" -> taskLevel = 3;
                    }
                    System.out.print("Enter deadline (DD/MM/YYYY HH:MM): ");
                    String taskDeadline = scanner.nextLine();
                    manager.createTask(taskName, taskLevel, taskDeadline);
                }
                case "3" -> {
                    if(manager.getTaskList().isEmpty()){
                        System.out.println("No tasks to delete.");
                        continue;
                    }
                    manager.showAllTasks();
                    System.out.print("Enter task's name: ");
                    String taskName = scanner.nextLine();
                    manager.deleteTask(taskName);
                }
                case "4" -> saver.saveTasks(manager.getTaskList());
                case "5" -> {
                    manager.setNewList(saver.loadTasks());
                    manager.updateTaskState();
                }
                case "6" -> isExit = true;
                default -> System.out.println("Invalid option.");
            }
        }while(!isExit);
        scanner.close();
    }
}
