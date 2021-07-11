/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Enzo Romano
 */


package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MenuMethods {

    //Create a new task with placeholder values
    public boolean makeTask() {

        //Check if our list is actually initialized before we try and add a task to it
        if (TodoList.listTitle != null) {
            TodoList.taskObserve.add(new Task("Unnamed task", TodoList.listTitle,
                    "Empty description", "No due-date assigned", false));
            return true;
        }
        //return false if we could not add a task
        return false;

    }

    //Delete a task that gets passed in from our TodoList
    public boolean deleteTask(Task task) {

        if (TodoList.listTitle != null) {
            TodoList.taskObserve.remove(task);
            return true;
        }
        //return false if our list has not been initialized or we did not find the task
        return false;

    }

    //Delete all the tasks in our current TodoList item
    public boolean deleteAllTasks() {

        try {
            TodoList.taskObserve.clear();
        } catch (NullPointerException n) {
            return false;
        }
        return true;
    }

    //Import a list from a JSON file if it meets our criteria
    public JSONArray importList(File selectedFile){

        if (TodoList.listTitle != null) {
            if (selectedFile != null) {
                //Call to parseJson function using our grabbed file
                return parseJson(selectedFile.getPath());
            }
        }

        return null;

    }

    //Parses our json file for tasks
    public JSONArray parseJson(String file) {

        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(file));

            JSONObject jsonObject = (JSONObject) obj;
            //Tasks must be stored within a JSON object of the value "tasks"
            JSONArray itemList = (JSONArray) jsonObject.get("tasks");
            return itemList;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    //Adds a JSON array of items to our Table View
    public boolean addToTableView(JSONArray itemList) {

        //Nothing to add if we have no items in our array
        if(itemList.size() == 0){
            return false;
        }

        boolean isDone;
        for (JSONObject object : (Iterable<JSONObject>) itemList) {

            isDone = object.get("isDone").equals("true");
            TodoList.taskObserve.add(new Task(object.get("taskTitle").toString(), TodoList.listTitle,
                    object.get("taskDescription").toString(), object.get("dueDate").toString(), isDone));
        }
        return true;
    }

    //Returns an array of only our completed items from our Todolist
    public ObservableList<Task> displayComplete() {

        ObservableList<Task> completeItems = FXCollections.observableArrayList();

        if (TodoList.listTitle != null) {
            //Iterate through all elements and check if isDone is true
            for (int i = 0; i < TodoList.taskObserve.size(); i++) {
                if (TodoList.taskObserve.get(i).getIsDone()) {
                    completeItems.add(TodoList.taskObserve.get(i));
                }
            }
        }

        return completeItems;
    }

    //Returns an array of only our Inomplete items from our Todolist
    public ObservableList<Task> displayIncomplete() {

        ObservableList<Task> incompleteItems = FXCollections.observableArrayList();

        if (TodoList.listTitle != null) {
            //Iterate through all elements and check if isDone is false
            for (int i = 0; i < TodoList.taskObserve.size(); i++) {
                if (!TodoList.taskObserve.get(i).getIsDone()) {
                    incompleteItems.add(TodoList.taskObserve.get(i));
                }
            }
        }
        return incompleteItems;

    }

    //Called on when we need to export a file, returns true if the file is created with no issues
    public boolean printToJson() {

        if(TodoList.taskObserve == null){
            return false;
        }

        //Get our current directory path and put it in string format to use as a file path
        Path currentRelativePath = Paths.get("");
        String path = currentRelativePath.toAbsolutePath().toString();

        try {
            File replacedOutput = new File(path + "\\" + "jsonOfList");
            if (replacedOutput.createNewFile()) {
                System.out.println("File created: " + replacedOutput.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return false;
        }

        //Create an array to store our tasks in and then an object to format it for our JSON output
        JSONArray allTasks = new JSONArray();
        JSONObject toJson = new JSONObject();
        for (int i = 0; i < TodoList.taskObserve.size(); i++) {
            //Store each task in an abject so we can add it to our array of objects
            JSONObject currentTask = new JSONObject();
            currentTask.put("taskTitle", TodoList.taskObserve.get(i).getTaskTitle());
            currentTask.put("listName", TodoList.taskObserve.get(i).getListName());
            currentTask.put("taskDescription", TodoList.taskObserve.get(i).getTaskDescription());
            currentTask.put("dueDate", TodoList.taskObserve.get(i).getDueDate());
            currentTask.put("isDone", TodoList.taskObserve.get(i).getIsDone().toString());
            allTasks.add(currentTask);
        }
        toJson.put("tasks", allTasks);

        //Put our output in string form for writing to the file
        String jsonString = toJson.toString();
        try {
            FileWriter myWriter = new FileWriter(path + "\\" + "jsonOfList");
            myWriter.write(jsonString);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return false;
        }

        return true;

    }

}
