package by.khomenko.training.task03.logic;

import by.khomenko.training.task03.entity.TextComponent;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class SymbolParserTest {

    @Test
    public void testParseIt() {

        String testLine = "test line";

        SymbolParser symbolParser = new SymbolParser();
        List<TextComponent> list = symbolParser.parseIt(testLine);
        String expected = "s";
        String actual = list.get(2).getValue();
        assertEquals(actual, expected);

    }
}
