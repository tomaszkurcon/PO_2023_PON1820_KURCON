package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {

    @Test
    void parse() {
        String[] args1 = {"f", "l", "r", "b"};
        String[] args2 = {"bbbb", "test", "r", "ff"};
        MoveDirection[] parsedArgs1 = {MoveDirection.FORWARD, MoveDirection.LEFT, MoveDirection.RIGHT,
                MoveDirection.BACKWARD};
        MoveDirection[] parsedArgs2 = {MoveDirection.RIGHT};
        assertArrayEquals(parsedArgs1, OptionsParser.parse(args1));
        assertArrayEquals(parsedArgs2, OptionsParser.parse(args2));
    }
}