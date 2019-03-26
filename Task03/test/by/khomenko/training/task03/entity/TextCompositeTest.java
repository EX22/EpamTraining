package by.khomenko.training.task03.entity;

import by.khomenko.training.task03.comparator.SymbolAppearanceComparator;
import by.khomenko.training.task03.logic.LexemeParser;
import by.khomenko.training.task03.logic.TextParser;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

import static org.testng.Assert.*;

public class TextCompositeTest {

    private String testedText = "    People who say it can not be done, should not "
            + "interrupt those who are doing it.\n";
    private TextParser textParser = new TextParser();
    private TextComponent textComponent = new Text(textParser.parseIt(testedText));

    @Test
    public void testGetValue() {
        String actual = textComponent.getValue();
        assertEquals(actual, testedText);
    }

    @Test
    public void testGetTextValue() {
        String actual = textComponent.getTextValue();
        assertEquals(actual, testedText);
    }

    @Test
    public void testGetSymbolAppearanceCount() {
        int actual = textComponent.getSymbolAppearanceCount('t');
        int expected = 7;
        assertEquals(actual, expected);
    }

    @Test
    public void testGetComponentsCount() {
        int actual = textComponent.getComponentsCount();
        int expected = 1;
        assertEquals(actual, expected);
    }

    @Test
    public void testGetAllTextComponentsOfType() {
        List<TextComponent> list = textComponent.getAllTextComponentsOfType(Text.class);
        int actual = list.size();
        int expected = 1;
        assertEquals(actual, expected);
    }

    @Test
    public void testSortTextComponents() {
        LexemeParser lexemeParser = new LexemeParser();
        List<TextComponent> list = lexemeParser.parseIt(testedText);
        Sentence sentence = new Sentence(list);
        Comparator<TextComponent> comparator = new SymbolAppearanceComparator('e');
        sentence.sortTextComponents(comparator);
        String actual = sentence.getTextValue();
        String expected = "who say it can not should not who doing it.\n"
                + " be done, interrupt those are People";
        assertEquals(actual, expected);
    }
}