package agh.ics.oop.model;

public class ConsoleMapDisplay implements MapChangeListener{
    private int amountOfMapChanges = 1;
    @Override
    public synchronized void mapChanged(WorldMap worldMap, String message) {
        System.out.println("Numer zmiany: " + amountOfMapChanges);
        System.out.println(worldMap.getId());
        System.out.println(message);
        System.out.println(worldMap);
        amountOfMapChanges+=1;
    }
}
