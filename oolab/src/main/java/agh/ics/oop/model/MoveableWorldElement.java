package agh.ics.oop.model;

public interface MoveableWorldElement extends WorldElement {
    void move(MoveDirection direction, MoveValidator validator);

}
