package by.khomenko.training.task01.logic;

import by.khomenko.training.task01.entity.Cone;
import by.khomenko.training.task01.entity.Vector;
import by.khomenko.training.task01.exception.ShapeException;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Contains test method for finding out whether the cone's base located
 * on coordinate plane.
 */
public class ShapeBasePositionTest {

    private Vector vector = new Vector(1.0, 2.0, 3.0);
    private Vector direction = new Vector(0.0, 0.0, 1.0);
    private Cone cone = new Cone(vector,9.0, 3.0, direction);
    private ShapeBasePosition shapeBasePosition = new ShapeBasePosition();

    /**
     * @throws ShapeException if passed cone's object is null.
     */
    @Test
    public void testIsConeBaseOnPosition() throws ShapeException {
        boolean actual = shapeBasePosition.isConeBaseOnPosition(cone);
        assertFalse(actual);
    }
}