package br.com.uniritter.tasima.arqweb.marsroverkata;

import br.com.uniritter.tasima.arqweb.marsroverkata.domain.TerrainModel;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TerrainModelTest {

    TerrainModel terrainModel;
    private final int location = 5;
    private final int maxLocation = 11;

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