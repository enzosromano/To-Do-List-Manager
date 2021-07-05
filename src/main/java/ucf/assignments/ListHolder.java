/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Enzo Romano
 */

package ucf.assignments;

import java.util.ArrayList;

public class ListHolder {

    //ArrayList<TodoList> Lists;

    public void listsToFile(){
        //Create or find a file path

        //if Lists is not empty
            //Iterate through all our lists in our ArrayList
                //Print all list info
                //Iterate through list items
                    //Print tasks out to file
        //else
            //throw exception and print message to user
    }
    public TodoList createList(String listName){
        //if listName != ""
            //use listName to create a new list instance and set its title to listName
            //Once the list is created, return it so we can go within it and create some tasks
            return null; //placeholder list
        //else
            //throw exception
    }
    public void listsFromFile(){

        //Get the file path where list information is stored
        //Create a file reader

        //if file can be read and isn't empty
            //Iterate through the lists and add them to our ListHolder
                //Iterate through all the tasks within each TodoList and add them to the
                //corresponding list that was created above
        //else
            //throw exception and print message to user


    }

}
