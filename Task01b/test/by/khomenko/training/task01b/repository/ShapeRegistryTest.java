package by.khomenko.training.task01b.repository;

import by.khomenko.training.task01b.entity.Cone;
import by.khomenko.training.task01b.entity.Vector;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ShapeRegistryTest {

    private Vector vector = new Vector(1.0, 2.0, 3.0);
    private Vector direction = new Vector(0.0, 0.0, 1.0);
    private Cone cone = new Cone(vector, 9.0, 3.0, direction, "TestRegistryName");
    private ShapeRegistry shapeRegistry = new ShapeRegistry();

    @Test
    public void testUpdate() {
        cone.setId(12345);
        cone.addObserver(shapeRegistry);
        cone.setRadius(5.0);
        cone.notifyObservers();
        double actual = shapeRegistry.getShapeSurfaceArea(12345);
        double expected = 240.26319641475686;
        assertEquals(actual, expected);

    }
}