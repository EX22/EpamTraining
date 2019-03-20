package by.khomenko.training.task03.binexp;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LshTest {

    @Test
    public void testInterpret() {
        Expression expression = new Lsh(new Num(64), new Num(3));
        int expected = 64<<3;
        int actual = expression.interpret();
        assertEquals(actual, expected);
    }
}