package by.khomenko.training.task03.logic;

import by.khomenko.training.task03.entity.TextComponent;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class TextParserTest {

    @Test
    public void testParseIt() {

        String text = "\tIt has survived - not only (five) centuries, but also the leap into 13<<2\n" +
                "electronic typesetting, remaining 30>>>3 essentially ~6&9|(3&4) unchanged. It was\n" +
                "popularised in the 5|(1&2&(3|(4&(25^5|6&47)|3)|2)|1) with the release of Letraset\n" +
                "sheets containing Lorem Ipsum passages, and more recently with desktop publishing\n" +
                "software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                "\tIt is a long established fact that a reader will be distracted by the readable\n" +
                "content of a page when looking at its layout. The point of using\n" +
                "(~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78 Ipsum is that it has a more-or-less normal\n" +
                "distribution of letters, as opposed to using (Content here), content here', making it look\n" +
                "like readable English.\n" +
                "\tIt is a (111^5|1&2<<(2|5>>2&71))|1200 established fact that a reader will be of a\n" +
                "page when looking at its layout.\n" +
                "\tBye.";

        CommonParser textParser = new TextParser();
        CommonParser paragraphParser = new ParagraphParser();
        CommonParser sentenceParser = new SentenceParser();
        CommonParser lexemeParser = new LexemeParser();
        textParser.setNextCommonParser(paragraphParser);
        paragraphParser.setNextCommonParser(sentenceParser);
        sentenceParser.setNextCommonParser(lexemeParser);

        List<TextComponent> textList = textParser.parseIt(text);
        //TODO Add assertion here!
    }
}