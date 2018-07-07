package br.com.uniritter.tasima.arqweb.marsroverkata;

import br.com.uniritter.tasima.arqweb.marsroverkata.domain.TerrainModel;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/*
TerrainModel is a two-dimensional terrainModel on the grid.
New instance is created with initial location and maximum location that can be reached on that axis.
Methods forward/backward increase/decrease location.
If maximum location is reached, forward/backward methods wrap location.
*/
public class TerrainModelTest {

    TerrainModel terrainModel;
    private final int location = 5;
    private final int maxLocation = 9;

    @Before
    public void beforePointTest() {
        terrainModel = new TerrainModel(location, maxLocation);
    }

    @Test
    public void newInstanceShouldSetLocationAndMaxLocationParams() {
        assertThat(terrainModel.getLocation()).isEqualTo(location);
        assertThat(terrainModel.getMaxLocation()).isEqualTo(maxLocation);
    }

    @Test
    public void getForwardLocationShouldIncreasePointValueByOne() {
        int expected = terrainModel.getLocation() + 1;
        assertThat(terrainModel.getForwardLocation()).isEqualTo(expected);
    }

    @Test
    public void getBackwardLocationShouldDecreasePointValueByOne() {
        int expected = terrainModel.getLocation() - 1;
        assertThat(terrainModel.getBackwardLocation()).isEqualTo(expected);
    }

    @Test
    public void getForwardLocationShouldSetValueToZeroIfMaxLocationIsPassed() {
        terrainModel.setLocation(terrainModel.getMaxLocation());
        assertThat(terrainModel.getForwardLocation()).isZero();
    }

    @Test
    public void getBackwardLocationShouldSetValueToMaxLocationIfZeroLocationIsPassed() {
        terrainModel.setLocation(0);
        assertThat(terrainModel.getBackwardLocation()).isEqualTo(terrainModel.getMaxLocation());
    }

}