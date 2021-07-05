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
        //if name != ""
            return "placeholder";
        //else
            //throw an exception
    }
    public void setName(String name){
        //if name != ""
            //set our name variable equal to the passed in "name"
        //else
            //throw an exception
    }
    public TodoList getList(){
        //if listName != ""
            return null; //null is a placeholder
        //else
            //throw an exception
    }
    public void setList(TodoList list){
        //if list != null
            //set TodoList = list
        //else
            //throw an exception
    }
    public String getDescription(){
        //if description != ""
            return "placeholder";
        //else
            //throw an exception
    }
    public void setDescription(String descriptionEdit){
        //if name != ""
            //set our description variable equal to the passed in "descriptionEdit"
        //else
            //throw an exception
    }
    public Date getDate(){
        //if description != null
            return new Date();
        //else
            //throw an exception
    }
    public void setDate(Date date){
        //if date != null
            //set our date variable equal to the passed in "date"
        //else
            //throw an exception
    }
    public boolean isDone(){
        //if isDone == false
            //return false;
        //else
            //return true;
        return true;
    }
    public void setDone(){
        //Set the boolean value isDone to true
    }

}