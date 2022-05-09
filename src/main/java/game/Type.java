package game;

import java.util.Arrays;

public enum Type {
    SHEEP("S", new Pair<>(0, 0)), WOLF1("W1", new Pair<>(0, 0)),
    WOLF2("W2", new Pair<>(0, 0)), WOLF3("W3", new Pair<>(0, 0)),
    WOLF4("W4", new Pair<>(0, 0)), WALL("WA", new Pair<>(0, 0));

    private final String name;
    private Pair<Integer, Integer> coordinate;

    Type(String name, Pair<Integer, Integer> coordinate) {
        this.coordinate = coordinate;
        this.name = name;
    }

    public Pair<Integer, Integer> setCoordinate(Pair<Integer,Integer> coordinate) {
       return this.coordinate = coordinate;
    }

    public void setX(int x) {
        this.coordinate.setSecond(x);
    }

    public void setY(int y) {
        this.coordinate.setFirst(y);
    }

    public int getY() {
        return this.coordinate.getFirst();
    }

    public int getX() {
        return this.coordinate.getSecond();
    }

    public String getName() {
        return name;
    }

}