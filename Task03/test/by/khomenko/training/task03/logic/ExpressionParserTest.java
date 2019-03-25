package by.khomenko.training.task03.logic;

import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class ExpressionParserTest {

    @Test
    public void testParseBinExp() {

        ExpressionParser expressionParser = new ExpressionParser();
        String binaryExpressionString = "30>>>3";
        List<String> resultedList = expressionParser.parseBinExp(binaryExpressionString);
        String expected = "[30, 3, >>>]";
        String actual = resultedList.toString();
        assertEquals(actual, expected);
    }
}
