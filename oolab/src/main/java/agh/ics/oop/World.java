package agh.ics.oop;
import agh.ics.oop.model.*;

import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;


public class World {
    public static void main(String[] args) {
        System.out.println("System wystartował");
        Instant start = Instant.now();
        List<MoveDirection> directions = OptionsParser.parse(args);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(2,3));
        ConsoleMapDisplay mapObserver = new ConsoleMapDisplay();
        List<Simulation> simulations = new LinkedList<>();
        for(int i=0; i<10000; i++) {
            WorldMap rectangularMap = new RectangularMap(3,5, "RectangularMap" + i);
            WorldMap grassMap = new GrassField(10, "Grassfield" + i);
            rectangularMap.addSubscriber(mapObserver);
            grassMap.addSubscriber(mapObserver);
            Simulation rectangularMapSimulation = new Simulation(directions, positions,  rectangularMap);
            Simulation grassMapSimulation = new Simulation(directions, positions,  grassMap);
            simulations.add(rectangularMapSimulation);
            simulations.add(grassMapSimulation);
        }

        SimulationEngine simulationEngine = new SimulationEngine(simulations);
        simulationEngine.runSync();
//        run(OptionsParser.parse(args));
        Instant end = Instant.now();
        System.out.println(Duration.between(start,end).toMillis());
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



