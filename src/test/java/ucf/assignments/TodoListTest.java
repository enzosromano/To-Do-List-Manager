package ucf.assignments;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TodoListTest {

    //Have a public list here that will be populated and can be accessed by all tests below

    @Test
    void testGetAndSetTitle(){

        //Set a title name for a list
        //Use getList in order to test if the title name was set correctly
        //and if getList pulls the name correctly

    }
    @Test
    void testAddAndRemoveTask(){

        //Add a task by assigning a tasks 4 variables randomly and check if there is
        //a task in the list that matches those attributes

        //Using the same test that we just added, remove it and check the list for a task
        //with those same 4 values and make sure nothing comes up matching

    }

    @Test
    void testDisplayTasks(){

        //Unit for testing all tasks and displaying them
        //Iterate through the entire list regardless of any attributes
            //Hardcode a count number, have a variable increase every iteration
            //and a list of task names that need to all be reached
            //If nothing fails, set a boolean value to true

        //Unit for testing only incomplete tasks and displaying them
        //Iterate through the entire list and only do the below actions if the task is incomplete
            //Hardcode a count number, have a variable increase every iteration
            //and a list of task names that need to all be reached
            //If nothing fails, set a boolean value to true

        //Unit for testing only complete tasks and displaying them
        //Iterate through the entire list and only do the below actions if the task is complete
            //Hardcode a count number, have a variable increase every iteration
            //and a list of task names that need to all be reached
            //If nothing fails, set a boolean value to true

    }
    @Test
    void testPrintToFile(){

        //Create all necessary modules to print to a file
        //Create the file itself that will be printed to if necessary
        //Iterate through our list we have defined and print all of them to the file

        //Iterate through and read in that same file and store the tasks in a temp list
        //Check the temp list for a certain number of elements and certain elements you expect

    }
    @Test
    void testReadFromFile(){

        //Create a new empty list
        //Iterate through the file stored from above and add each item to the list
        //Check to see if expected number of elements and certain elements have been added

    }

}
