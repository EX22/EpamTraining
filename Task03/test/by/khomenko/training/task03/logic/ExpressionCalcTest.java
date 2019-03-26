package by.khomenko.training.task03.logic;

import by.khomenko.training.task03.entity.BinaryExpression;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ExpressionCalcTest {

    @Test
    public void testCalculateBinaryExpression() {
        String expressionToCalculate = "(111^5|1&2<<(2|5>>2&71))|1200";
        WordExpressionSymbolParser parser = new WordExpressionSymbolParser();
        BinaryExpression binaryExpression = new BinaryExpression(parser.parseIt(expressionToCalculate));
        ExpressionCalc expressionCalc = new ExpressionCalc();
        Integer expected = 1274;
        Integer actual = expressionCalc.calculateBinaryExpression(binaryExpression);
        assertEquals(actual, expected);

    }
}
