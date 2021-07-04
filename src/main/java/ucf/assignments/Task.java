/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Enzo Romano
 */

package ucf.assignments;

import java.util.Date;

class Task{

    String taskTitle;
    TodoList listName;
    String taskDescription;
    Date dueDate;
    boolean isDone;

    public String getName(){
        //Return the variable taskTitle
        //for pseudocode purposes im returning a literal
        return "placeholder";
    }
    public void setName(String name){
        //Set the variable taskTitle to the string that we get passed in
    }
    public TodoList getList(){
        //Get the value of listName and return it
        //for pseudocode purposes im returning a literal
        return null;
    }
    public void setList(){
        //Set the value of listName and return nothing
    }
    public String getDescription(){
        //Get the value of taskDescription and return it
        //for pseudocode purposes im returning a literal
        return "placeholder";
    }
    public void setDescription(String descriptionEdit){
        //Set the variable taskDescription to the string that we get passed in
    }
    public Date getDate(){
        //Get the value of dueDate and return it
        //Date is just a placeholder for pseudocode purposes currently
        return new Date();
    }
    public void setDate(Date date){
        //Set the value of dueDate to our read in date
    }
    public boolean isDone(){
        //Return the isDone boolean value (true or false)
        //for psuedocode purposes im returning a literal
        return true;
    }
    public void setDone(){
        //Set the boolean value isDone to true
    }

}