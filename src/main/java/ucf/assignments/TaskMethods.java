package ucf.assignments;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TaskMethods {

    private Task changeTask;

    public Task setEditTask(Task task) {
        changeTask = task;
        TodoList.taskObserve.remove(task);
        return changeTask;
    }

    public Task changeTaskName(String editName){
        changeTask.setTaskTitle(editName);
        return changeTask;
    }

    public Task changeTaskDescription(String editDescription){
        changeTask.setDescription(editDescription);
        return changeTask;
    }

    public Task changeTaskDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String printDate = formatter.format(date);
        changeTask.setDueDate(printDate);
        return changeTask;
    }

    public Task toggleStatus(){
        changeTask.setIsDone(!changeTask.getIsDone());
        return changeTask;
    }

}
