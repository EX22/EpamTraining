package by.khomenko.training.task01b.logic;

import by.khomenko.training.task01b.entity.Cone;
import by.khomenko.training.task01b.entity.DefaultShape;
import by.khomenko.training.task01b.entity.Vector;
import by.khomenko.training.task01b.exception.ShapeException;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ShapeTypeTest {

    private Vector vector = new Vector(1.0, 2.0, 3.0);
    private Vector direction = new Vector(0.0, 0.0, 1.0);
    private Cone coneTypeTest = new Cone(vector,9.0, 3.0,
            direction, "TestShapeTypeName" );
    private ShapeType shapeType = new ShapeType();

    @Test
    public void testShapeTypeDetector() throws ShapeException {
        assertTrue(shapeType.shapeTypeDetector(coneTypeTest));
    }

}