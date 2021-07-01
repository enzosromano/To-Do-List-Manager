/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Enzo Romano
 */

package ucf.assignments;

import java.util.ArrayList;

class TodoList{

    String listTitle;

    private ArrayList<Task> tasks;

    //Title stuff
    public void setTitle(String title){
        //This function will take in a string and set that string as the title (listTitle) of our current list
    }
    public void getTitle(String titleEdit){
        //This function will return the value of listTitle
    }

    //Task stuff
    public void addTask(){
        //Get user input for the task that we want to add
        //Specifically the title, description and due date
        //Set the tasks boolean "isDone" to false
    }
    public void removeTask(){
        //Get the information for the task we want to remove

        //Find the task in the list

        //Remove the Task
        //Luckily removing from an arraylist in java is easy, use the built in .remove function
    }
    public void displayTasks(){

        //Iterate through the list
            //Print all the items of the list

    }
    public void displayIncomplete(){

        //Iterate through the list
            //Check if the current item is complete
                //If the item is not complete, print it

    }
    public void displayComplete(){

        //Iterate through the list
            //Check if the current item is complete
                //If the item is complete, print it

    }
    public void printToFile(){

        //Create or find a path
        //Create a file within that path

        //Iterate through the list
            //Either print all items for each iteration or add them to a string builder and print at the end

    }
    public void readFromFile(){

        //Get the file path from the user
        //Make a fileReader

        //Read the information from the file and assign certain lines to certain variables
        //When the file is read and the variables are assigned, assign the variables to the tasks traits

    }

}