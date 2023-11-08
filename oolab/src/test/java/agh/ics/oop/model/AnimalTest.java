package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {
    Animal myAnimal = new Animal(new Vector2d(2,2));
    Animal myAnimal2 = new Animal();

    @Test
    void testToString() {
        assertEquals("Position: (2, 2), Orientation: Północ", myAnimal2.toString());
    }

    @Test
    void isAt() {
            Vector2d position1 = new Vector2d(2,2);
            Vector2d position2 = new Vector2d(4,3);
            assertTrue(myAnimal.isAt(position1));
            assertFalse(myAnimal.isAt(position2));
    }

    @Test
    void move() {
        MoveDirection[] moves = {MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.BACKWARD, MoveDirection.LEFT,
                MoveDirection.LEFT, MoveDirection.FORWARD, MoveDirection.LEFT, MoveDirection.BACKWARD
        };
        Vector2d finalPosition = new Vector2d(0,4);
        for(MoveDirection move: moves) {
            myAnimal.move(move);
        }
        assertEquals(finalPosition, myAnimal.getPosition());
        assertEquals(MapDirection.SOUTH, myAnimal.getAnimalOrientation());
        myAnimal.move(MoveDirection.BACKWARD);
        assertEquals(finalPosition, myAnimal.getPosition());
        myAnimal.move(MoveDirection.RIGHT);
        myAnimal.move(MoveDirection.FORWARD);
        assertEquals(finalPosition, myAnimal.getPosition());

    }

}