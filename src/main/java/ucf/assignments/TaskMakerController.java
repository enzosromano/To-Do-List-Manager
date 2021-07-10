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


    public void mainMenu(ActionEvent event) throws IOException {

        TodoList.taskObserve.add(changeTask);

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("mainMenu.fxml"));
        Parent root =  loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setEditTask(Task task) {
        changeTask = task;
        TodoList.taskObserve.remove(task);
        setupMenu();
    }

    public void changeTaskName(){
        String name = assignName.getText();
        changeTask.setTaskTitle(name);
        assignName.clear();
        assignName.setPromptText("Confirmed");
        setupMenu();
    }
    public void changeTaskDescription(){
        String description = assignDescription.getText();

        if(description.length() < 1 || description.length() > 256){
            assignDescription.clear();
            assignDescription.setPromptText("Must be between 1-256 Characters");
        }
        else {
            changeTask.setDescription(description);
            assignDescription.clear();
            assignDescription.setPromptText("Confirmed");
        }
        setupMenu();
    }

    public void changeTaskDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = datePicker.getValue();
        String printDate = formatter.format(date);
        changeTask.setDueDate(printDate);
        setupMenu();
    }

    public void toggleStatus(){
        changeTask.setIsDone(!changeTask.getIsDone());
        setupMenu();
    }


}
