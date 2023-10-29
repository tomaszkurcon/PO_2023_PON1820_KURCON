package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapDirectionTest {

    @Test
    void testToString() {
        assertEquals("Północ", MapDirection.NORTH.toString());
        assertEquals("Wschód", MapDirection.EAST.toString());
        assertEquals("Zachód", MapDirection.WEST.toString());
        assertEquals("Południe", MapDirection.SOUTH.toString());
    }

    @Test
    void next() {
        assertEquals(MapDirection.NORTH, MapDirection.WEST.next());
        assertEquals(MapDirection.WEST, MapDirection.SOUTH.next());
        assertEquals(MapDirection.SOUTH, MapDirection.EAST.next());
        assertEquals(MapDirection.EAST, MapDirection.NORTH.next());
    }

    @Test
    void previous() {
        assertEquals(MapDirection.SOUTH, MapDirection.WEST.previous());
        assertEquals(MapDirection.EAST, MapDirection.SOUTH.previous());
        assertEquals(MapDirection.NORTH, MapDirection.EAST.previous());
        assertEquals(MapDirection.WEST, MapDirection.NORTH.previous());
    }

    @Test
    void toUnitVector() {
        assertEquals(new Vector2d(0, 1), MapDirection.NORTH.toUnitVector());
        assertEquals(new Vector2d(-1, 0), MapDirection.WEST.toUnitVector());
        assertEquals(new Vector2d(1, 0), MapDirection.EAST.toUnitVector());
        assertEquals(new Vector2d(0, -1), MapDirection.SOUTH.toUnitVector());
    }
}