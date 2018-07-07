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
    private TerrainModel x;
    private TerrainModel y;
    private List<ObstacleModel> obstacleModels;
    private final Compass compass = Compass.NORTH;

    @Before
    public void beforeCoordinatesTest() {
        x = new TerrainModel(1, 99);
        y = new TerrainModel(2, 99);
        obstacleModels = Arrays.asList(new ObstacleModel(20, 20), new ObstacleModel(30, 30));
        commandService = new CommandService(x, y, compass, obstacleModels);
    }

    @Test
    public void newInstanceShouldSetXAndYParams() {
        assertThat(commandService.getX()).isEqualToComparingFieldByField(x);
        assertThat(commandService.getY()).isEqualToComparingFieldByField(y);
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
        TerrainModel expected = new TerrainModel(y.getLocation() + 1, y.getMaxLocation());
        commandService.setCompass(Compass.NORTH);
        commandService.moveForward();
        assertThat(commandService.getY()).isEqualToComparingFieldByField(expected);
    }

    @Test
    public void moveForwardShouldIncreaseXWhenDirectionIsEast() {
        TerrainModel expected = new TerrainModel(x.getLocation() + 1, x.getMaxLocation());
        commandService.setCompass(Compass.EAST);
        commandService.moveForward();
        assertThat(commandService.getX()).isEqualToComparingFieldByField(expected);
    }

    @Test
    public void moveForwardShouldDecreaseYWhenDirectionIsSouth() {
        TerrainModel expected = new TerrainModel(y.getLocation() - 1, y.getMaxLocation());
        commandService.setCompass(Compass.SOUTH);
        commandService.moveForward();
        assertThat(commandService.getY()).isEqualToComparingFieldByField(expected);
    }

    @Test
    public void moveForwardShouldDecreaseXWhenDirectionIsWest() {
        TerrainModel expected = new TerrainModel(x.getLocation() - 1, x.getMaxLocation());
        commandService.setCompass(Compass.WEST);
        commandService.moveForward();
        assertThat(commandService.getX()).isEqualToComparingFieldByField(expected);
    }

    @Test
    public void moveForwardShouldNotChangeLocationsWhenObstacleIsFound() {
        int expected = x.getLocation();
        commandService.setCompass(Compass.EAST);
        commandService.setObstacleModels(Arrays.asList(new ObstacleModel(x.getLocation() + 1, y.getLocation())));
        commandService.move(commandService.getCompass());
        assertThat(commandService.getX().getLocation()).isEqualTo(expected);
    }

    @Test
    public void moveBackwardShouldDecreaseYWhenDirectionIsNorth() {
        TerrainModel expected = new TerrainModel(y.getLocation() - 1, y.getMaxLocation());
        commandService.setCompass(Compass.NORTH);
        commandService.moveBackward();
        assertThat(commandService.getY()).isEqualToComparingFieldByField(expected);
    }

    @Test
    public void moveBackwardShouldDecreaseXWhenDirectionIsEast() {
        TerrainModel expected = new TerrainModel(x.getLocation() - 1, x.getMaxLocation());
        commandService.setCompass(Compass.EAST);
        commandService.moveBackward();
        assertThat(commandService.getX()).isEqualToComparingFieldByField(expected);
    }

    @Test
    public void moveBackwardShouldIncreaseYWhenDirectionIsSouth() {
        TerrainModel expected = new TerrainModel(y.getLocation() + 1, y.getMaxLocation());
        commandService.setCompass(Compass.SOUTH);
        commandService.moveBackward();
        assertThat(commandService.getY()).isEqualToComparingFieldByField(expected);
    }

    @Test
    public void moveBackwardShouldIncreaseXWhenDirectionIsWest() {
        TerrainModel expected = new TerrainModel(x.getLocation() + 1, x.getMaxLocation());
        commandService.setCompass(Compass.WEST);
        commandService.moveBackward();
        assertThat(commandService.getX()).isEqualToComparingFieldByField(expected);
    }

    @Test
    public void toStringShouldReturnXAndY() {
        String expected = x.getLocation() + " X " + y.getLocation() + " " + compass.getShortName();
        assertThat(commandService.toString()).isEqualTo(expected);
    }

}