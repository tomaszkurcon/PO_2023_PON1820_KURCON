package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.exceptions.PositionAlreadyOccupiedException;
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
    @Test
    void parse2() {
        String[] args = {"bbbb", "test", "r", "ff"};
        assertThrows(IllegalArgumentException.class,() -> {
            OptionsParser.parse(args);
        });
    }
}