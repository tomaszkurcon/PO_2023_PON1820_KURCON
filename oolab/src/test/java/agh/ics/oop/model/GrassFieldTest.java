package agh.ics.oop.model;

import agh.ics.oop.model.exceptions.PositionAlreadyOccupiedException;
import agh.ics.oop.model.util.Boundary;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {

    GrassField map = new GrassField(10, "Grassfield");
    Animal animal = new Animal(new Vector2d(2,2));



    @Test
    void isOccupied() {
        try{
            map.place(animal);
        } catch(PositionAlreadyOccupiedException e) {

        }

        assertTrue(map.isOccupied(new Vector2d(2,2)));
    }

    @Test
    void objectAt() {
        try{
            map.place(animal);
        } catch(PositionAlreadyOccupiedException e) {

        }

        assertEquals(animal,map.objectAt(new Vector2d(2,2)));
    }

    @Test
    void getElements() {
        try{
            map.place(animal);
        } catch(PositionAlreadyOccupiedException e) {}
        List correctList = new ArrayList<>(List.of("N", "*", "*", "*", "*", "*", "*", "*", "*", "*", "*"));
        assertEquals(correctList.toString(), map.getElements().toString());
    }

    @Test
    void calculateMapCorners() {
        GrassField map = new GrassField(10, "Grassfield2");
        Animal rightUpperAnimal = new Animal(new Vector2d(11, 11));
        Animal leftBottomAnimal = new Animal(new Vector2d(-1,-1));
        try{
            map.place(rightUpperAnimal);
            map.place(leftBottomAnimal);
        } catch(PositionAlreadyOccupiedException e) {}
        Vector2d[] grassMapCorners = map.calculateMapCorners(new HashMap<>(map.getGrass()));
        Vector2d[] moveableElementsMapCorners = map.calculateMapCorners(new HashMap<>(map.getMoveableElements()));
        Boundary boundaries = new Boundary(moveableElementsMapCorners[0].lowerLeft(grassMapCorners[0]), moveableElementsMapCorners[1].upperRight(grassMapCorners[1]));
        assertEquals(new Boundary(new Vector2d(-1,-1), new Vector2d(11,11)), boundaries);
    }

    @Test
    void addSubscriber() {
        MapChangeListener mapObserver = new ConsoleMapDisplay();
        map.addSubscriber(mapObserver);
        assertEquals(mapObserver, map.getListOfListeners().get(0));
    }
    @Test
    void removeSubscriber() {
        MapChangeListener mapObserver = new ConsoleMapDisplay();
        map.addSubscriber(mapObserver);
        map.removeSubscriber(mapObserver);
        assertEquals(0, map.getListOfListeners().size());
    }
}