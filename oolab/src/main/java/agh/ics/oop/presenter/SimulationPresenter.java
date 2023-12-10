package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.model.*;
import agh.ics.oop.model.util.Boundary;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;


import java.awt.*;
import java.util.List;

public class SimulationPresenter implements MapChangeListener {
    private WorldMap WorldMap;
    @FXML
    private TextField movesInput;
    @FXML
    private Label moveDescription;
    @FXML
    private GridPane mapGrid;

    public void setMap(WorldMap map) {
        this.WorldMap = map;
    }

    private void drawMap(WorldMap map) {
        clearGrid();
        Boundary mapBoundaries = map.getCurrentBounds();
        int columns = mapBoundaries.rightUpperCorner().getX() - mapBoundaries.leftBottomCorner().getX();
        int rows = mapBoundaries.rightUpperCorner().getY() - mapBoundaries.leftBottomCorner().getY();
        int offsetX = mapBoundaries.leftBottomCorner().getX();
        int offsetY = mapBoundaries.leftBottomCorner().getY();
        Text label = new Text("y/x");
        mapGrid.setHalignment(label, HPos.CENTER);
        mapGrid.add(label, 0, 0);

        for (int i = 0; i < rows + 1; i++) {
            mapGrid.getRowConstraints().add(new RowConstraints(30));
            Text coordinateY = new Text(Integer.toString(i + offsetY));
            mapGrid.setHalignment(coordinateY, HPos.CENTER);
            mapGrid.add(coordinateY, 0, i + 1);
        }
        for (int i = 0; i < columns + 1; i++) {
            mapGrid.getColumnConstraints().add(new ColumnConstraints(30));
            Text coordinateX = new Text(Integer.toString(i + offsetX));
            mapGrid.setHalignment(coordinateX, HPos.CENTER);
            mapGrid.add(coordinateX, i + 1, 0);
        }
        mapGrid.getRowConstraints().add(new RowConstraints(30));
        mapGrid.getColumnConstraints().add(new ColumnConstraints(30));
        for (WorldElement el : map.getElements()) {
            Text worldElement = new Text(el.toString());
            mapGrid.setHalignment(worldElement, HPos.CENTER);
            mapGrid.add(worldElement, el.getPosition().getX() + 1 - offsetX, el.getPosition().getY() + 1 - offsetY);
        }

    }

    public void onSimulationStartClicked() {
        String[] moves = movesInput.getText().split(" ");
        List<Vector2d> positions = List.of(new Vector2d(0, 0), new Vector2d(3, 3));
        List<MoveDirection> directions = OptionsParser.parse(moves);
        Simulation grassMapSimulation = new Simulation(directions, positions, WorldMap);
        SimulationEngine simulationEngine = new SimulationEngine(List.of(grassMapSimulation));
        simulationEngine.runAsync();
    }

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        Platform.runLater(() -> {
            drawMap(worldMap);
            moveDescription.setText(message);
        });
    }

    private void clearGrid() {
        mapGrid.getChildren().retainAll(mapGrid.getChildren().get(0)); // hack to retain visible grid lines
        mapGrid.getColumnConstraints().clear();
        mapGrid.getRowConstraints().clear();
    }
}
