/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Enzo Romano
 */

package ucf.assignments;

import java.util.ArrayList;

class TodoList{

    //String listTitle;

    //List<Task> tasks;

    //Title stuff
    public void setTitle(String title){
        //if title != ""
            //set our title variable equal to the passed in "title"
        //else
            //throw an exception
    }
    public String getTitle(){
        //if title != ""
            return "placeholder";
        //else
            //throw an exception
    }

    //Task stuff
    public void addTask(){
        //Get user input for the task that we want to add
        //Specifically the title, description and due date
        //Set the tasks boolean "isDone" to false
    }
    public void removeTask(){
        //Get the information for the task we want to remove

        //Search for the task in the list
            //if found
                //remove the task
                //Luckily removing from an arraylist in java is easy, use the built in .remove function
            //else
                //throw an exception
    }
    public void displayTasks(){
        //If todoList != empty
            //Iterate through the tasks within the list
                //for each task, print only the task name variable using getName()
        //else
            //throw exception or print message to user and do nothing (not sure yet)
    }
    public void displayIncomplete(){

        //boolean hasIncomplete

        //if todoList != empty
            //Iterate through the tasks within the list
                //Check if the current item is not complete
                    //hasIncomplete = true
                    //If the item is not complete, print it

        //if hasIncomplete == false
            //exception or print message and do nothing

    }
    public void displayComplete(){

        //boolean hasComplete

        //if todoList != empty
            //Iterate through the tasks within the list
                //Check if the current item is complete
                    //hasComplete = true
        //If the item is complete, print it

        //if hasComplete == false
            //exception or print message and do nothing

    }
    public void printToFile(){
        //if todoList != empty
            //Create or find a path
            //Create a file within that path

            //Iterate through the tasks in the list
                //Either print all items for each iteration or add them to a string builder and print at the end
        //else
            //throw exception and print message to user
    }
    public void readFromFile(){

        //Get the file path from the user
        //Make a fileReader

        //Read the information from the file and assign certain lines to certain variables
        //When the file is read and the variables are assigned, assign the variables to the tasks traits

    }

}