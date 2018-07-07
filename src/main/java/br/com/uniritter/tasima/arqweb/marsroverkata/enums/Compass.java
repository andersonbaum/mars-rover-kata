package br.com.uniritter.tasima.arqweb.marsroverkata.enums;

public enum Compass {

    NORTH(0, 'N'),
    EAST(1, 'E'),
    SOUTH(2, 'S'),
    WEST(3, 'W');

    private int value;
    private char shortName;
    private Compass(int newValue, char shortNameValue) {
        value = newValue;
        shortName = shortNameValue;
    }

    public Compass getBackwardDirection() {
        return values()[(this.getValue() + 2) % 4];
    }

    public int getValue() {
        return value;
    }

    public char getShortName() { return shortName; }

}