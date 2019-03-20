package by.khomenko.training.task03.binexp;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class NumTest {

    @Test
    public void testInterpret() {
        Expression expression = new Num(4);
        int expected = 4;
        int actual = expression.interpret();
        assertEquals(actual, expected);
    }
}