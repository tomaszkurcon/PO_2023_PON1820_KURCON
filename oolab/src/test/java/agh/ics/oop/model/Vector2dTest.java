package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {
    Vector2d other = new Vector2d(2, 3);
    Vector2d myVector_0 = new Vector2d(1, 2);
    Vector2d myVector_1 = new Vector2d(1, 3);
    Vector2d myVector_2 = new Vector2d(2, 2);
    Vector2d myVector_3 = new Vector2d(2, 3);
    Vector2d myVector_4 = new Vector2d(3, 3);
    Vector2d myVector_5 = new Vector2d(2, 4);
    Vector2d myVector_6 = new Vector2d(3, 4);
    @Test
    void getX() {
        assertEquals(1, myVector_0.getX());
    }

    @Test
    void getY() {

        assertEquals(2, myVector_0.getY());
    }

    @Test
    void testToString() {

        String vectorToString = myVector_0.toString();
        assertEquals("(1,2)", vectorToString);
    }

    @Test
    void precedes() {
        assertTrue(myVector_0.precedes(other));
        assertTrue(myVector_1.precedes(other));
        assertTrue(myVector_2.precedes(other));
        assertTrue(myVector_3.precedes(other));
        assertFalse(myVector_4.precedes(other));
        assertFalse(myVector_5.precedes(other));
        assertFalse(myVector_6.precedes(other));
    }

    @Test
    void follows() {
        assertFalse(myVector_0.follows(other));
        assertFalse(myVector_1.follows(other));
        assertFalse(myVector_2.follows(other));
        assertTrue(myVector_3.follows(other));
        assertTrue(myVector_4.follows(other));
        assertTrue(myVector_5.follows(other));
        assertTrue(myVector_6.follows(other));
    }

    @Test
    void add() {
        assertEquals(myVector_6, myVector_0.add(myVector_2));
    }

    @Test
    void substract() {
        assertEquals(myVector_2, myVector_6.substract(myVector_0));
    }

    @Test
    void upperRight() {
        assertEquals(new Vector2d(3, 4), myVector_4.upperRight(myVector_5));
        assertEquals(new Vector2d(3, 4), myVector_0.upperRight(myVector_6));
        assertEquals(new Vector2d(3, 4), myVector_4.upperRight(myVector_5));
    }

    @Test
    void lowerLeft() {
        assertEquals(new Vector2d(1, 2), myVector_0.lowerLeft(myVector_6));
        assertEquals(new Vector2d(1, 2), myVector_1.lowerLeft(myVector_2));
        assertEquals(new Vector2d(2, 3), myVector_4.lowerLeft(myVector_5));
    }

    @Test
    void opposite() {
        assertEquals(new Vector2d(-1, -2), myVector_0.opposite());
    }

    @Test
    void testEquals() {
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(2, 4);
        assertTrue(v1.equals(myVector_0));
        assertFalse(v2.equals(myVector_0));

    }

    @Test
    void testHashCode() {
        Vector2d v1 = new Vector2d(1, 2);
        assertEquals(v1.hashCode(), myVector_0.hashCode());
    }

}