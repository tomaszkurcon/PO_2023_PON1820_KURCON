package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractWorldMap implements WorldMap {
    private final Map<Vector2d, Animal> animals = new HashMap<>();
    private final MapVisualizer mapVisualizer = new MapVisualizer(this);
    public Map<Vector2d, Animal> getAnimals() {
        return animals;
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
        return animals.get(position)!=null;
    }
    @Override
    public WorldElement objectAt(Vector2d position) {
        return animals.get(position);
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
        return new ArrayList<>(animals.values());
    }
}
