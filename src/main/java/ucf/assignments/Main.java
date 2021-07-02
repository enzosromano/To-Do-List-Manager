/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Enzo Romano
 */

package ucf.assignments;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("mainMenu.fxml"));
        Parent root =  loader.load();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 550, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}