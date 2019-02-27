package by.khomenko.training.task01b.logic;

import by.khomenko.training.task01b.entity.Cone;
import by.khomenko.training.task01b.entity.Vector;
import by.khomenko.training.task01b.exception.ShapeException;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ShapeBasePositionTest {

    private Vector vector = new Vector(1.0, 2.0, 3.0);
    private Vector direction = new Vector(0.0, 0.0, 1.0);
    private Cone cone = new Cone(vector,9.0, 3.0, direction, "TestBasePositionName" );
    private ShapeBasePosition shapeBasePosition = new ShapeBasePosition();

    @Test
    public void testIsConeBaseOnPosition() throws ShapeException {
        boolean actual = shapeBasePosition.isConeBaseOnPosition(cone);
        assertFalse(actual);
    }
}