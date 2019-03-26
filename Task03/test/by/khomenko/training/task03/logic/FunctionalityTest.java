package by.khomenko.training.task03.logic;

import by.khomenko.training.task03.entity.Paragraph;
import by.khomenko.training.task03.entity.Sentence;
import by.khomenko.training.task03.entity.Text;
import by.khomenko.training.task03.entity.TextComponent;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class FunctionalityTest {


    private String text = "\tIt has survived - not only (five) centuries, but also the leap into 13<<2\n" +
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

    private Functionality functionality = new Functionality();
    private TextParser textParser = new TextParser();

    @Test
    public void testSortParagraphBySentencesAmount() {

        String expectedTextAfterSort = "    It is a (111^5|1&2<<(2|5>>2&71))|1200 established fact that a reader will be of a\n" +
                " page when looking at its layout.\n" +
                "    Bye.\n" +
                "    It has survived - not only (five) centuries, but also the leap into 13<<2\n" +
                " electronic typesetting, remaining 30>>>3 essentially ~6&9|(3&4) unchanged. It was\n" +
                " popularised in the 5|(1&2&(3|(4&(25^5|6&47)|3)|2)|1) with the release of Letraset\n" +
                " sheets containing Lorem Ipsum passages, and more recently with desktop publishing\n" +
                " software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                "    It is a long established fact that a reader will be distracted by the readable\n" +
                " content of a page when looking at its layout. The point of using\n" +
                " (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78 Ipsum is that it has a more-or-less normal\n" +
                " distribution of letters, as opposed to using (Content here), content here', making it look\n" +
                " like readable English.\n";

        List<TextComponent> textList = textParser.parseIt(text);
        TextComponent sortedText = functionality.sortParagraphBySentencesAmount(textList.get(0));
        assertEquals(sortedText.getTextValue(), expectedTextAfterSort);

    }

    @Test
    public void testSortWordsByLength() {

        List<TextComponent> textComponentList = textParser.parseIt(text);
        TextComponent wordsSortedByLength = functionality.sortWordsByLength(textComponentList.get(0));
        List<TextComponent> list = wordsSortedByLength.getAllTextComponentsOfType(Sentence.class);
        String previousWord = list.get(0).getTextValue();
        String nextWord = list.get(1).getTextValue();
        assertTrue(previousWord.length() <= nextWord.length());
    }

    @Test
    public void testSortLexemeBySymbol() {

        String testedLine = "Need succeeded passion let.";
        SentenceParser sentenceParser = new SentenceParser();
        List<TextComponent> unsortedList = sentenceParser.parseIt(testedLine);
        Sentence sentence = new Sentence(unsortedList);
        List<TextComponent> sortedList = functionality.sortLexemeBySymbol(sentence, 'e');
        String expected = "succeeded";
        String actual = sortedList.get(0).getTextValue();
        assertEquals(actual, expected);
    }

    @Test
    public void testRestoreText() {

        String expectedRestoredText = "    It has survived - not only (five) centuries, but also the leap into 52\n" +
                " electronic typesetting, remaining 3 essentially 9 unchanged. It was\n" +
                " popularised in the 5 with the release of Letraset\n" +
                " sheets containing Lorem Ipsum passages, and more recently with desktop publishing\n" +
                " software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                "    It is a long established fact that a reader will be distracted by the readable\n" +
                " content of a page when looking at its layout. The point of using\n" +
                " 78 Ipsum is that it has a more-or-less normal\n" +
                " distribution of letters, as opposed to using (Content here), content here', making it look\n" +
                " like readable English.\n" +
                "    It is a 1274 established fact that a reader will be of a\n" +
                " page when looking at its layout.\n" +
                "    Bye.\n";

        List<TextComponent> textComponentList = textParser.parseIt(text);
        TextComponent textEntity = textComponentList.get(0);
        String restoredText = functionality.restoreText(textEntity);
        assertEquals(restoredText, expectedRestoredText);
    }
}