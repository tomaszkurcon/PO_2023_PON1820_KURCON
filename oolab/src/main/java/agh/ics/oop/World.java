package agh.ics.oop;
import agh.ics.oop.model.*;
import java.util.List;


public class World {
    public static void main(String[] args) {
        System.out.println("System wystartował");
        ConsoleMapDisplay mapObserver = new ConsoleMapDisplay();
        List<MoveDirection> directions = OptionsParser.parse(args);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(2,3));
//        WorldMap map = new RectangularMap(3,5);
        WorldMap grassMap = new GrassField(10);
        grassMap.addSubscriber(mapObserver);
        Simulation simulation = new Simulation(directions, positions,  grassMap);
        simulation.run();
//        run(OptionsParser.parse(args));
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



