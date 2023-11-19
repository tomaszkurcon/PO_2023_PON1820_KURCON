package agh.ics.oop;

import agh.ics.oop.model.*;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;


import static org.junit.jupiter.api.Assertions.*;

class SimulationTest {
    Vector2d position1 = new Vector2d(0, 0);
    Vector2d position2 = new Vector2d(4, 4);
    Vector2d position3 = new Vector2d(2, 2);


    @Test
    void rectangularMapRunConfig1() {
        String[] simulationConfig1 = {"f", "l", "r", "f", "f", "l"};
        WorldMap map1 = new RectangularMap(4,5);
        Simulation simulation1 = new Simulation(OptionsParser.parse(simulationConfig1),
                new LinkedList<>(List.of(position1, position2)), map1);
        List<Animal> animalList1 = simulation1.getListOfAnimals();

        simulation1.run();

        assertEquals(MapDirection.EAST, animalList1.get(0).getAnimalOrientation());
        assertEquals(MapDirection.SOUTH, animalList1.get(1).getAnimalOrientation());

        assertEquals(new Vector2d(1,1), animalList1.get(0).getPosition());
        assertEquals(new Vector2d(3,4), animalList1.get(1).getPosition());

    }
    @Test
    void rectangularMapRunConfig2() {
        String[] simulationConfig2 = {"l", "f", "f", "f","bad", "l", "f", "r","t", "f","r", "b", "f"};
        WorldMap map2 = new RectangularMap(4,4);
        Simulation simulation2 = new Simulation(OptionsParser.parse(simulationConfig2),
                new LinkedList<>(List.of(position1, position2, position3)), map2);
        List<Animal> animalList2 = simulation2.getListOfAnimals();

        simulation2.run();

        assertEquals(MapDirection.NORTH, animalList2.get(0).getAnimalOrientation());
        assertEquals(MapDirection.WEST, animalList2.get(1).getAnimalOrientation());
        assertEquals(MapDirection.EAST, animalList2.get(2).getAnimalOrientation());

        assertEquals(new Vector2d(0,0), animalList2.get(0).getPosition());
        assertEquals(new Vector2d(3,4), animalList2.get(1).getPosition());
        assertEquals(new Vector2d(2,4), animalList2.get(2).getPosition());
    }

    @Test
    void grassFieldRunConfig1() {
        String[] simulationConfig1 = {"f", "l", "f", "f","l", "f", "f", "r","f", "f","l", "f", "f","f","f","f","f"};
        WorldMap map1 = new GrassField(20);
        Simulation simulation = new Simulation(OptionsParser.parse(simulationConfig1),
                new LinkedList<>(List.of(position1, position2)), map1);
        List<Animal> animalList1 = simulation.getListOfAnimals();

        simulation.run();

        assertEquals(MapDirection.SOUTH, animalList1.get(0).getAnimalOrientation());
        assertEquals(MapDirection.NORTH, animalList1.get(1).getAnimalOrientation());


        assertEquals(new Vector2d(-2,-1), animalList1.get(0).getPosition());
        assertEquals(new Vector2d(2,8), animalList1.get(1).getPosition());

    }
}