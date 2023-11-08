package agh.ics.oop;
import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.List;

public class World {
    public static void main(String[] args) {
        System.out.println("System wystartował");
        Animal my_animal = new Animal();
        System.out.println(my_animal);
        List<MoveDirection> directions = OptionsParser.parse(args);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        Simulation simulation = new Simulation(directions, positions);
        simulation.run();
        run(OptionsParser.parse(args));
        System.out.println("System zakończył działanie");
    }

    public static void run(List<MoveDirection> args) {
        String message;
        System.out.println("Zwierzak idzie do przodu");
        System.out.print(args.get(0));
        for (int i = 1; i < args.size(); i++) {
            System.out.print("," + args.get(i));
        }
        System.out.println("");
        for (MoveDirection arg : args) {
            message = switch (arg) {
                case FORWARD -> "zwierzak idzie do przodu";
                case BACKWARD -> "zwierzak idzie do tyłu";
                case RIGHT -> "zwierzak skręca w prawo";
                case LEFT -> "zwierzak skręca w lewo";
            };

            System.out.println(message);

        }
    }
}



