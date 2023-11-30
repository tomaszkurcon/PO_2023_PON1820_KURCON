package agh.ics.oop.model;

import agh.ics.oop.model.exceptions.PositionAlreadyOccupiedException;
import agh.ics.oop.model.util.Boundary;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {

    RectangularMap map = new RectangularMap(4, 4);
    Animal animal = new Animal(new Vector2d(2, 2));

    @Test
    void place() {
        assertDoesNotThrow(()->map.place(animal));
        assertThrows(PositionAlreadyOccupiedException.class, () -> {
            map.place(animal);
        });


    }

    @Test
    void move() {
        assertDoesNotThrow(()->map.place(animal));
        map.move(animal, MoveDirection.FORWARD);
        map.move(animal, MoveDirection.FORWARD);
        map.move(animal, MoveDirection.FORWARD);
        map.move(animal, MoveDirection.RIGHT);
        map.move(animal, MoveDirection.BACKWARD);
        map.move(animal, MoveDirection.BACKWARD);
        map.move(animal, MoveDirection.BACKWARD);
        assertEquals(new Vector2d(0, 4), animal.getPosition());
    }

    @Test
    void isOccupied() {
        assertDoesNotThrow(()->map.place(animal));
        assertTrue(map.isOccupied(new Vector2d(2, 2)));
        assertFalse(map.isOccupied(new Vector2d(2, 1)));

    }

    @Test
    void objectAt() {
        assertDoesNotThrow(()->map.place(animal));
        assertEquals(animal, map.objectAt(new Vector2d(2, 2)));
        assertEquals(null, map.objectAt(new Vector2d(1, 2)));
    }

    @Test
    void canMoveTo() {
        assertDoesNotThrow(()->map.place(animal));
        assertFalse(map.canMoveTo(new Vector2d(2, 2)));
        assertFalse(map.canMoveTo(new Vector2d(2, 5)));
        assertTrue(map.canMoveTo(new Vector2d(2, 4)));
    }

    @Test
    void getCurrentBounds() {
        Boundary boundaries = map.getCurrentBounds();
        Boundary correctBoundaries = new Boundary(new Vector2d(0,0),new Vector2d(4,4));
        assertEquals(correctBoundaries, boundaries);
    }

}