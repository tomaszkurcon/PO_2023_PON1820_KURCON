package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractWorldMap implements WorldMap {
    private final Map<Vector2d, MoveableWorldElement> moveableWorldElements = new HashMap<>();
    private final MapVisualizer mapVisualizer = new MapVisualizer(this);
    public Map<Vector2d, MoveableWorldElement> getMoveableElements() {
        return moveableWorldElements;
    }


    @Override
    public boolean place(MoveableWorldElement element) {
        if (canMoveTo(element.getPosition())) {
            moveableWorldElements.put(element.getPosition(), element);
            return true;
        }
        return false;
    }
    @Override
    public void move(MoveableWorldElement worldElement, MoveDirection direction) {
        Vector2d animalPosition = worldElement.getPosition();
        worldElement.move(direction, this);
        moveableWorldElements.remove(animalPosition);
        moveableWorldElements.put(worldElement.getPosition(), worldElement);
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

    public String toString(Vector2d leftBottomCorner, Vector2d rightUpperCorner) {
        return mapVisualizer.draw(leftBottomCorner, rightUpperCorner);
    }

    @Override
    public List<WorldElement> getElements() {
        return new ArrayList<>(moveableWorldElements.values());
    }
}
