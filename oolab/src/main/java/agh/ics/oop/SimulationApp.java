package agh.ics.oop;

import agh.ics.oop.model.util.FXMLConfigurator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;


public class SimulationApp extends Application  {
    public void start(Stage primaryStage) {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getClassLoader().getResource("startingView.fxml"));

        try{
            BorderPane viewRoot = loader.load();
            FXMLConfigurator.configureStage(primaryStage, viewRoot);
            primaryStage.show();
        }catch(IOException err){};

    }



}
