package by.khomenko.training.task03.binexp;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class NotTest {

    @Test
    public void testInterpret() {
        Expression expression = new Not(new Num(342));
        int expected = ~342;
        int actual = expression.interpret();
        assertEquals(actual, expected);

    }
}