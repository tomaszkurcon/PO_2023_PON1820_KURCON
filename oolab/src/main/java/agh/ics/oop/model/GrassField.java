package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;


public class GrassField implements WorldMap {
    private final int amountOfGrassFields;
    private final Map<Vector2d, Animal> animals = new HashMap<>();
    private final Map<Vector2d, Grass> grass = new HashMap<>();
    private final MapVisualizer mapVisualizer = new MapVisualizer(this);
    public GrassField(int amountOfGrassFields) {
        this.amountOfGrassFields = amountOfGrassFields;
        for(int i=0; i<amountOfGrassFields; i++) {

            Vector2d newRandomPosition = generateRandomPosition();
            while(grass.containsKey(newRandomPosition)) {
                newRandomPosition = generateRandomPosition();
            }
            grass.put(newRandomPosition, new Grass(newRandomPosition));
        }
    }

    private Vector2d generateRandomPosition() {
        int max = (int)Math.sqrt(amountOfGrassFields*10);
        int min = 0;
        return new Vector2d((int)(Math.random()*(max-min+1)+min), (int)(Math.random()*(max-min+1)+min));
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !(objectAt(position) instanceof Animal);
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
        return objectAt(position)!=null;
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        if (animals.get(position) != null) {
            return animals.get(position);
        }
        else {
            return grass.get(position);
        }
    }

    private Vector2d[] calculateMapCorners(Map<Vector2d, WorldElement> map) {
        int xMax = Integer.MIN_VALUE, xMin = Integer.MAX_VALUE, yMax = Integer.MIN_VALUE, yMin = Integer.MAX_VALUE;
        for(Vector2d position : map.keySet()) {
            xMax = Math.max(xMax, position.getX());
            xMin = Math.min(xMin, position.getX());
            yMax = Math.max(yMax, position.getY());
            yMin = Math.min(yMin, position.getY());
        }
        Vector2d[] corners = {new Vector2d(xMin, yMin), new Vector2d(xMax, yMax)};

        return corners;
    }


    @Override
    public String toString() {
        Vector2d[] animalsMapCorners = calculateMapCorners(new HashMap<>(animals));
        Vector2d[] grassMapCorners = calculateMapCorners(new HashMap<>(grass));
        return mapVisualizer.draw(animalsMapCorners[0].lowerLeft(grassMapCorners[0]), animalsMapCorners[1].upperRight(grassMapCorners[1]));

    }
}
