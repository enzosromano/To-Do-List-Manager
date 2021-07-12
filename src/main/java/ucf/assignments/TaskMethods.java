/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Enzo Romano
 */

package ucf.assignments;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TaskMethods {

    private Task changeTask;

    //Identifies the task we want to edit
    public Task setEditTask(Task task) {
        changeTask = task;
        TodoList.taskObserve.remove(task);
        return changeTask;
    }

    //Sets our tasks name
    public Task changeTaskName(String editName){
        changeTask.setTaskTitle(editName);
        return changeTask;
    }

    //Sets our tasks description
    public Task changeTaskDescription(String editDescription){
        changeTask.setDescription(editDescription);
        return changeTask;
    }

    //Takes a LocalDate and converts it to a string in the correct format for user output
    public Task changeTaskDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String printDate = formatter.format(date);
        changeTask.setDueDate(printDate);
        return changeTask;
    }

    //Changes the value of boolean isDone to true or false
    public Task toggleStatus(){
        changeTask.setIsDone(!changeTask.getIsDone());
        return changeTask;
    }

}
