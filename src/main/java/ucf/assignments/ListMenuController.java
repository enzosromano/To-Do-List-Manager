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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class ListMenuController {

    public TextField newListName;
    private Stage stage;
    private Scene scene;

    //Function called by the home button to load the main menu view
    public void mainMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("mainMenu.fxml"));
        Parent root =  loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    //Create and initialize a list from user input, called by the "Make new List" button
    public void createListFromInput(ActionEvent event) throws IOException {

        String name = newListName.getText();
        if(name.length() == 0){
            //If the input for the name entered isn't long enough, don't initialize the list
            newListName.setPromptText("Must be longer than one character");
            return;
        }
        //If the input meets our constraints, initialize the list and set its name to the users input
        TodoList.initializeList();
        TodoList.setTitle(name);

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("mainMenu.fxml"));
        Parent root =  loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}
