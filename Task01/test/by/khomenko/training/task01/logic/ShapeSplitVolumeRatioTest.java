package by.khomenko.training.task01.logic;

import by.khomenko.training.task01.entity.Cone;
import by.khomenko.training.task01.entity.Plane;
import by.khomenko.training.task01.entity.Vector;
import by.khomenko.training.task01.exception.ShapeException;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ShapeSplitVolumeRatioTest {


    private Vector vectorCone = new Vector(1.0, 2.0, 3.0);
    private Vector directionCone = new Vector(0.0, 0.0, 1.0);
    private Vector vectorPlane = new Vector(2.0, 1.0, 5.0);
    private Vector normalPlane = new Vector(0.0, 0.0, 1.0);
    private Cone cone = new Cone(vectorCone, 9.0, 3.0,
            directionCone);
    private ShapeSplitVolumeRatio shapeSplitVolumeRatio
            = new ShapeSplitVolumeRatio();
    private Plane cuttingPlane = new Plane(vectorPlane, normalPlane);

    @Test
    public void testCalcConeSplitVolumeRation() throws ShapeException {
        double actual = shapeSplitVolumeRatio.calcConeSplitVolumeRation(cone,
                cuttingPlane);
        double expected = 0.88860103626943;
        assertEquals(actual, expected);
    }
}