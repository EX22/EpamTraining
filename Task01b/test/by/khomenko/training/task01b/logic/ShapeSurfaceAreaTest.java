package by.khomenko.training.task01b.logic;

import by.khomenko.training.task01b.entity.Cone;
import by.khomenko.training.task01b.entity.Plane;
import by.khomenko.training.task01b.entity.Vector;
import by.khomenko.training.task01b.logic.ShapeSurfaceArea;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ShapeSurfaceAreaTest {

    private ShapeSurfaceArea shapeSurfaceArea = new ShapeSurfaceArea();
    private Vector vector = new Vector(1.0, 2.0, 3.0);
    private Vector direction = new Vector(0.0, 0.0, 1.0);
    private Cone cone = new Cone(vector,9.0, 3.0, direction,
            "TestSurfaceAreaName" );


    @Test
    public void testCalcConeSurfaceArea() {
        double actual = shapeSurfaceArea.calcConeSurfaceArea(cone);
        double expected = 117.68562827447305;
        assertEquals(actual, expected);
    }
}