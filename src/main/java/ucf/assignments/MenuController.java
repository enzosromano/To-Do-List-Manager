/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Enzo Romano
 */

package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MenuController {

    public Text textBox;
    public Button taskButton;
    public TableView<Task> tasksTwo;

    private Stage stage;
    private Scene scene;
    private int viewCount = 0;

    public void initialize() {

        if (TodoList.taskObserve == null) {
            textBox.setText("Please create a list to get started");
        } else {
            textBox.setText("You are working on: " + TodoList.listTitle);
        }

        tasksTwo.getColumns().clear();

        TableColumn<Task, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(150);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("taskTitle"));

        TableColumn<Task, String> listColumn = new TableColumn<>("List Name");
        listColumn.setMinWidth(120);
        listColumn.setCellValueFactory(new PropertyValueFactory<>("listName"));

        TableColumn<Task, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setMinWidth(300);
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("taskDescription"));

        TableColumn<Task, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setMinWidth(150);
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));

        TableColumn<Task, Boolean> isDoneColumn = new TableColumn<>("Completed");
        isDoneColumn.setMinWidth(100);
        isDoneColumn.setCellValueFactory(new PropertyValueFactory<>("isDone"));

        tasksTwo.setItems(TodoList.taskObserve);
        tasksTwo.getColumns().addAll(nameColumn, listColumn, descriptionColumn, dateColumn, isDoneColumn);

    }

    //Leads to list control view
    public void listMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("listMenu.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void makeTask() {
        if (TodoList.listTitle != null) {
            TodoList.taskObserve.add(new Task("Unnamed task", TodoList.listTitle,
                    "Empty description", "No due-date assigned", false));
            initialize();
        } else {
            textBox.setText("List not initialized yet!");
        }
    }

    public void editTask(ActionEvent event) {

        if (TodoList.listTitle != null) {
            Task selectedItem = tasksTwo.getSelectionModel().getSelectedItem();
            try {
                FXMLLoader passController = new FXMLLoader(getClass().getResource("taskMaker.fxml"));
                Parent root = passController.load();

                TaskMakerController taskMaker = passController.getController();
                taskMaker.setEditTask(selectedItem);

                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            textBox.setText("List not initialized yet!");
        }
    }

    public void deleteTask() {

        if (TodoList.listTitle != null) {
            TodoList.taskObserve.remove(tasksTwo.getSelectionModel().getSelectedItem());
            initialize();
        } else {
            textBox.setText("List not initialized yet!");
        }
    }

    public void deleteAllTasks() {

        try {
            TodoList.taskObserve.clear();
            initialize();
        } catch (NullPointerException n) {
            textBox.setText("Nothing to delete! Try making a list first");
        }
    }

    public void importList(){

        if (TodoList.listTitle != null) {
            FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showOpenDialog(null);

            if (selectedFile != null) {
                parseJson(selectedFile.getPath());
            }
        } else {
            textBox.setText("List not initialized yet!");
        }
    }

    public void parseJson(String file) {

        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(file));

            JSONObject jsonObject = (JSONObject) obj;
            JSONArray itemList = (JSONArray) jsonObject.get("tasks");

            addToTableView(itemList);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addToTableView(JSONArray itemList) {
        Boolean isDone;
        for (JSONObject object : (Iterable<JSONObject>) itemList) {

            isDone = object.get("isDone").equals("true");
            TodoList.taskObserve.add(new Task(object.get("taskTitle").toString(), TodoList.listTitle,
                    object.get("taskDescription").toString(), object.get("dueDate").toString(), isDone));
        }
        initialize();
    }

    public void toggleView(ActionEvent event) throws IOException {

        if (TodoList.listTitle != null && !TodoList.taskObserve.isEmpty()) {
            if (viewCount == 3) {
                viewCount = 0;
            }

            System.out.println("Starting value: " + viewCount);

            if (viewCount == 0) {
                displayAll();
                viewCount++;
            } else if (viewCount == 1) {
                displayComplete();
                viewCount++;
            } else if (viewCount == 2) {
                displayIncomplete();
                viewCount++;
            }
        } else {
            textBox.setText("Cant sort yet!");
        }
    }

    public void displayComplete() {

        ObservableList<Task> completeItems = FXCollections.observableArrayList();

        if (TodoList.listTitle != null) {
            for (int i = 0; i < TodoList.taskObserve.size(); i++) {
                if (TodoList.taskObserve.get(i).getIsDone()) {
                    completeItems.add(TodoList.taskObserve.get(i));
                }
            }
            tasksTwo.setItems(completeItems);
        } else {
            textBox.setText("Cannot sort yet!");
        }
    }

    public void displayIncomplete() {

        ObservableList<Task> incompleteItems = FXCollections.observableArrayList();

        if (TodoList.listTitle != null) {
            for (int i = 0; i < TodoList.taskObserve.size(); i++) {
                if (!TodoList.taskObserve.get(i).getIsDone()) {
                    incompleteItems.add(TodoList.taskObserve.get(i));
                }
            }
            tasksTwo.setItems(incompleteItems);
        } else {
            textBox.setText("Cannot sort yet!");
        }
    }

    public void displayAll() {
        if (TodoList.listTitle != null && !TodoList.taskObserve.isEmpty()) {
            initialize();
        } else {
            textBox.setText("Cannot sort yet!");
        }
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
