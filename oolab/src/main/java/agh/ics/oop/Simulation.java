package agh.ics.oop;

import agh.ics.oop.model.*;
import agh.ics.oop.model.exceptions.PositionAlreadyOccupiedException;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Simulation implements Runnable {
    private final List<Animal> listOfAnimals = new LinkedList<>();
    private final List<MoveDirection> moves;
    private final WorldMap map;
    public Simulation(List<MoveDirection> moves, List<Vector2d> initPositions, WorldMap map) {
        this.moves = new LinkedList<>(moves);
        this.map = map;
        for(Vector2d position : initPositions) {
            Animal newAnimal = new Animal(position);
            try{
                map.place(newAnimal);
                listOfAnimals.add(newAnimal);
                map.mapChanged("Zwierzak został ustawiony na pozycji: " + position);
            } catch(PositionAlreadyOccupiedException ex) {
                map.mapChanged("Nie udało się postawić zwierzaka na pozycji: " + position);
            }
        }
    }

    public List<Animal> getListOfAnimals() {
        return Collections.unmodifiableList(listOfAnimals);
    }

    public void run() {
        for(int i=0; i<moves.size(); i++) {
            int animalIndex = i%listOfAnimals.size();
            System.out.println("Zwierzę nr: " + animalIndex);
            map.move(listOfAnimals.get(animalIndex), moves.get(i));
        }
    }
}
