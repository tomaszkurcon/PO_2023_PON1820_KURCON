package agh.ics.oop.model;

public class Animal {
    private MapDirection animalOrientation;
    private Vector2d position;


    public Animal(Vector2d location) {
        this.position = location;
        this.animalOrientation = MapDirection.NORTH;
    }
    public Animal() {
        this(new Vector2d(2, 2));
    }

    @Override
    public String toString() {
        return "Position: (%d, %d), Orientation: %s".formatted(position.getX(), position.getY(), animalOrientation.toString());
    }

    public boolean isAt(Vector2d position) {
        return position.equals(this.position);
    }

    public MapDirection getAnimalOrientation() {
        return animalOrientation;
    }

    public Vector2d getPosition() {
        return position;
    }

    public void move(MoveDirection direction) {

        switch (direction) {
            case RIGHT:
                this.animalOrientation = animalOrientation.next();
                break;
            case LEFT:
                this.animalOrientation = animalOrientation.previous();
                break;
            case FORWARD:
                moveIfIsInMap(this.position.add(animalOrientation.toUnitVector()));
                break;
            case BACKWARD:
                moveIfIsInMap(this.position.substract(animalOrientation.toUnitVector()));
                break;

        }
    }

    private void moveIfIsInMap(Vector2d position) {
        if (position.precedes(new Vector2d(4, 4)) && position.follows(new Vector2d(0, 0))) {
            this.position = position;
        }
    }

}
