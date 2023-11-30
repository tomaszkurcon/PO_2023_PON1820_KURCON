package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;


import java.util.LinkedList;
import java.util.List;


public class OptionsParser {
    public static List<MoveDirection> parse(String[] args)  {
        List<MoveDirection> list = new LinkedList<MoveDirection>();
        for (String arg : args) {
            MoveDirection move = switch (arg) {
                case "f" -> MoveDirection.FORWARD;
                case "b" -> MoveDirection.BACKWARD;
                case "r" -> MoveDirection.RIGHT;
                case "l" -> MoveDirection.LEFT;
                default -> throw new IllegalArgumentException(arg + " is not legal move specification");
            };

            list.add(move);


        }
        return list;
    }
}
