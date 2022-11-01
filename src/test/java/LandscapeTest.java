import org.example.Landscape;
import org.example.exceptions.InvalidHillHeightException;
import org.example.exceptions.InvalidLandscapeLengthException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LandscapeTest {


    @Test
    public void calculateWaterAmount_edgeCases() {
        assertEquals(0, new Landscape(new int[]{}).calculateWaterAmount());
        assertEquals(0, new Landscape(new int[]{3}).calculateWaterAmount());
        assertEquals(0, new Landscape(new int[]{3, 5}).calculateWaterAmount());
    }

    @Test
    public void calculateWaterAmount_regular() {
        assertEquals(9, new Landscape(new int[]{5, 2, 3, 4, 5, 4, 0, 3, 1}).calculateWaterAmount());
        assertEquals(0, new Landscape(new int[]{1, 2, 3, 4, 5, 5, 4, 3, 2, 1, 1, 0}).calculateWaterAmount());
        assertEquals(10, new Landscape(new int[]{1, 2, 1, 0, 2, 1, 1, 4, 2, 1, 4, 0}).calculateWaterAmount());
        assertEquals(28, new Landscape(new int[]{7, 2, 1, 4, 2, 1, 4, 7}).calculateWaterAmount());
        assertEquals(12, new Landscape(new int[]{7, 1, 15, 1, 7, 6, 5, 4, 3, 2, 1, 0}).calculateWaterAmount());
        assertEquals(26, new Landscape(new int[]{4, 5, 0, 2, 3, 2, 1, 3, 9, 2, 7, 5, 4, 3, 2, 0, 0, 1}).calculateWaterAmount());
    }

    @Test(expected = InvalidLandscapeLengthException.class)
    public void calculateWaterAmount_invalidLandscapeSize_tooLarge() {
        new Landscape(new int[32001]).calculateWaterAmount();
    }

    @Test(expected = InvalidHillHeightException.class)
    public void calculateWaterAmount_invalidHillHeight_negative() {
        new Landscape(new int[]{5, 2, 3, -4, 5, 4, 0, -3, 1}).calculateWaterAmount();
    }

    @Test(expected = InvalidHillHeightException.class)
    public void calculateWaterAmount_invalidHillHeight_tooHigh() {
        new Landscape(new int[]{5, 2, 3, 4, 5, 4, 32001, 3, 1}).calculateWaterAmount();
    }

}