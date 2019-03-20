package by.khomenko.training.task03.binexp;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class OrTest {

    @Test
    public void testInterpret() {
        Expression expression = new Or(new Num(277), new Num(432));
        int expected = 277|432;
        int actual = expression.interpret();
        assertEquals(actual, expected);
    }
}