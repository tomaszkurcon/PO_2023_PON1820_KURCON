package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {

    @Test
    void parse() {
        String[] args1 = {"f", "l", "r", "b"};
        String[] args2 = {"bbbb", "test", "r", "ff"};
        List<MoveDirection> parsedArgs1 = new LinkedList<MoveDirection>(List.of(MoveDirection.FORWARD, MoveDirection.LEFT, MoveDirection.RIGHT,
                MoveDirection.BACKWARD));
        List<MoveDirection> parsedArgs2 = new LinkedList<MoveDirection>(List.of(MoveDirection.RIGHT));
        assertEquals(parsedArgs1, OptionsParser.parse(args1));
        assertEquals(parsedArgs2, OptionsParser.parse(args2));
    }
}