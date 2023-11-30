package agh.ics.oop.model;

import agh.ics.oop.model.exceptions.PositionAlreadyOccupiedException;

public interface MoveableWorldElement extends WorldElement {
    void move(MoveDirection direction, MoveValidator validator) throws PositionAlreadyOccupiedException;

}
