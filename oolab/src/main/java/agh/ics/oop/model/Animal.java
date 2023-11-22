package agh.ics.oop.model;

public class Animal implements MoveableWorldElement {
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
        return "%s".formatted(animalOrientation.toString());
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

    public void move(MoveDirection direction, MoveValidator moveValidator) {

        switch (direction) {
            case RIGHT:
                this.animalOrientation = animalOrientation.next();
                break;
            case LEFT:
                this.animalOrientation = animalOrientation.previous();
                break;
            case FORWARD:
                Vector2d destination1 = this.position.add(animalOrientation.toUnitVector());
                if (moveValidator.canMoveTo(destination1)) {
                    this.position = destination1;
                }
                break;
            case BACKWARD:
                Vector2d destination2 = this.position.substract(animalOrientation.toUnitVector());
                if (moveValidator.canMoveTo(destination2)) {
                    this.position = destination2;
                }
                break;

        }
    }


}
