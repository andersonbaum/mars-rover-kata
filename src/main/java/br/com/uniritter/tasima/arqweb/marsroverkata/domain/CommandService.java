package br.com.uniritter.tasima.arqweb.marsroverkata.domain;

import br.com.uniritter.tasima.arqweb.marsroverkata.enums.Compass;

import java.util.List;

public class CommandService {

    private TerrainModel x;
    public void setX(TerrainModel value) { x = value; }
    public TerrainModel getX() { return x; }

    private TerrainModel y;
    public void setY(TerrainModel value) { y = value; }
    public TerrainModel getY() { return y; }

    private Compass compass;
    public void setCompass(Compass value) {
        compass = value;
    }
    public Compass getCompass() {
        return compass;
    }

    private List<ObstacleModel> obstacleModels;
    public void setObstacleModels(List<ObstacleModel> value) {
        obstacleModels = value;
    }
    public List<ObstacleModel> getObstacleModels() {
        return obstacleModels;
    }

    private boolean foundObstacle = false;

    public CommandService(TerrainModel xValue,
                          TerrainModel yValue,
                          Compass compassValue,
                          List<ObstacleModel> obstaclesValue) {
        setX(xValue);
        setY(yValue);
        setCompass(compassValue);
        setObstacleModels(obstaclesValue);
    }

    public boolean move(Compass compassValue) {
        int xLocation = x.getLocation();
        int yLocation = y.getLocation();
        switch (compassValue) {
            case NORTH:
                yLocation = y.getForwardLocation();
                break;
            case EAST:
                xLocation = x.getForwardLocation();
                break;
            case SOUTH:
                yLocation = y.getBackwardLocation();
                break;
            case WEST:
                xLocation = x.getBackwardLocation();
                break;
        }
        if (!hasObstacle(xLocation, yLocation)) {
            x.setLocation(xLocation);
            y.setLocation(yLocation);
            return true;
        } else {
            return false;
        }
    }

    private boolean hasObstacle(int xLocation, int yLocation) {
        for (ObstacleModel obstacleModel : obstacleModels) {
            if (obstacleModel.getX() == xLocation && obstacleModel.getY() == yLocation) {
                foundObstacle = true;
                return true;
            }
        }
        return false;
    }

    public boolean moveForward() {
        return move(compass);
    }

    public boolean moveBackward() {
        return move(compass.getBackwardDirection());
    }

    private void changeDirection(Compass compassValue, int directionStep) {
        int directions = Compass.values().length;
        int index = (directions + compassValue.getValue() + directionStep) % directions;
        compass = Compass.values()[index];
    }

    public void changeDirectionLeft() {
        changeDirection(compass, -1);
    }

    public void changeDirectionRight() {
        changeDirection(compass, 1);
    }

    @Override
    public String toString() {
        String status = "";
        if (foundObstacle) {
            status = " NOK";
        }
        return getX().getLocation() + " X " + getY().getLocation() + " " + getCompass().getShortName() + status;
    }

}
