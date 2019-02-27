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
    private Cone cone = new Cone(vector,9.0, 3.0, direction, "TestName" );

    //TODO Remove Data Provider example.
    @DataProvider(name = "input_a_b")
    public Object [][] createCorrectData(){
        return
                new Object[][]{
                        {new int[]{2, 3}, 5},
                        {new int[]{1, 1}, 2},
                        {new int[]{0, 0}, 0},

                };
    }

    @Test(description = "Positive case scenario for the sum calculation", dataProvider = "input_a_b")
    public void testSum(int [] ab, int c) throws Exception{
        int actual = shapeVolume.sum(ab[0], ab[1]);
        int expected = c;
        assertEquals(actual, expected);
    }

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