package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;


import java.util.LinkedList;


public class OptionsParser {
    public static MoveDirection[] parse(String[] args) {
        LinkedList<MoveDirection> list = new LinkedList<MoveDirection>();
        for (String arg : args) {
            MoveDirection move = switch (arg) {
                case "f" -> MoveDirection.FORWARD;
                case "b" -> MoveDirection.BACKWARD;
                case "r" -> MoveDirection.RIGHT;
                case "l" -> MoveDirection.LEFT;
                default -> null;
            };

            if (move != null) {
                list.add(move);
            }

        }

        return list.toArray(new MoveDirection[0]);
    }
}
