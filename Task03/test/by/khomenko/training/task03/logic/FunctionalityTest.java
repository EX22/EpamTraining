package by.khomenko.training.task03.logic;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import by.khomenko.training.task03.entity.TextComponent;

public class FunctionalityTest {

    @DataProvider(name = "paragraphs")
    public Object[][] createParagraphs() {
        List<TextComponent> paragraphList = new ArrayList<>();

        return null;
    }


    @Test
    public void testSortParagraphBySentencesAmount() {

    }

    @Test
    public void testSortWordsByLength() {
    }

    @Test
    public void testSortLexemeBySymbol() {
    }

    @Test
    public void testRestoreInitialText() {
    }
}