package by.khomenko.training.task03.logic;

import by.khomenko.training.task03.entity.BinaryExpression;
import by.khomenko.training.task03.entity.TextComponent;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class WordExpressionSymbolParserTest {

    @Test
    public void testParseIt() {
        WordExpressionSymbolParser parser = new WordExpressionSymbolParser();
        String lineToParse = "remaining 30>>>3 essentially ~6&9|(3&4) unchanged.";
        List<TextComponent> list = parser.parseIt(lineToParse);
        assertTrue(list.get(1) instanceof BinaryExpression);
    }
}
