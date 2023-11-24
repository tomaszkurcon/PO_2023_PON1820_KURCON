package agh.ics.oop.model;

import agh.ics.oop.model.util.RandomPositionGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GrassField extends AbstractWorldMap {
    private final Map<Vector2d, Grass> grass = new HashMap<>();


    public GrassField(int amountOfGrassFields) {
        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator((int) Math.sqrt(amountOfGrassFields * 10), (int) Math.sqrt(amountOfGrassFields * 10), amountOfGrassFields);
        for(Vector2d grassPosition : randomPositionGenerator) {
            grass.put(grassPosition, new Grass(grassPosition));
        }
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        if (super.objectAt(position)!=null) {
            return super.objectAt(position);
        } else {
            return grass.get(position);
        }
    }
    private Vector2d[] calculateMapCorners(Map<Vector2d, WorldElement> map) {
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
    public String toString() {
        Vector2d[] animalsMapCorners = calculateMapCorners(new HashMap<>(super.getMoveableElements()));
        Vector2d[] grassMapCorners = calculateMapCorners(new HashMap<>(grass));
        return super.toString(animalsMapCorners[0].lowerLeft(grassMapCorners[0]), animalsMapCorners[1].upperRight(grassMapCorners[1]));
    }

    @Override
    public List<WorldElement> getElements() {
        List<WorldElement> worldElementsList = super.getElements();
        worldElementsList.addAll(new ArrayList<WorldElement>(grass.values()));
        return worldElementsList;
    }
}
