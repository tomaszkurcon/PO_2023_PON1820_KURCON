package agh.ics.oop;

import agh.ics.oop.model.GrassField;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;
import agh.ics.oop.presenter.SimulationPresenter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;


public class SimulationApp extends Application  {
    public void start(Stage primaryStage) {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getClassLoader().getResource("simulation.fxml"));

        try{
            BorderPane viewRoot = loader.load();
            configureStage(primaryStage, viewRoot);
            primaryStage.show();
        }catch(IOException err){};

        SimulationPresenter presenter = loader.getController();
        WorldMap grassMap = new GrassField(10, "Grassfield");
        presenter.setMap(grassMap);
        grassMap.addSubscriber(presenter);
    }
    private void configureStage(Stage primaryStage, BorderPane viewRoot) {
        var scene = new Scene(viewRoot);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Simulation app");
        primaryStage.minWidthProperty().bind(viewRoot.minWidthProperty());
        primaryStage.minHeightProperty().bind(viewRoot.minHeightProperty());
    }


}
