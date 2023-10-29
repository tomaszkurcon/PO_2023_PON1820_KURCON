package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Simulation {
    private List<Animal> listOfAnimals = new LinkedList<>();
    private final List<MoveDirection> moves;
    
    public Simulation(List<MoveDirection> moves, List<Vector2d> initPositions) {
        for(Vector2d position : initPositions) {
            listOfAnimals.add(new Animal(position));
        }
        this.moves = moves;
    }

    public List<Animal> getListOfAnimals() {
        return Collections.unmodifiableList(listOfAnimals);
    }

    public void run() {
        for(int i=0; i<moves.size(); i++) {
            int animalIndex = i%listOfAnimals.size();
            listOfAnimals.get(animalIndex).move(moves.get(i));
            System.out.println("Zwierzę %d : ".formatted(animalIndex) + listOfAnimals.get(animalIndex).getPosition());
        }
    }
}
