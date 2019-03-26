package by.khomenko.training.task03.logic;

import by.khomenko.training.task03.entity.TextComponent;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class SentenceParserTest {

    @Test
    public void testParseIt() {
        String paragraphExample = "\tIt has survived - not only (five) "
                + "centuries, but also the leap into 13<<2 electronic "
                + "typesetting, remaining 30>>>3 essentially ~6&9|(3&4) "
                + "unchanged. It was popularised in the "
                + "5|(1&2&(3|(4&(25^5|6&47)|3)|2)|1) with the release "
                + "of Letraset sheets containing Lorem Ipsum passages, "
                + "and more recently with desktop publishing software like "
                + "Aldus PageMaker including versions of Lorem Ipsum.";

        CommonParser sentenceParser = new SentenceParser();
        CommonParser lexemeParser = new LexemeParser();
        sentenceParser.setNextCommonParser(lexemeParser);
        List<TextComponent> textComponentList = sentenceParser.parseIt(paragraphExample);
        String sentenceExample = textComponentList.get(0).getValue();
        List<TextComponent> list = lexemeParser.parseIt(sentenceExample);
        String expected = "centuries,";
        String actual = list.get(7).getValue();
        assertEquals(actual, expected);
    }
}
