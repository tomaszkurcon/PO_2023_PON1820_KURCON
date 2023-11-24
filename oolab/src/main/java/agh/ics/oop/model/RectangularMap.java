package agh.ics.oop.model;

public class RectangularMap extends AbstractWorldMap {
    private final Vector2d rightUpperCorner;
    private final Vector2d leftBottomCorner;

    public RectangularMap(int width, int height) {
        rightUpperCorner = new Vector2d(width, height);
        leftBottomCorner = new Vector2d(0, 0);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        boolean isInMap = position.precedes(rightUpperCorner) && position.follows(leftBottomCorner);
        return super.canMoveTo(position) && isInMap;
    }
    @Override
    public String toString() {
        return super.toString(leftBottomCorner,rightUpperCorner);
    }
}
