package by.khomenko.training.task03.logic;

import by.khomenko.training.task03.entity.TextComponent;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class LexemeParserTest {

    @Test
    public void testParseIt() {

        String sentenceExample = "Lexeme lexemeOne, lexemeTwo extraLexeme.";

        CommonParser lexemeParser = new LexemeParser();
        List<TextComponent> list = lexemeParser.parseIt(sentenceExample);
        String expected = "lexemeTwo";
        String actual = list.get(2).getValue();
        assertEquals(actual, expected);
    }
}
