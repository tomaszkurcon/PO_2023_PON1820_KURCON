package agh.ics.oop;

import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

public class World {
    public static void main(String[] args) {
        System.out.println("System wystartował");
        run(OptionsParser.parse(args));
        System.out.println("System zakończył działanie");
    }

    public static void run(MoveDirection[] args) {
        String message;
        System.out.println("Zwierzak idzie do przodu");
        System.out.print(args[0]);
        for (int i = 1; i < args.length; i++) {
            System.out.print("," + args[i]);
        }
        System.out.println("");
        for (MoveDirection arg : args) {
            message = switch (arg) {
                case FORWARD -> "zwierzak idzie do przodu";
                case BACKWARD -> "zwierzak idzie do tyłu";
                case RIGHT -> "zwierzak skręca w prawo";
                case LEFT -> "zwierzak skręca w lewo";
                default -> null;
            };
            if (message != null) {
                System.out.println(message);
            }
        }
    }
}



