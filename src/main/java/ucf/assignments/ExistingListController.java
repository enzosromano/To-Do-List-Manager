package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class ExistingListController {

    public TextField listName;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void mainMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("mainMenu.fxml"));
        Parent root =  loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void makeList(ActionEvent event) {
        //This command will be used when "Make the List" button is clicked
        //Grab the users input from the text box and initialize a new list using
        //the text box input to assign its name

        System.out.println("This would make the list " + listName);
    }

    public void printList(ActionEvent event) {

        //This command will be used when "Print the List" button is clicked
        //Grab the users input from the text box
        //Iterate through all lists in our system
        //Check to see if the current list in iteration matches the text box name input
        //If it does, iterate through our found list and print all tasks

        String stuff = listName.getText();
        System.out.println(stuff);
    }

    public void taskMaker(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("taskMaker.fxml"));
        Parent root =  loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
