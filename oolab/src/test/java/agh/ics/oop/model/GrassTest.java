package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrassTest {
    Grass grass = new Grass(new Vector2d(2,2));
    @Test
    void getPosition() {
        assertEquals(new Vector2d(2,2),grass.getPosition());
    }

    @Test
    void testToString() {
        assertEquals("*", grass.toString());
    }
}