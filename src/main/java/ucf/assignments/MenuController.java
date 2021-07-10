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

    MenuMethods methods = new MenuMethods();

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

    public void makeTask() {

        if(!methods.makeTask()){
            textBox.setText("List not initialized yet!");
        }

    }

    public void deleteTask() {

        Task toDelete = tasksTwo.getSelectionModel().getSelectedItem();
        if(!methods.deleteTask(toDelete)){
            textBox.setText("List not initialized yet!");
        }

    }

    public void deleteAllTasks() {

        if(!methods.deleteAllTasks()){
            textBox.setText("Nothing to delete! Try making a list first");
        }

    }

    public void importList(){

        if(TodoList.listTitle == null){
            textBox.setText("List not initialized yet!");
            return;
        }

        JSONArray toImport = methods.importList();

        if(toImport != null){
            addToTableView(toImport);
        }
        else{
            textBox.setText("Invalid file for import!");
        }
    }


    public void addToTableView(JSONArray itemList) {
        if(!methods.addToTableView(itemList)){
            textBox.setText("No items to add!");
        }
    }

    public void toggleView(ActionEvent event) throws IOException {


        if (TodoList.listTitle != null && !TodoList.taskObserve.isEmpty()) {
            if (viewCount == 3) {
                viewCount = 0;
            }

            if (viewCount == 0) {
                initialize();
                viewCount++;
            } else if (viewCount == 1) {
                tasksTwo.setItems(methods.displayComplete());
                //displayComplete();
                viewCount++;
            } else if (viewCount == 2) {
                tasksTwo.setItems(methods.displayIncomplete());
                //displayIncomplete();
                viewCount++;
            }
        } else {
            textBox.setText("Cant sort yet!");
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
        if(!methods.printToJson()){
            textBox.setText("Could not export list.");
        }
    }

}
