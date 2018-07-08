package br.com.uniritter.tasima.arqweb.marsroverkata;

import br.com.uniritter.tasima.arqweb.marsroverkata.application.Rover;
import br.com.uniritter.tasima.arqweb.marsroverkata.domain.CommandService;
import br.com.uniritter.tasima.arqweb.marsroverkata.enums.Compass;
import br.com.uniritter.tasima.arqweb.marsroverkata.domain.ObstacleModel;
import br.com.uniritter.tasima.arqweb.marsroverkata.domain.TerrainModel;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RoverTest {

    private Rover rover;
    private CommandService roverCommandService;
    private final Compass compass = Compass.NORTH;
    private TerrainModel x;
    private TerrainModel y;
    private List<ObstacleModel> obstacleModels;

    @Before
    public void beforeRoverTest() {
        x = new TerrainModel(1, 11);
        y = new TerrainModel(1, 11);
        obstacleModels = new ArrayList<ObstacleModel>();
        roverCommandService = new CommandService(x, y, compass, obstacleModels);
        rover = new Rover(roverCommandService);
    }

    @Test
    public void newInstanceShouldSetRoverCoordinatesAndDirection() {
        assertThat(rover.getCommandService()).isEqualToComparingFieldByField(roverCommandService);
    }

    @Test
    public void receiveSingleCommandShouldMoveForwardWhenCommandIsF() throws Exception {
        int expected = y.getLocation() + 1;
        rover.receiveSingleCommand('F');
        assertThat(rover.getCommandService().getY().getLocation()).isEqualTo(expected);
    }

    @Test
    public void receiveSingleCommandShouldMoveBackwardWhenCommandIsB() throws Exception {
        int expected = y.getLocation() - 1;
        rover.receiveSingleCommand('B');
        assertThat(rover.getCommandService().getY().getLocation()).isEqualTo(expected);
    }

    @Test
    public void receiveSingleCommandShouldTurnLeftWhenCommandIsL() throws Exception {
        rover.receiveSingleCommand('L');
        assertThat(rover.getCommandService().getCompass()).isEqualTo(Compass.WEST);
    }

    @Test
    public void receiveSingleCommandShouldTurnRightWhenCommandIsR() throws Exception {
        rover.receiveSingleCommand('R');
        assertThat(rover.getCommandService().getCompass()).isEqualTo(Compass.EAST);
    }

    @Test
    public void receiveSingleCommandShouldIgnoreCase() throws Exception {
        rover.receiveSingleCommand('r');
        assertThat(rover.getCommandService().getCompass()).isEqualTo(Compass.EAST);
    }

    @Test(expected = Exception.class)
    public void receiveSingleCommandShouldThrowExceptionWhenCommandIsUnknown() throws Exception {
        rover.receiveSingleCommand('X');
    }

    @Test
    public void receiveCommandsShouldBeAbleToReceiveMultipleCommands() throws Exception {
        int expected = x.getLocation() + 1;
        rover.receiveCommands("RFR");
        assertThat(rover.getCommandService().getX().getLocation()).isEqualTo(expected);
        assertThat(rover.getCommandService().getCompass()).isEqualTo(Compass.SOUTH);
    }

    @Test
    public void receiveCommandShouldWhatFromOneEdgeOfTheGridToAnother() throws Exception {
        int expected = x.getMaxLocation() + x.getLocation() - 2;
        rover.receiveCommands("LFFF");
        assertThat(rover.getCommandService().getX().getLocation()).isEqualTo(expected);
    }

    @Test
    public void receiveCommandsShouldStopWhenObstacleIsFound() throws Exception {
        int expected = x.getLocation() + 1;
        rover.getCommandService().setObstacleModels(Arrays.asList(new ObstacleModel(expected + 1, y.getLocation())));
        rover.getCommandService().setCompass(Compass.EAST);
        rover.receiveCommands("FFFRF");
        assertThat(rover.getCommandService().getX().getLocation()).isEqualTo(expected);
        assertThat(rover.getCommandService().getCompass()).isEqualTo(Compass.EAST);
    }

    @Test
    public void positionShouldReturnXYAndDirection() throws Exception {
        rover.receiveCommands("LFFFRFF");
        assertThat(rover.getPosition()).isEqualTo("10 X 3 N");
    }

    @Test
    public void positionShouldReturnNokWhenObstacleIsFound() throws Exception {
        rover.getCommandService().setObstacleModels(Arrays.asList(new ObstacleModel(x.getLocation() + 1, y.getLocation())));
        rover.getCommandService().setCompass(Compass.EAST);
        rover.receiveCommands("F");
        assertThat(rover.getPosition()).endsWith(" NOK");
    }

}