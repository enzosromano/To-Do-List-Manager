/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Enzo Romano
 */

package ucf.assignments;

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

import java.io.File;
import java.io.IOException;

public class MenuController {

    public Text textBox;
    public Button taskButton;
    public TableView<Task> tasksTwo;

    private Stage stage;
    private Scene scene;
    private int viewCount = 0;

    MenuMethods methods = new MenuMethods();

    //Initialize is called whenever the "mainMenu.fxml" page is opened up
    //Defines columns and sets them to our Table View
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

    //Function called by our Create List button to open our "listMenu.fxml"
    public void listMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("listMenu.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //Function called by our Task button to open our "taskMaker.fxml"
    public void editTask(ActionEvent event) {

        if (TodoList.listTitle != null) {
            Task selectedItem = tasksTwo.getSelectionModel().getSelectedItem();
            try {
                FXMLLoader passController = new FXMLLoader(getClass().getResource("taskMaker.fxml"));
                Parent root = passController.load();

                //Pass our selected task over into the next scene
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

    //Creates a blank task using makeTask() from our MenuMethods class
    public void makeTask() {

        //If it returns false our list has not been initialized
        if(!methods.makeTask()){
            textBox.setText("List not initialized yet!");
        }

    }

    //Deletes a selected task using deleteTask() from our MenuMethods class
    public void deleteTask() {

        //Get and pass our selected task that we want to delete through to our other class
        Task toDelete = tasksTwo.getSelectionModel().getSelectedItem();
        if(!methods.deleteTask(toDelete)){
            textBox.setText("List not initialized yet!");
        }

    }

    //Deletes all tasks in the list using deleteAllTasks() from our MenuMethods class
    public void deleteAllTasks() {

        if(!methods.deleteAllTasks()){
            textBox.setText("Nothing to delete! Try making a list first");
        }

    }

    //Calls our importList() function from our MenuMethods class
    public void importList(){

        if(TodoList.listTitle == null){
            textBox.setText("List not initialized yet!");
            return;
        }

        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);

        //The importList() function returns the JSONArray we need to add to our TodoList
        JSONArray toImport = methods.importList(selectedFile);

        if(toImport != null){
            addToTableView(toImport);
        }
        else{
            textBox.setText("Invalid file for import!");
        }
    }

    //Calls our addToTableView() function in our MenuMethods lass
    public void addToTableView(JSONArray itemList) {

        //if the boolean returned is false, we did not import any tasks
        if(!methods.addToTableView(itemList)){
            textBox.setText("No items to add!");
        }

    }

    //Toggle view is used to control our Complete/Incomplete/All button
    public void toggleView(ActionEvent event) throws IOException {

        //Since this button has toggle functionality, if we have reached our final state
        //restart it back to the first
        if (TodoList.listTitle != null && !TodoList.taskObserve.isEmpty()) {
            if (viewCount == 3) {
                viewCount = 0;
            }

            if (viewCount == 0) {
                //Calls initialize to show all tasks in tableview
                initialize();
                viewCount++;
            } else if (viewCount == 1) {
                tasksTwo.setItems(methods.displayComplete());
                viewCount++;
            } else if (viewCount == 2) {
                tasksTwo.setItems(methods.displayIncomplete());
                viewCount++;
            }
        } else {
            textBox.setText("Cant sort yet!");
        }
    }

    //Calls our printToJson() function in our MenuMethods class
    public void printToJson() {

        //if the boolean value returned is false, we failed at exporting our list
        if(!methods.printToJson()){
            textBox.setText("Could not export list.");
        }

    }

}
