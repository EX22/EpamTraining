package by.khomenko.training.task03.binexp;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class RshTest {

    @Test
    public void testInterpret() {
        Expression expression = new Rsh(new Num(64), new Num(2));
        int expected = 64>>2;
        int actual = expression.interpret();
        assertEquals(actual, expected);
    }
}