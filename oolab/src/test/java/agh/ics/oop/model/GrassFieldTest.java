package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {

    GrassField map = new GrassField(10);
    Animal animal = new Animal(new Vector2d(2,2));



    @Test
    void isOccupied() {
        map.place(animal);
        assertTrue(map.isOccupied(new Vector2d(2,2)));
    }

    @Test
    void objectAt() {
        map.place(animal);
        assertEquals(animal,map.objectAt(new Vector2d(2,2)));
    }

    @Test
    void getElements() {
        map.place(animal);
        List correctList = new ArrayList<>(List.of("N", "*", "*", "*", "*", "*", "*", "*", "*", "*", "*"));
        assertEquals(correctList.toString(), map.getElements().toString());
    }
}