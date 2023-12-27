package agh.ics.oop.model;

import agh.ics.oop.model.exceptions.PositionAlreadyOccupiedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleMapDisplayTest {


    @Test
    void mapChanged() {
        WorldMap map = new GrassField(10, "Grassfield");
        Animal animal = new Animal();
        ConsoleMapDisplay listener = new ConsoleMapDisplay();
        map.addSubscriber(listener);
        assertEquals(1,listener.getAmountOfMapChanges());
        try{
            map.place(animal);
            map.mapChanged("Zwierzak został ustawiony");
        } catch(PositionAlreadyOccupiedException err) {

        }
        assertEquals(2,listener.getAmountOfMapChanges());

    }
}