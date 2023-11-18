package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class TextMap implements WorldMap<String, Integer> {

    private final Map<String, Integer> words = new HashMap<>();
    private final ArrayList<MapDirection> wordsOrientations = new ArrayList<>();
    private final ArrayList<String> listOfWords = new ArrayList();

    @Override
    public boolean place(String word) {
        words.put(word, words.size());
        wordsOrientations.add(MapDirection.NORTH);
        listOfWords.add(word);
        return true;
    }


    @Override
    public void move(String word, MoveDirection direction) {
        Integer wordIndex = words.get(word);
        MapDirection oldOrientation = wordsOrientations.get(wordIndex);
        switch (direction) {
            case RIGHT -> {
                wordsOrientations.set(wordIndex, oldOrientation.next());
            }
            case LEFT -> {
                wordsOrientations.set(wordIndex, oldOrientation.previous());
            }
            case FORWARD -> {
                moveIfCan(word, wordIndex,wordsOrientations.get(wordIndex), 1);
            }

            case BACKWARD -> {
                moveIfCan(word, wordIndex,wordsOrientations.get(wordIndex), -1);
            }
        }
    }


    private void moveIfCan(String word, Integer wordIndex,MapDirection wordOrientation, int direction) {
        if (wordOrientation== MapDirection.WEST) {
            direction*=-1;
        }
        if(wordOrientation==MapDirection.NORTH || wordOrientation==MapDirection.SOUTH) return;

        Integer swapIndex = wordIndex+direction;
        if (canMoveTo(swapIndex)) {
            String swapWord = listOfWords.get(swapIndex);
            Collections.swap(listOfWords, wordIndex, swapIndex);
            Collections.swap(wordsOrientations, wordIndex, swapIndex);
            words.remove(swapWord);
            words.remove(word);
            words.put(swapWord, wordIndex);
            words.put(word, swapIndex);
        }

    }
    @Override
    public boolean isOccupied(Integer index) {
        return words.size() > index && index > 0;
    }

    @Override
    public String objectAt(Integer index) {
        return listOfWords.get(index);
    }

    @Override
    public boolean canMoveTo(Integer index) {
        return index >= 0 && index < words.size();
    }

    @Override
    public String toString() {
        return listOfWords.toString();
    }
}
