package br.com.uniritter.tasima.arqweb.marsroverkata.domain;

public class TerrainModel {

    private int location;
    public void setLocation(int value) { location = value; }
    public int getLocation() { return location; }

    private int maxLocation;
    public void setMaxLocation(int value) { maxLocation = value; }
    public int getMaxLocation() { return maxLocation; }

    public TerrainModel(int locationValue, int maxLocationValue) {
        setLocation(locationValue);
        setMaxLocation(maxLocationValue);
    }

    public int getForwardLocation() {
        return (getLocation() + 1) % (getMaxLocation() + 1);
    }

    public int getBackwardLocation() {
        if (getLocation() > 0) return getLocation() - 1;
        else return getMaxLocation();
    }

}