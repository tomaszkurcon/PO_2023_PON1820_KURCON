package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {

    @Test
    void parse1() {
        String[] args = {"f", "l", "r", "b"};
        List<MoveDirection> parsedArgs = new LinkedList<MoveDirection>(List.of(MoveDirection.FORWARD, MoveDirection.LEFT, MoveDirection.RIGHT,
                MoveDirection.BACKWARD));
        assertEquals(parsedArgs, OptionsParser.parse(args));
    }
    void parse2() {
        String[] args = {"bbbb", "test", "r", "ff"};
        List<MoveDirection> parsedArgs = new LinkedList<MoveDirection>(List.of(MoveDirection.RIGHT));
        assertEquals(parsedArgs, OptionsParser.parse(args));
    }
}