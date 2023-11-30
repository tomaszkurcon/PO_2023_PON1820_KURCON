package agh.ics.oop.model;

import agh.ics.oop.model.exceptions.PositionAlreadyOccupiedException;
import agh.ics.oop.model.util.Boundary;
import agh.ics.oop.model.util.MapVisualizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractWorldMap implements WorldMap {
    private final Map<Vector2d, MoveableWorldElement> moveableWorldElements = new HashMap<>();
    private final MapVisualizer mapVisualizer = new MapVisualizer(this);
    private final List<MapChangeListener>  listOfListeners = new ArrayList<>();
    public Map<Vector2d, MoveableWorldElement> getMoveableElements() {
        return moveableWorldElements;
    }


    public List<MapChangeListener> getListOfListeners() {
        return listOfListeners;
    }

    @Override
    public void place(MoveableWorldElement element) throws PositionAlreadyOccupiedException {
        if (canMoveTo(element.getPosition())) {
            moveableWorldElements.put(element.getPosition(), element);
        } else {
            throw new PositionAlreadyOccupiedException(element.getPosition());
        }

    }
    @Override
    public void move(MoveableWorldElement worldElement, MoveDirection direction) {
        Vector2d animalPosition = worldElement.getPosition();
        try {
            worldElement.move(direction, this);
            moveableWorldElements.remove(animalPosition);
            moveableWorldElements.put(worldElement.getPosition(), worldElement);
            mapChanged("Zwierzak poruszył się z pozycji: " + animalPosition + " na pozycję: " + worldElement.getPosition());
        } catch(PositionAlreadyOccupiedException er) {
            mapChanged("Zwierzak nie mógł poruszyć się na wskazane pole");
        }

    }
    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position)!=null;
    }
    @Override
    public WorldElement objectAt(Vector2d position) {
        return moveableWorldElements.get(position);
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        return !(objectAt(position) instanceof Animal);
    }

    @Override
    public String toString() {
        Boundary boundary = getCurrentBounds();
        return mapVisualizer.draw(boundary.leftBottomCorner(), boundary.rightUpperCorner());
    }

    @Override
    public List<WorldElement> getElements() {
        return new ArrayList<>(moveableWorldElements.values());
    }

    protected Vector2d[] calculateMapCorners(Map<Vector2d, WorldElement> map) {
        int xMax = Integer.MIN_VALUE, xMin = Integer.MAX_VALUE, yMax = Integer.MIN_VALUE, yMin = Integer.MAX_VALUE;
        for (Vector2d position : map.keySet()) {
            xMax = Math.max(xMax, position.getX());
            xMin = Math.min(xMin, position.getX());
            yMax = Math.max(yMax, position.getY());
            yMin = Math.min(yMin, position.getY());
        }
        Vector2d[] corners = {new Vector2d(xMin, yMin), new Vector2d(xMax, yMax)};

        return corners;
    }

    @Override
    public void addSubscriber(MapChangeListener listener) {
        listOfListeners.add(listener);
    }
    @Override
    public void removeSubscriber(MapChangeListener listener) {
        listOfListeners.remove(listener);
    }
    @Override
    public void mapChanged(String message) {
        for(MapChangeListener listener : listOfListeners) {
            listener.mapChanged(this, message);
        }
    }
}
