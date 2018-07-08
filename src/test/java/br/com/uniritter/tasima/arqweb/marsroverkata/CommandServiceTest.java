package br.com.uniritter.tasima.arqweb.marsroverkata;

import br.com.uniritter.tasima.arqweb.marsroverkata.enums.Compass;
import br.com.uniritter.tasima.arqweb.marsroverkata.domain.CommandService;
import br.com.uniritter.tasima.arqweb.marsroverkata.domain.ObstacleModel;
import br.com.uniritter.tasima.arqweb.marsroverkata.domain.TerrainModel;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CommandServiceTest {

    private CommandService commandService;
    private TerrainModel xPosition;
    private TerrainModel yPosition;
    private List<ObstacleModel> obstacleModels;
    private final Compass compass = Compass.NORTH;

    @Before
    public void setTest() {
        xPosition = new TerrainModel(1, 11);
        yPosition = new TerrainModel(1, 11);
        obstacleModels = Arrays.asList(new ObstacleModel(3, 3), new ObstacleModel(6, 4));
        commandService = new CommandService(xPosition, yPosition, compass, obstacleModels);
    }

    @Test
    public void newInstanceShouldSetXAndYParams() {
        assertThat(commandService.getX()).isEqualToComparingFieldByField(xPosition);
        assertThat(commandService.getY()).isEqualToComparingFieldByField(yPosition);
    }

    @Test
    public void newInstanceShouldSetDirection() {
        assertThat(commandService.getCompass()).isEqualTo(compass);
    }

    @Test
    public void newInstanceShouldSetObstacles() {
        assertThat(commandService.getObstacleModels()).hasSameElementsAs(obstacleModels);
    }

    @Test
    public void moveForwardShouldIncreaseYWhenDirectionIsNorth() {
        TerrainModel expected = new TerrainModel(yPosition.getLocation() + 1, yPosition.getMaxLocation());
        commandService.setCompass(Compass.NORTH);
        commandService.moveForward();
        assertThat(commandService.getY()).isEqualToComparingFieldByField(expected);
    }

    @Test
    public void moveForwardShouldIncreaseXWhenDirectionIsEast() {
        TerrainModel expected = new TerrainModel(xPosition.getLocation() + 1, xPosition.getMaxLocation());
        commandService.setCompass(Compass.EAST);
        commandService.moveForward();
        assertThat(commandService.getX()).isEqualToComparingFieldByField(expected);
    }

    @Test
    public void moveForwardShouldDecreaseYWhenDirectionIsSouth() {
        TerrainModel expected = new TerrainModel(yPosition.getLocation() - 1, yPosition.getMaxLocation());
        commandService.setCompass(Compass.SOUTH);
        commandService.moveForward();
        assertThat(commandService.getY()).isEqualToComparingFieldByField(expected);
    }

    @Test
    public void moveForwardShouldDecreaseXWhenDirectionIsWest() {
        TerrainModel expected = new TerrainModel(xPosition.getLocation() - 1, xPosition.getMaxLocation());
        commandService.setCompass(Compass.WEST);
        commandService.moveForward();
        assertThat(commandService.getX()).isEqualToComparingFieldByField(expected);
    }

    @Test
    public void moveForwardShouldNotChangeLocationsWhenObstacleIsFound() {
        int expected = xPosition.getLocation();
        commandService.setCompass(Compass.EAST);
        commandService.setObstacleModels(Arrays.asList(new ObstacleModel(xPosition.getLocation() + 1, yPosition.getLocation())));
        commandService.move(commandService.getCompass());
        assertThat(commandService.getX().getLocation()).isEqualTo(expected);
    }

    @Test
    public void moveBackwardShouldDecreaseYWhenDirectionIsNorth() {
        TerrainModel expected = new TerrainModel(yPosition.getLocation() - 1, yPosition.getMaxLocation());
        commandService.setCompass(Compass.NORTH);
        commandService.moveBackward();
        assertThat(commandService.getY()).isEqualToComparingFieldByField(expected);
    }

    @Test
    public void moveBackwardShouldDecreaseXWhenDirectionIsEast() {
        TerrainModel expected = new TerrainModel(xPosition.getLocation() - 1, xPosition.getMaxLocation());
        commandService.setCompass(Compass.EAST);
        commandService.moveBackward();
        assertThat(commandService.getX()).isEqualToComparingFieldByField(expected);
    }

    @Test
    public void moveBackwardShouldIncreaseYWhenDirectionIsSouth() {
        TerrainModel expected = new TerrainModel(yPosition.getLocation() + 1, yPosition.getMaxLocation());
        commandService.setCompass(Compass.SOUTH);
        commandService.moveBackward();
        assertThat(commandService.getY()).isEqualToComparingFieldByField(expected);
    }

    @Test
    public void moveBackwardShouldIncreaseXWhenDirectionIsWest() {
        TerrainModel expected = new TerrainModel(xPosition.getLocation() + 1, xPosition.getMaxLocation());
        commandService.setCompass(Compass.WEST);
        commandService.moveBackward();
        assertThat(commandService.getX()).isEqualToComparingFieldByField(expected);
    }

    @Test
    public void toStringShouldReturnXAndY() {
        String expected = xPosition.getLocation() + " X " + yPosition.getLocation() + " " + compass.getShortName();
        assertThat(commandService.toString()).isEqualTo(expected);
    }

}