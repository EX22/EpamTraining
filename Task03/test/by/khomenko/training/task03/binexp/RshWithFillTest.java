package by.khomenko.training.task03.binexp;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class RshWithFillTest {

    @Test
    public void testInterpret() {
        Expression expression = new RshWithFill(new Num(-1), new Num(24));
        int expected = -1>>>24;
        int actual = expression.interpret();
        assertEquals(actual, expected);
    }
}