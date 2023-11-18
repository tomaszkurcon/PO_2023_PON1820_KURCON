package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap implements WorldMap<Animal, Vector2d> {
    private final Map<Vector2d, Animal> animals = new HashMap<>();
    private final int width;
    private final int height;
    private final Vector2d rightUpperCorner;
    private final Vector2d leftBottomCorner;
    private final MapVisualizer mapVisualizer = new MapVisualizer(this);

    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
        rightUpperCorner = new Vector2d(width, height);
        leftBottomCorner = new Vector2d(0, 0);
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            animals.put(animal.getPosition(), animal);
            return true;
        }
        return false;
    }



    @Override
    public void move(Animal animal, MoveDirection direction) {
        Vector2d animalPosition = animal.getPosition();
        animal.move(direction, this);
        animals.remove(animalPosition);
        animals.put(animal.getPosition(), animal);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }

    @Override
    public Animal objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        boolean isInMap = position.precedes(rightUpperCorner) && position.follows(leftBottomCorner);
        return animals.get(position) == null && isInMap;
    }

    @Override
    public String toString() {
        return mapVisualizer.draw(leftBottomCorner, rightUpperCorner);
    }
}
