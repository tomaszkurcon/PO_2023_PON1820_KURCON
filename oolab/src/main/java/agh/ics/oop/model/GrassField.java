package agh.ics.oop.model;

import agh.ics.oop.model.util.Boundary;
import agh.ics.oop.model.util.RandomPositionGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GrassField extends AbstractWorldMap {
    private final Map<Vector2d, Grass> grass = new HashMap<>();


    public GrassField(int amountOfGrassFields, String id) {
        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator((int) Math.sqrt(amountOfGrassFields * 10), (int) Math.sqrt(amountOfGrassFields * 10), amountOfGrassFields);
        for(Vector2d grassPosition : randomPositionGenerator) {
            grass.put(grassPosition, new Grass(grassPosition));
        }
        super.id = id;
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        if (super.objectAt(position)!=null) {
            return super.objectAt(position);
        } else {
            return grass.get(position);
        }
    }

    @Override
    public List<WorldElement> getElements() {
        List<WorldElement> worldElementsList = super.getElements();
        worldElementsList.addAll(grass.values());
        return worldElementsList;
    }

    public Map<Vector2d, Grass> getGrass() {
        return grass;
    }

    @Override
    public Boundary getCurrentBounds() {
        Vector2d[] animalsMapCorners = calculateMapCorners(new HashMap<>(super.getMoveableElements()));
        Vector2d[] grassMapCorners = calculateMapCorners(new HashMap<>(grass));
        return new Boundary(animalsMapCorners[0].lowerLeft(grassMapCorners[0]), animalsMapCorners[1].upperRight(grassMapCorners[1]));
    }


}
