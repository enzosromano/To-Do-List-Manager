/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Enzo Romano
 */

package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TodoList{

    public static String listTitle;
    public static ObservableList<Task> taskObserve;


    public static void initializeList(){
        taskObserve = FXCollections.observableArrayList();
    }

    public static void setTitle(String title){
        listTitle = title;
    }
    public String getTitle(){
        return listTitle;
    }

}