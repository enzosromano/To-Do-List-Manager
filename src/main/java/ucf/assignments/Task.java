/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Enzo Romano
 */

package ucf.assignments;

import javafx.scene.control.CheckBox;

import java.time.LocalDate;
import java.util.Date;

public class Task{

    private String taskTitle;
    private String listName;
    private String taskDescription;
    private String dueDate;
    private Boolean isDone;

    public Task(){
        this.taskTitle = "";
        this.listName = "";
        this.taskDescription = "";
        this.dueDate = "";
        this.isDone = false;
    }
    public Task(String taskTitle, String listName, String taskDescription, String dueDate, Boolean isDone){
        this.taskTitle = taskTitle;
        this.listName = listName;
        this.taskDescription = taskDescription;
        this.dueDate = dueDate;
        this.isDone = isDone;
    }

    public String getTaskTitle() {
        return taskTitle;
    }
    public void setTaskTitle(String name){
        this.taskTitle = name;
    }
    public String getListName(){
        return listName;
    }
    public void setListName(String list){
        this.listName = list;
    }
    public String getTaskDescription(){
        return taskDescription;
    }
    public void setDescription(String descriptionEdit){
        this.taskDescription = descriptionEdit;
    }
    public String getDueDate(){
        return dueDate;
    }
    public void setDueDate(String date){
        this.dueDate = date;
    }
    public Boolean getIsDone(){
        return isDone;
    }
    public void setIsDone(Boolean done){
        this.isDone = done;
    }

}