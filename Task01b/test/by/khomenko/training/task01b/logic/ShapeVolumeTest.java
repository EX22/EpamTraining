package by.khomenko.training.task01b.logic;

import by.khomenko.training.task01b.entity.Cone;
import by.khomenko.training.task01b.entity.Vector;
import by.khomenko.training.task01b.logic.ShapeVolume;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ShapeVolumeTest {

    private ShapeVolume shapeVolume = new ShapeVolume();
    private Vector vector = new Vector(1.0, 2.0, 3.0);
    private Vector direction = new Vector(0.0, 0.0, 1.0);
    private Cone cone = new Cone(vector,9.0, 3.0, direction,
            "TestVolumeName" );

    @Test
    public void testCalcConeVolume() {
        double actual = shapeVolume.calcConeVolume(cone);
        double expected = 84.82300164692441;
        assertEquals(actual, expected);
    }

    @Test
    public void testCalcFrustumVolume() {
        double actual = shapeVolume.calcFrustumVolume(2.0, 3.0, 1.0);
        double expected = 19.896753472735355;
        assertEquals(actual, expected);
    }
}