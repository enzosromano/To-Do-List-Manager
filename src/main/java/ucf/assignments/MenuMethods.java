package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

    private int viewCount = 0;

    public boolean makeTask() {
        if (TodoList.listTitle != null) {
            TodoList.taskObserve.add(new Task("Unnamed task", TodoList.listTitle,
                    "Empty description", "No due-date assigned", false));
            return true;
        }
        return false;
    }

    public boolean deleteTask(Task task) {

        if (TodoList.listTitle != null) {
            TodoList.taskObserve.remove(task);
            return true;
        }
        return false;
    }

    public boolean deleteAllTasks() {

        try {
            TodoList.taskObserve.clear();
        } catch (NullPointerException n) {
            return false;
        }
        return true;
    }

    public JSONArray importList(){

        if (TodoList.listTitle != null) {
            FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showOpenDialog(null);

            if (selectedFile != null) {
                return parseJson(selectedFile.getPath());
            }
        }

        return null;

    }

    public JSONArray parseJson(String file) {

        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(file));

            JSONObject jsonObject = (JSONObject) obj;
            JSONArray itemList = (JSONArray) jsonObject.get("tasks");
            return itemList;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    public boolean addToTableView(JSONArray itemList) {

        if(itemList.size() == 0){
            return false;
        }

        Boolean isDone;
        for (JSONObject object : (Iterable<JSONObject>) itemList) {

            isDone = object.get("isDone").equals("true");
            TodoList.taskObserve.add(new Task(object.get("taskTitle").toString(), TodoList.listTitle,
                    object.get("taskDescription").toString(), object.get("dueDate").toString(), isDone));
        }
        return true;
    }

    public ObservableList<Task> displayComplete() {

        ObservableList<Task> completeItems = FXCollections.observableArrayList();

        if (TodoList.listTitle != null) {
            for (int i = 0; i < TodoList.taskObserve.size(); i++) {
                if (TodoList.taskObserve.get(i).getIsDone()) {
                    completeItems.add(TodoList.taskObserve.get(i));
                }
            }
        }

        return completeItems;
    }

    public ObservableList<Task> displayIncomplete() {

        ObservableList<Task> incompleteItems = FXCollections.observableArrayList();

        if (TodoList.listTitle != null) {
            for (int i = 0; i < TodoList.taskObserve.size(); i++) {
                if (!TodoList.taskObserve.get(i).getIsDone()) {
                    incompleteItems.add(TodoList.taskObserve.get(i));
                }
            }
        }
        return incompleteItems;

    }

    public void printToJson() {

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
        }

        JSONArray allTasks = new JSONArray();
        JSONObject toJson = new JSONObject();
        for (int i = 0; i < TodoList.taskObserve.size(); i++) {
            JSONObject currentTask = new JSONObject();
            currentTask.put("taskTitle", TodoList.taskObserve.get(i).getTaskTitle());
            currentTask.put("listName", TodoList.taskObserve.get(i).getListName());
            currentTask.put("taskDescription", TodoList.taskObserve.get(i).getTaskDescription());
            currentTask.put("dueDate", TodoList.taskObserve.get(i).getDueDate());
            currentTask.put("isDone", TodoList.taskObserve.get(i).getIsDone().toString());
            allTasks.add(currentTask);
        }
        toJson.put("tasks", allTasks);

        String jsonString = toJson.toString();
        try {
            FileWriter myWriter = new FileWriter(path + "\\" + "jsonOfList");
            myWriter.write(jsonString);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

}
