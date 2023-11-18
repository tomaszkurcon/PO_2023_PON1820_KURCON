package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Simulation {
    private final List<Animal> listOfAnimals = new LinkedList<>();
    private final List<MoveDirection> moves;
    private final WorldMap map;
    public Simulation(List<MoveDirection> moves, List<Vector2d> initPositions, WorldMap<Animal, Vector2d> map) {
        this.moves = new LinkedList<>(moves);
        this.map = map;
        for(Vector2d position : initPositions) {
            Animal newAnimal = new Animal(position);
            if(map.place(newAnimal)) {
                listOfAnimals.add(newAnimal);
            }
        }


    }

    public List<Animal> getListOfAnimals() {
        return Collections.unmodifiableList(listOfAnimals);
    }

    public void run() {
        //Stan początkowy mapy
        System.out.println(map);
        for(int i=0; i<moves.size(); i++) {
            int animalIndex = i%listOfAnimals.size();
            map.move(listOfAnimals.get(animalIndex), moves.get(i));
            System.out.println("Zwierzę %d : ".formatted(animalIndex) + listOfAnimals.get(animalIndex).getPosition());
            System.out.println(map);
        }
    }
}
