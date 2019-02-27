package by.khomenko.training.task01b.repository;

import by.khomenko.training.task01b.comporator.ShapeByFirstXCoordinate;
import by.khomenko.training.task01b.entity.Cone;
import by.khomenko.training.task01b.entity.DefaultShape;
import by.khomenko.training.task01b.entity.Vector;
import by.khomenko.training.task01b.logic.GeometryUtil;
import by.khomenko.training.task01b.logic.ShapeSurfaceArea;
import by.khomenko.training.task01b.logic.ShapeVolume;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

/**
 * Test ShapeRepository's methods.
 */
public class ShapeRepositoryTest {

    private Vector vector = new Vector(1.0, 2.0, 3.0);
    private Vector direction = new Vector(0.0, 0.0, 1.0);
    private Cone cone = new Cone(vector, 9.0, 3.0, direction, "TestRepositoryName");
    private ShapeRepository shapeRepository = new ShapeRepository();


    /**
     * Positive case scenario for AddShape method.
     */
    @Test(description = "positive case scenario for AddShape method")
    public void testAddShape() {
        shapeRepository.addShape(cone);
        assertNotNull(shapeRepository.searchShapeByID(cone.getId()));
    }

    @Test
    public void testRemoveShape() {
        shapeRepository.addShape(cone);
        shapeRepository.removeShape(cone);
        assertNull(shapeRepository.searchShapeByID(cone.getId()));

    }


    @Test
    public void testUpdateShape() {
        shapeRepository.addShape(cone);
        Vector vector = new Vector(3.0, 2.0, 1.0);
        Vector direction = new Vector(1.0, 0.0, 0.0);
        Cone coneUpdated = new Cone(vector, 7.0, 5.0, direction, "TestUpdateShapeName");
        coneUpdated.setId(cone.getId());
        shapeRepository.updateShape(coneUpdated);
        assertFalse(shapeRepository.searchShapeByName(coneUpdated.getName()).isEmpty());

    }


    @Test
    public void testSearchShapeByID() {
        shapeRepository.addShape(cone);
        DefaultShape foundShape = shapeRepository.searchShapeByID(cone.getId());
        assertEquals(foundShape, cone);

    }

    @Test
    public void testSearchShapeByName() {
        shapeRepository.addShape(cone);
        List<DefaultShape> foundShapeList = shapeRepository.searchShapeByName(cone.getName());
        assertTrue(foundShapeList.contains(cone));
    }

    @DataProvider(name = "coneInOctant")
    public Object[][] createConeInOctant() {
        Vector vector = new Vector(4.0, 3.0, 1.0);
        Vector direction = new Vector(0.0, 1.0, 0.0);
        Cone coneInOctant = new Cone(vector, 5.0, 2.0, direction, "TestShapeByCoordinateName");
        Vector vector1 = new Vector(-4.0, -3.0, -1.0);
        Vector direction1 = new Vector(0.0, 1.0, 0.0);
        Cone coneNotInOctant = new Cone(vector1, 5.0, 2.0, direction1, "TestShapeByCoordinateName2");
        return
                new Object[][]{
                        {coneInOctant, coneNotInOctant}
                };
    }

    /**
     * Uses data provider coneInOctant.
     * @param coneInOctant instance of cone from the first octant.
     * @param coneNotInOctant instance of cone not from the first octant
     */
    @Test(description = "Positive case scenario for searchShapeByCoordinateInOctant", dataProvider = "coneInOctant")
    public void testSearchShapeByCoordinateInOctant(Cone coneInOctant, Cone coneNotInOctant) {
        shapeRepository.addShape(coneInOctant);
        List<DefaultShape> foundShapeList = shapeRepository.searchShapeByCoordinateInOctant();
        assertTrue(foundShapeList.contains(coneInOctant) && !foundShapeList.contains(coneNotInOctant));
    }
    //TODO To test negative case scenario.
    @Test
    public void testSearchShapeBySurfaceAreaInRange() {
        shapeRepository.addShape(cone);
        ShapeSurfaceArea shapeSurfaceArea = new ShapeSurfaceArea();
        Double actualConeSurfaceArea = shapeSurfaceArea.calcConeSurfaceArea(cone);
        Double surfaceAreaRange = 55.5;
        List<DefaultShape> foundShapeList = shapeRepository.searchShapeBySurfaceAreaInRange(actualConeSurfaceArea
                - surfaceAreaRange, actualConeSurfaceArea + surfaceAreaRange);
        assertTrue(foundShapeList.contains(cone));
    }
    //TODO To test negative case scenario.
    @Test
    public void testSearchShapeByVolumeInRange() {
        shapeRepository.addShape(cone);
        ShapeVolume shapeVolume = new ShapeVolume();
        Double actualConeVolume = shapeVolume.calcConeVolume(cone);
        Double volumeRange = 22.2;
        List<DefaultShape> foundShapeList = shapeRepository.searchShapeByVolumeInRange(actualConeVolume
                - volumeRange, actualConeVolume + volumeRange);
        assertTrue(foundShapeList.contains(cone));
    }

    @Test
    public void testSearchShapeByDistanceFromOriginInRange() {
        shapeRepository.addShape(cone);
        Double actualDistance = GeometryUtil.vectorLength(cone.getBaseCenter());
        Double distanceRange = 11.1;
        List<DefaultShape> foundShapeList = shapeRepository.searchShapeByDistanceFromOriginInRange(actualDistance
                - distanceRange, actualDistance + distanceRange);
        assertTrue(foundShapeList.contains(cone));
    }

    //TODO Create data provider for this test method.
    @Test
    public void testSortShape() {
        Vector vectorFirst = new Vector(3.3, 2.2, 1.1);
        Vector directionFirst = new Vector(1.0, 0.0, 0.0);
        Cone coneFirst = new Cone(vectorFirst, 7.7, 5.5, directionFirst, "TestSortShapeFirst");

        Vector vectorSecond = new Vector(2.2, 5.5, 6.6);
        Vector directionSecond = new Vector(0.0, 1.0, 0.0);
        Cone coneSecond = new Cone(vectorSecond, 4.4, 3.3, directionSecond, "TestSortShapeSecond");

        Vector vectorThird = new Vector(9.9, 2.9, 8.9);
        Vector directionThird = new Vector(0.0, 0.0, 1.0);
        Cone coneThird = new Cone(vectorThird, 3.9, 2.9, directionThird, "TestSortShapeThird");

        Vector vectorFourth = new Vector(2.1, 3.1, 4.1);
        Vector directionFourth = new Vector(0.0, 1.0, 0.0);
        Cone coneFourth = new Cone(vectorFourth, 4.0, 3.4, directionFourth, "TestSortShapeFourth");

        shapeRepository.addShape(coneFirst);
        shapeRepository.addShape(coneSecond);
        shapeRepository.addShape(coneThird);
        shapeRepository.addShape(coneFourth);

        List<DefaultShape> sortedList = shapeRepository.sortShape(new ShapeByFirstXCoordinate());
        Cone coneShapeOne = (Cone) sortedList.get(1);
        Cone coneShapeTwo = (Cone) sortedList.get(2);
        assertTrue(coneShapeOne.getBaseCenter().getX() <= coneShapeTwo.getBaseCenter().getX());

    }
}