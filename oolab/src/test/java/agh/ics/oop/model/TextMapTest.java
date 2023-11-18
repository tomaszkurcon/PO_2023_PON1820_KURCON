package agh.ics.oop.model;

import agh.ics.oop.OptionsParser;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TextMapTest {

    TextMap map = new TextMap();

    @Test
    void place() {
        assertTrue(map.place("word1"));
    }

    @Test
    void move() {
        map.place("word1");
        map.place("word2");
        map.move("word1", MoveDirection.RIGHT);
        map.move("word1", MoveDirection.FORWARD);
        map.move("word1", MoveDirection.FORWARD);
        assertEquals("word1", map.objectAt(1) );
        assertEquals("word2", map.objectAt(0) );

    }

    @Test
    void isOccupied() {
        map.place("word1");
        map.place("word2");

        assertFalse(map.isOccupied(3));
        assertTrue(map.isOccupied(1));
    }

    @Test
    void objectAt() {
        map.place("word1");
        map.place("word2");
        assertEquals("word1", map.objectAt(0));
        assertEquals("word2", map.objectAt(1));
    }

    @Test
    void canMoveTo() {
        map.place("word1");
        map.place("word2");
        assertFalse(map.canMoveTo(-1));
        assertFalse(map.canMoveTo(2));
        assertTrue(map.canMoveTo(1));
    }

    @Test
    void testToString() {
        map.place("word1");
        map.place("word2");
        assertEquals("[word1, word2]", map.toString());
    }

    @Test
    void config1() {
        String[] config = {"r","l","f","f", "f", "f", "f", "b", "b" };
        List<MoveDirection> directions = OptionsParser.parse(config);
        String[] listOfWords = {"word1", "word2", "word3"};
        for(String word:listOfWords) {
            map.place(word);
        }

        for(int i=0; i < directions.size(); i++) {
            int wordIndex = i%listOfWords.length;
            map.move(listOfWords[wordIndex], directions.get(i));
        }
        assertEquals("[word3, word2, word1]", map.toString());

    }
}