package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {

    RectangularMap map = new RectangularMap(4,4);
    Animal animal = new Animal(new Vector2d(2,2));
    @Test
    void place() {
        assertTrue(map.place(animal));
        assertFalse(map.place(animal));
    }

    @Test
    void move() {
        map.place(animal);
        map.move(animal, MoveDirection.FORWARD);
        map.move(animal, MoveDirection.FORWARD);
        map.move(animal, MoveDirection.FORWARD);
        map.move(animal, MoveDirection.RIGHT);
        map.move(animal, MoveDirection.BACKWARD);
        map.move(animal, MoveDirection.BACKWARD);
        map.move(animal, MoveDirection.BACKWARD);
        assertEquals(new Vector2d(0,4), animal.getPosition());
    }

    @Test
    void isOccupied() {
        map.place(animal);
        assertTrue(map.isOccupied(new Vector2d(2,2)));
        assertFalse(map.isOccupied(new Vector2d(2,1)));

    }

    @Test
    void objectAt() {
        map.place(animal);
        assertEquals(animal, map.objectAt(new Vector2d(2,2)));
        assertEquals(null, map.objectAt(new Vector2d(1, 2)));
    }

    @Test
    void canMoveTo() {
        map.place(animal);
        assertFalse(map.canMoveTo(new Vector2d(2,2)));
        assertFalse(map.canMoveTo(new Vector2d(2,5)));
        assertTrue(map.canMoveTo(new Vector2d(2,4)));
    }
}