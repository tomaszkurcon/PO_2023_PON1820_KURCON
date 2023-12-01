package agh.ics.oop.model;

import agh.ics.oop.model.exceptions.PositionAlreadyOccupiedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {
    Animal myAnimal = new Animal(new Vector2d(2,2));
    Animal myAnimal2 = new Animal();
    WorldMap map = new RectangularMap(4,4, "RectangularMap");

    @Test
    void testToString() {
        assertEquals("N", myAnimal2.toString());
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
            try{
                myAnimal.move(move, map);
            } catch(PositionAlreadyOccupiedException er) {}



        }
        assertEquals(finalPosition, myAnimal.getPosition());
        assertEquals(MapDirection.SOUTH, myAnimal.getAnimalOrientation());
        try{
            myAnimal.move(MoveDirection.BACKWARD, map);
        } catch(PositionAlreadyOccupiedException er) {}

        assertEquals(finalPosition, myAnimal.getPosition());
        try{
            myAnimal.move(MoveDirection.RIGHT, map);
        } catch(PositionAlreadyOccupiedException er) {}
        try{
            myAnimal.move(MoveDirection.FORWARD, map);
        } catch(PositionAlreadyOccupiedException er) {}


        assertEquals(finalPosition, myAnimal.getPosition());

    }

}