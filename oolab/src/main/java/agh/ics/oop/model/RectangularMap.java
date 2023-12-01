package agh.ics.oop.model;

import agh.ics.oop.model.util.Boundary;

public class RectangularMap extends AbstractWorldMap {
    private final Vector2d rightUpperCorner;
    private final Vector2d leftBottomCorner;

    public RectangularMap(int width, int height, String id) {
        rightUpperCorner = new Vector2d(width, height);
        leftBottomCorner = new Vector2d(0, 0);
        super.id = id;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        boolean isInMap = position.precedes(rightUpperCorner) && position.follows(leftBottomCorner);
        return !super.isOccupied(position) && isInMap;
    }

    @Override
    public Boundary getCurrentBounds() {
        return new Boundary(leftBottomCorner, rightUpperCorner);
    }
}
