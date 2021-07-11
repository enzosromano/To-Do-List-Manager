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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TaskMakerController {

    public TextField assignName;
    public TextField assignDescription;
    public DatePicker datePicker;
    public Text completed;
    public Text date;
    public Text description;
    public Text title;
    private Task changeTask;

    TaskMethods methods = new TaskMethods();

    public void mainMenu(ActionEvent event) throws IOException {

        TodoList.taskObserve.add(changeTask);

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("mainMenu.fxml"));
        Parent root =  loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //Lays out the interface that displays task information to users
    public void setupMenu(){

        if(changeTask.getTaskTitle() != null){
            title.setText(changeTask.getTaskTitle());
        }
        else{
            title.setText("Unnamed Task");
        }

        if(changeTask.getTaskDescription() != null){
            description.setText(changeTask.getTaskDescription());
        }
        else{
            description.setText("No description yet, try writing one!");
        }

        if(changeTask.getDueDate() != null){
            date.setText(changeTask.getDueDate());
        }
        else{
            description.setText("No date assigned yet, try adding one!");
        }

        if(!changeTask.getIsDone()){
            completed.setText("Incomplete");
        }
        else{
            completed.setText("Completed");
        }
    }

    //Identifies the task we want to edit and calls our menu setup function
    public void setEditTask(Task task) {
        changeTask = methods.setEditTask(task);
        setupMenu();
    }

    //Passes our text field input to changeTaskName() in TaskMethods class
    public void changeTaskName(){
        String name = assignName.getText();
        if(name.length() ==0){
            assignName.setPromptText("Name must be more than one character");
            return;
        }
        changeTask = methods.changeTaskName(name);

        assignName.clear();
        assignName.setPromptText("Confirmed");
        setupMenu();
    }

    //Gets our text field input and passes it to changeTaskDescription in TaskMethods class
    public void changeTaskDescription(){
        String description = assignDescription.getText();

        //Make sure use input follows our constraints of 1-256 characters
        if(description.length() < 1 || description.length() > 256){
            assignDescription.clear();
            assignDescription.setPromptText("Must be between 1-256 Characters");
        }
        else {
            changeTask = methods.changeTaskDescription(description);
            assignDescription.clear();
            assignDescription.setPromptText("Confirmed");
        }
        setupMenu();
    }

    //Gets user input from our DatePicker and passes it to our TaskMethods class
    public void changeTaskDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = datePicker.getValue();
        changeTask = methods.changeTaskDate(date);
        setupMenu();
    }

    //Changes the status of our task
    public void toggleStatus(){
        changeTask = methods.toggleStatus();
        setupMenu();
    }


}
